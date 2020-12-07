import java.util.Scanner;
import java.util.HashMap;

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
                        questions[i].toString() + "\n\n";
        }
        return output;
    }

    public static void main(String[] args) {
        // ObjectiveQuestion[] generatedQuestions = readQuestions("test");
        // Test test = new Test(generatedQuestions);
        ;
    }
}