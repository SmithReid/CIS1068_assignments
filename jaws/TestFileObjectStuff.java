import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class TestFileObjectStuff {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream(new File("test.txt"));
    }
}
