import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    public Pane menuPane;
    public JFXButton startButton;

    public Pane questionPane;
    public TextArea cardQuestion;
    public JFXButton blueButton;

    public Pane answerPane;
    public TextArea cardAnswer;
    public JFXButton greenButton;
    public JFXButton yellowButton;
    public JFXButton redButton;

    public ArrayList<Card> redCards;
    public ArrayList<Card> yellowCards;
    public ArrayList<Card> greenCards;
    public ArrayList<Integer> probability;
    public Card currentCard;
    
    public void initialize() {
        menuPane.setVisible(true);
        questionPane.setVisible(false);
        answerPane.setVisible(false);
    }

    public void startGame() {
        menuPane.setVisible(false);
        questionPane.setVisible(true);
        redCards = Card.makeFlashcards();
        probability = Card.getProbability();

        setQuestion();
    }

    public void setQuestion() {
        int deckChoice = probability.get((int) (Math.random() * probability.size()));
        System.out.println(deckChoice);

        if(deckChoice == 3) {
            currentCard = redCards.get((int) (Math.random() * redCards.size()));
        }
        if(deckChoice == 2) {
            currentCard = yellowCards.get((int) (Math.random() * yellowCards.size()));
        }
        if(deckChoice == 1) {
            currentCard = greenCards.get((int) (Math.random() * greenCards.size()));
        }

        cardQuestion.setText(currentCard.question);
    }

    public void seeAnswer() {
        questionPane.setVisible(false);
        answerPane.setVisible(true);

        cardAnswer.setText(currentCard.answer);
    }
}
