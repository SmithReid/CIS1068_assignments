public class TaskThread 
extends Thread {
    Task task;
    public TaskThread(Task cTask) {
        task = cTask;
    }
    public void run() {
        task.runTask();
    }
}