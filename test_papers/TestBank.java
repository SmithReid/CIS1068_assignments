import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestBank {
    private Test test;
    private int N_QUESTIONS = 2;
    private Question[] bank;
    private String IN_FILE = "input_files/test_bank1.txt"; // 3 questions long... 
                                                            // sufficient for testing
    private String OUT_FILE = "output_files/test1.txt";

    public int getLines(Scanner in) {
        int output = 0;
        while (in.hasNextLine()) {
            in.nextLine();
            output++;
        }
        return output;
    }

    public Question processLineToQuestion(Scanner sc) {
        String questionType = sc.next();
        int points = sc.nextInt();
        int difficulty = sc.nextInt();
        int answerSpace = sc.nextInt();
        String questionText = sc.next();

        if (questionType.equals("MultipleChoice")) {
            int nAnswers = sc.nextInt();
            String correctAnswer = sc.next();
            HashMap<String,String> answerBank = new HashMap<String,String>();
            for (int i = 0; i < nAnswers; i++) {
                answerBank.put(Character.toString(65 + i), sc.next());
            }
            return new MultipleChoiceQuestion(points, difficulty, answerSpace, questionText, correctAnswer, answerBank);
        } else if (questionType.equals("FillInTheBlank")) {
            String correctAnswer = sc.next();
            int answerLocation = sc.nextInt();
            return new FillInTheBlankQuestion(points, difficulty, answerSpace, questionText, correctAnswer, answerLocation);
        } else if (questionType.equals("OpenEnded")) {
            return new Question(points, difficulty, answerSpace, questionText);
        }
        return new Question(1,1,1,"Lorem ipsum");
    }

    public Question[] shuffleAndShrinkBank() {
        List<Question> qList = Arrays.asList(bank);
        Question[] output = new Question[N_QUESTIONS];

        Collections.shuffle(qList);

        Question[] temp = new Question[bank.length];
        qList.toArray(temp);

        int outputCounter = 0;
        for (int i = 0; i < N_QUESTIONS; i++) {
            output[i] = bank[i];
        }
        return output;
    }

    public void run() throws FileNotFoundException {
        // Build questions
        Scanner in = new Scanner(new File(IN_FILE));
        int bankSize = getLines(in);
        bankSize--;
        in.close();

        bank = new Question[bankSize];

        Scanner in2 = new Scanner(new File(IN_FILE));

        in2.nextLine(); // Throw away the header
        for (int i = 0; i < bankSize; i++) {
            Scanner lineSc = new Scanner(in2.nextLine()).useDelimiter(",");

            bank[i] = processLineToQuestion(lineSc);

            lineSc.close();
        }
        in2.close();

        // pull N_QUESTIONS from questions and print new test
        Question[] smallBank = shuffleAndShrinkBank();

        Test test = new Test(smallBank);
        System.out.println(test);
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