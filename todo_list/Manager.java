import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class Manager {
    public final String FILENAME = "input_files/test1.txt";
    public HoneyDoList hdl = new HoneyDoList();

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

    public void parseLineAddTasks(String line) {
        Task[] thisTick = lineToTasks(line);
        for (int i = 0; i < thisTick.length; i++) {
            hdl.addTask(thisTick[i]);
        }
    }

    public void run() {
        // TODO: implement multithreading to allow per tick updating of the HoneyDoList
            // for considering different tasks each time a task is completed. 
        Scanner mainScanner;
        try{
            mainScanner = new Scanner(new File(FILENAME));
        } catch(FileNotFoundException ex) {
            mainScanner = new Scanner("fail");
            System.out.println(ex);
        }
        mainScanner.nextLine(); // throw away the formatting header
        String line = mainScanner.nextLine();
        parseLineAddTasks(line);
        while (mainScanner.hasNextLine()) {
            Task activeTask = hdl.chooseTask("first");
            if (activeTask.isComplete()) {
                activeTask = hdl.chooseTask("first");
            }
            try {
                activeTask.tick();
            } catch(NullPointerException ex) {
                System.out.println("No tasks found to execute.");
            }
            try{
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                System.out.println(ex);
            }
            line = mainScanner.nextLine();
            parseLineAddTasks(line);
        }
    }
}








