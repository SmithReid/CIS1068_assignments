import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class Manager {
    public final String FILENAME = "input_files/test1.txt";

    public int countTasks(String line) {
        Scanner lineScanner = new Scanner(line).useDelimiter(";");
        int output = 0;
        while(lineScanner.hasNext()) {
            lineScanner.next();
            output++;
        }
        return output;
    }

    public Task[] lineToTasks(String line) {
        int outputLength = countTasks(line);

        Task[] output = new Task[outputLength];
        Scanner lineScanner = new Scanner(line).useDelimiter(";");

        System.out.println(line);

        for (int i = 0; i < outputLength; i++) {
            Scanner oneTask = new Scanner(lineScanner.next()).useDelimiter(", ");
            
            String taskName;
            int taskPriority, taskEstSecsToComplete;
            int taskSecsToDue;

            taskName = oneTask.next();
            taskPriority = oneTask.nextInt();
            taskEstSecsToComplete = oneTask.nextInt();
            taskSecsToDue = oneTask.nextInt();

            output[i] = new Task(taskName, 
                                taskPriority, 
                                taskEstSecsToComplete, 
                                LocalDateTime.now().plusSeconds(taskSecsToDue));
        }
        return output;
    }

    public void run() throws FileNotFoundException {
        // TODO: implement multithreading to allow per tick updating of the HoneyDoList
            // for considering different tasks each time a task is completed. 
        HoneyDoList hdl = new HoneyDoList();
        Scanner mainScanner = new Scanner(new File(FILENAME));
        mainScanner.nextLine(); // throw away the formatting header
        while (mainScanner.hasNextLine()) {
            String line = mainScanner.nextLine();
            Task[] thisTick = lineToTasks(line);
            for (int i = 0; i < thisTick.length; i++) {
                hdl.addTask(thisTick[i]);
            }
        }
        while (hdl.hasTasks()) {
            TaskThread tt = new TaskThread(hdl.chooseTask("first"));
            tt.start();
            try{
                tt.join();
            } catch(InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}








