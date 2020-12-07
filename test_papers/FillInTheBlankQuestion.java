public class FillInTheBlankQuestion extends ObjectiveQuestion {
    protected int answerLocation;

    public FillInTheBlankQuestion(int points, 
                                    int difficulty, 
                                    int answerSpace, 
                                    String questionText, 
                                    String correctAnswer, 
                                    int answerLocation) {
        super(points, difficulty, answerSpace, questionText, correctAnswer);
        this.answerLocation = answerLocation;
    }

    public int getAnswerLocation() {
        return answerLocation;
    }

    public String toString() {
        return "Points: " + points + "\n" + 
                "Difficulty: " + difficulty + "\n\n" + 
                questionText + "\n";
    }
}