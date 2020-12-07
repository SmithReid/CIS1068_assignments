import java.util.HashMap;

public class MultipleChoiceQuestion extends ObjectiveQuestion {
    protected HashMap<String, String> possibleAnswers;

    public MultipleChoiceQuestion(int points, 
                                    int difficulty, 
                                    int answerSpace, // For consistensy, 
    // we will take the argument, but we will pass 1 to the ObjectiveQuestion contructor
                                    String questionText, 
                                    String correctAnswer, 
                                    HashMap<String,String> possibleAnswers) {
        super(points, difficulty, 1, questionText, correctAnswer);
        this.possibleAnswers = possibleAnswers;
    }

    public HashMap<String, String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String toString() {
        String choices = "";
        for (String answerKey : possibleAnswers.keySet()) {
            choices += answerKey + ": " + possibleAnswers.get(answerKey) + "\n";
        }
        return "Points: " + points + "\n" + 
                "Difficulty: " + difficulty + "\n\n" + 
                questionText + "\n" + 
                "---------------------" + "\n" + 
                choices;
    }

    public static void main(String[] args) {
        HashMap<String, String> answers = new HashMap<String, String>();
        answers.put("A", "One leg's the same");
        answers.put("B", "lolidk");
        answers.put("C", "swiggity swoogity, this is only a test");
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(1, 1, 1, "What's the difference between a duck?", "A", answers);
        System.out.println(mcq);
    }
}