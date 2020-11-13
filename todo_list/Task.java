import java.time.LocalDateTime;
import java.util.Random;
import java.lang.RuntimeException;

public class Task {

    /********************CONSTRUCTION**********************/

    private String name;
    private int priority, estSecsToComplete, secsToComplete;
    private LocalDateTime whenDue;
    private Random rand = new Random();

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

        secsToComplete = rand.nextInt(4) - 2 + estSecsToComplete;
        if (secsToComplete < 0) {
            secsToComplete = 0;
        }
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

    /********************TASK RUN OPS****************************/

    public void tick() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            System.out.println(ex);
        }
        secsToComplete--;
    }

    public boolean isComplete() {
        return (secsToComplete <= 0); // == would likely work, <= is safer. 
    }
}