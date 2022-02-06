import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return ("q: " + getQuestion() + " ... a: " + getAnswer());
    }

}

//choose subject when making a deck and the background is set based on subject