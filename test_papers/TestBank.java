import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TestBank {
    private Test test;
    private int N_QUESTIONS = 10;
    private Question[] bank = new Question[N_QUESTIONS];
    private String IN_FILE = "input_files/test_bank1.txt";
    private String OUT_FILE = "output_files/test1.txt";

    public int getLines(Scanner in) {
        int output = 0;
        while (in.hasNextLine()) {
            in.nextLine();
            output++;
        }
        return output;
    }

    public void run() throws FileNotFoundException {
        Random rand = new Random();
        Scanner in = new Scanner(new File(IN_FILE));
        int bankSize = getLines(in);
        in.close();
        Scanner in = new Scanner(new File(IN_FILE), ",");

        in.nextLine(); // Throw away the header
        for (int i = 0; i < bankSize; i++) {
            Scanner lineSc = new Scanner(in.nextLine());

            String questionType = lineSc.next();
            int points = lineSc.nextInt();
            int difficulty = lineSc.nextInt();
            int answerSpace = lineSc.nextInt();
            String questionText = lineSc.next();

            if (questionType.equals("MultipleChoice")) {
                ;
            } else if (questionType.equals("FillInTheBlank"))
        }
    }

    public static void main(String[] args) {
        TestBank tb = new TestBank();
        try{
            tb.run();
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
}