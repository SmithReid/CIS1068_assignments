import java.util.Scanner;
import java.util.HashMap;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    private Question[] questions;
    private int totalPoints = 0;

    public Test(Question[] questions) {
        this.questions = questions;
        for (Question question : questions) {
            totalPoints += question.getPoints();
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < questions.length; i++) {
            output += "Question number: " + (i + 1) + "\n" + 
                        questions[i].toString() + "\n";
        }
        return output;
    }

    public String answersToString() {
        String output = "";
        for (int i = 0; i < questions.length; i++) {
            output += "Question number: " + (i + 1) + "\n" + 
                        questions[i].toString() + "\n" + 
                        questions[i].getCorrectAnswer() + "\n\n";
        }
        return output;
    }

    public void toFile(String outfile) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File(outfile));
        ps.print(toString());
        ps.close();
    }

    public void answersToFile(String outfile) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File(outfile));
        ps.print(answersToString());
        ps.close();
    }
}