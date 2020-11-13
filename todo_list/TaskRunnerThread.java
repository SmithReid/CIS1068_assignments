public class TaskRunnerThread 
extends Thread {
    public HoneyDoList hdl;

    public TaskRunnerThread(HoneyDoList cHdl) {
        hdl = cHdl;
    }

    public void run() {
        while (hdl.hasTasks()) {
            hdl.chooseTask("first").runTask();
        }
    }
}
