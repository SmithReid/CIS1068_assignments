public class Question {
    /*********************CONSTRUCTION***************************/

    protected int points, difficulty, answerSpace;
    protected String questionText;

    public Question(int points, 
                    int difficulty, 
                    int answerSpace, 
                    String questionText) {
        this.points = points;
        this.difficulty = difficulty;
        this.answerSpace = answerSpace;
        this.questionText = questionText;
    }

    /************************GET FUNCTIONS*************************/

    public int getPoints() {
        return this.points;
    }
    public int getDifficulty() {
        return this.difficulty;
    }
    public int getAnswerSpace() {
        return this.answerSpace;
    }
    public String getQuestionText() {
        return this.questionText;
    }

    public String getName() {
        return "Question (likely non-objective question.)";
    }

    public String toString() {
        return "Points: " + points + "\n" + 
                "Difficulty: " + difficulty + "\n\n" + 
                questionText + "\n";
    }

    public String getCorrectAnswer() {
        return "This question may not have one correct answer.";
    }

    // I have decided not to implement set functions, 
       // as I see little use case beyond construction
}