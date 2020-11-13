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
            Scanner oneTask = new Scanner(lineScanner.next()).useDelimiter(",");
            
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
        Scanner mainScanner;
        try{
            mainScanner = new Scanner(new File(FILENAME));
        } catch(FileNotFoundException ex) {
            mainScanner = new Scanner("FileNotFound");
            System.out.println(ex);
        }

        mainScanner.nextLine(); // throw away the formatting header
        String line = mainScanner.nextLine();
        parseLineAddTasks(line);
        Task activeTask = hdl.chooseTask("first");

        while (mainScanner.hasNextLine() || hdl.hasTasks()) {
            if (activeTask != null) {
                if (activeTask.tick()) {
                    activeTask = hdl.chooseTask("first");
                }
            } else {
                activeTask = hdl.chooseTask("first");
            }

            if (mainScanner.hasNextLine()) {
                line = mainScanner.nextLine();
                System.out.println("Current line: " + line);
                parseLineAddTasks(line);
                System.out.println(hdl);
            }
        }
    }
}








