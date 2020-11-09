public class HoneyDoList {
    public final int INITIAL_CAPACITY = 32;
    Task[] tasks;
    int numTasks;

    public HoneyDoList() {
        tasks = new Task[INITIAL_CAPACITY];
        numTasks = 0;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < numTasks; i++) {
            output += tasks[i].toString();
        }
        return output;
    }

    public int find(String name) {
        for (int i = 0; i < numTasks; i++) {
            if (name.equalsIgnoreCase(tasks[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    public void addTask(Task newTask) {
        int firstNull = tasks.length;
        boolean nullFound = false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null && !nullFound) {
                nullFound = true;
                firstNull = i;
            }
        }
        if (firstNull == tasks.length) {
            tasks = new Task[tasks.length + 1];
        }
        tasks[firstNull] = newTask;
        numTasks += 1;
    }

    public int totalTime() {
        int output = 0;
        for (int i = 0; i < numTasks; i++) {
            output += tasks[i].getEstSecsToComplete();
        }
        return output;
    }

    public int shortestTime() {
        int shortestTime = Integer.MAX_VALUE;
        int output = -1;
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i].getEstSecsToComplete() < shortestTime) {
                shortestTime = tasks[i].getEstSecsToComplete();
                output = i;
            }
        }
        return output;
    }

    public Task completeTask(int index) {
        if (index == -1 || index >= numTasks) {
            return null;
        }
        Task output = tasks[index];
        for (int i = 0; i < numTasks; i++) {
            if (i <= index) {
                ; // do nothing
            } else {
                tasks[i - 1] = tasks[i];
            }
        }
        numTasks--;
        return output;
    }

    public Task[] overdueTasks() {
        int outputLength = 0;
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i].overdue()) {
                outputLength++;
            }
        }
        Task[] output = new Task[outputLength];
        int outputIndex = 0;
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i].overdue()) {
                output[outputIndex] = tasks[i];
                outputIndex++;
            }
        }
        return output;
    }

    public Task chooseTask(String selectionMethod) {
        if (selectionMethod.equalsIgnoreCase("shortest")) {
            return completeTask(shortestTime());
            // this may return null
        } 
        if (selectionMethod.equalsIgnoreCase("first")) {
            return completeTask(0);
            // this may return null
        } else {
            return completeTask(shortestTime());
        }
    }

    public boolean hasTasks() {
        if (numTasks > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        HoneyDoList hdl = new HoneyDoList();
    }
}