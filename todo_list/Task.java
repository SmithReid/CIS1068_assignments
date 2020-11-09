import java.time.LocalDateTime;
import java.util.Random;
import java.lang.RuntimeException;

public class Task {

    /********************CONSTRUCTION**********************/

    String name;
    int priority, estSecsToComplete;
    LocalDateTime whenDue;
    Random rand = new Random();

    public Task(String cName, 
                int cPriority, int cEstSecsToComplete, 
                LocalDateTime cWhenDue) {
        try {
            if (cPriority < 0) {
                throw new Exception("Priority must be a non-negative integer.");
            }
            if (cEstSecsToComplete < 0) {
                throw new Exception("Seconds to complete must be a non-negative integer.");
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        name = cName;
        priority = cPriority;
        estSecsToComplete = cEstSecsToComplete;
        whenDue = cWhenDue;
    }

    public String toString() {
        return "Task: " + name + "\n";
    }

    /********************GET METHODS**********************/

    public String getName() {
        return name;
    }
    public int getPriority() {
        return priority;
    }
    public int getEstSecsToComplete() {
        return estSecsToComplete;
    }
    public LocalDateTime getWhenDue() {
        return whenDue;
    }

    /*********************SET METHODS***********************/

    public void setName(String newName) {
        name = newName;
    }
    public void setEstSecsToComplete(int newEstSecsToComplete) {
        estSecsToComplete = newEstSecsToComplete;
    }
    public void setWhenDue(LocalDateTime newWhenDue) {
        whenDue = newWhenDue;
    }

    /********************PRIORITY CONTROLS******************/

    public void increasePriority(int amount) {
        priority += amount;
    }
    public void decreasePriority(int amount) {
        priority -= amount;
    }

    /********************OVERDUE****************************/

    public boolean overdue() {
        return whenDue.isAfter(LocalDateTime.now());
    }

    /********************RUN TASK****************************/

    public void runTask() {
        // The task will complete in a random number of seconds n where
            // 0 <= estSecsToComplete - 2 < n < estSecsToComplete + 2
        int n = rand.nextInt(4) - 2;
        n += estSecsToComplete;
        if (n < 0) {
            n = 0;
        }
        System.out.println(name + " begun.");
        // Try/Catch block pretty boilerplate from here: 
        // https://dzone.com/articles/how-to-handle-the-interruptedexception
        try {
            Thread.sleep(n * 1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
        System.out.println(name + " complete.");
    }
}