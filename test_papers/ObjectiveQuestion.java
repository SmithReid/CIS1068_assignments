public class ObjectiveQuestion extends Question {
    /********************CONSTRUCTION***********************************/
    protected String correctAnswer;

    public ObjectiveQuestion(int points, 
                                int difficulty, 
                                int answerSpace, 
                                String questionText, 
                                String correctAnswer) {
        super(points, difficulty, answerSpace, questionText);
        this.correctAnswer = correctAnswer;
    }

    /***************************GET FUNCTIONS****************************/

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String toString() {
        return "Points: " + points + "\n" + 
                "Difficulty: " + difficulty + "\n\n" + 
                questionText + "\n";
    }

    public String getName() {
        return "ObjectiveQuestion";
    }

    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer);
    }
}