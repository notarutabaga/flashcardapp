import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    public Pane menuPane;
    public JFXButton startButton;

    public Pane questionPane;
    public TextArea cardQuestion;
    public JFXButton blueButton;
    public int deckChoice;

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
    public JFXButton newSetButton;
    public Pane newSetPane;
    public TextField fillName;
    public JFXButton saveCardButton;
    public JFXButton saveSetButton;
    public TextArea fillQuestion;
    public TextArea fillAnswer;

    public Text numCards;

    public Deck currentSet;
    public ArrayList<String> allDecks;

    ArrayList<Card> newSet = new ArrayList<>();
    Deck newCardSet;
    @FXML
    int numCardsCount;

    public void initialize() {
        menuPane.setVisible(true);
        questionPane.setVisible(false);
        answerPane.setVisible(false);
        newSetPane.setVisible(false);
        allDecks = Deck.readInDecks();
        System.out.println(allDecks);
    }

    public Deck chooseDeck() {
        String name = "firstSet";
        ArrayList<Card> cards = Deck.readFileDeck(new File("src\\main\\resources\\savedDecks\\" + name + ".txt"));

        Deck deck = new Deck(cards, name);

        return deck;
    }

    public void startGame() {
        menuPane.setVisible(false);
        questionPane.setVisible(true);
        currentSet = chooseDeck();
        redCards = currentSet.getDeck();
        probability = Deck.getProbability();
        yellowCards = new ArrayList<>();
        greenCards = new ArrayList<>();

        setQuestion();
    }

    public void setQuestion() {
        questionPane.setVisible(true);
        answerPane.setVisible(false);

        deckChoice = probability.get((int) (Math.random() * probability.size()));
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

        cardQuestion.setText(currentCard.getQuestion());
    }

    public void seeAnswer() {
        questionPane.setVisible(false);
        answerPane.setVisible(true);

        cardAnswer.setText(currentCard.getAnswer());
    }

    public void clickedGreen() {
        if(!probability.contains(1)) {
            probability.add(1);
        }

        //if card came from green and chose green remove entirely
        if(deckChoice == 1) {
            greenCards.remove(currentCard);
            if(greenCards.isEmpty()) {
                probability.remove((Integer) 1);
            }
        }

        //if card came from yellow and chose green put to green
        if(deckChoice == 2) {
            yellowCards.remove(currentCard);
            greenCards.add(currentCard);

            if(yellowCards.isEmpty()) {
                probability.remove((Integer) 2);
                probability.remove((Integer) 2);
            }

            if(!probability.contains(1)) {
                probability.add(1);
            }
        }

        //if card came from red and chose green put to green
        if(deckChoice == 3) {
            redCards.remove(currentCard);
            greenCards.add(currentCard);

            if(redCards.isEmpty()) {
                probability.remove((Integer) 3);
                probability.remove((Integer) 3);
                probability.remove((Integer) 3);
            }

            if(!probability.contains(1)) {
                probability.add(1);
            }
        }

        if(greenCards.isEmpty() && yellowCards.isEmpty() && redCards.isEmpty()) {
            endGame();
        } else {
            setQuestion();
        }
    }

    public void clickedYellow() {
        if(!probability.contains(2)) {
            probability.add(2);
            probability.add(2);
        }

        //if card came from green and chose yellow put to yellow
        if(deckChoice == 1) {
            greenCards.remove(currentCard);
            yellowCards.add(currentCard);

            if(greenCards.isEmpty()) {
                probability.remove((Integer) 1);
            }

            if(!probability.contains(2)) {
                probability.add(2);
                probability.add(2);
            }
        }

        //if card came from yellow and chose yellow keep yellow

        //if card came from red and chose yellow put to yellow
        if(deckChoice == 3) {
            redCards.remove(currentCard);
            yellowCards.add(currentCard);

            if(redCards.isEmpty()) {
                probability.remove((Integer) 3);
                probability.remove((Integer) 3);
                probability.remove((Integer) 3);
            }

            if(!probability.contains(2)) {
                probability.add(2);
                probability.add(2);
            }
        }

        if(greenCards.isEmpty() && yellowCards.isEmpty() && redCards.isEmpty()) {
            endGame();
        } else {
            setQuestion();
        }
    }

    public void clickedRed() {
        //if card came from green and chose red put to yellow?
        if(deckChoice == 1) {
            greenCards.remove(currentCard);
            yellowCards.add(currentCard);

            if(greenCards.isEmpty()) {
                probability.remove((Integer) 1);
            }

            if(!probability.contains(2)) {
                probability.add(2);
                probability.add(2);
            }
        }

        //if card came from yellow and chose red put to red
        if(deckChoice == 2) {
            yellowCards.remove(currentCard);
            redCards.add(currentCard);

            if(yellowCards.isEmpty()) {
                probability.remove((Integer) 2);
                probability.remove((Integer) 2);
            }

            if(!probability.contains(3)) {
                probability.add(3);
                probability.add(3);
                probability.add(3);
            }
        }
        //if card came from red and chose red keep red

        if(greenCards.isEmpty() && yellowCards.isEmpty() && redCards.isEmpty()) {
            endGame();
        } else {
            setQuestion();
        }
    }

    public void endGame() {
        initialize();
    }

    public void makeNewSet() {
        newSetPane.setVisible(true);
        menuPane.setVisible(false);
    }

    public void saveCard() {
        if(!fillQuestion.getText().isEmpty() && !fillAnswer.getText().isEmpty()) {
            String question = fillQuestion.getText();
            String answer = fillAnswer.getText();
            newSet.add(new Card(question, answer));
            fillQuestion.clear();
            fillAnswer.clear();
            numCardsCount++;
            numCards.setText(String.valueOf(numCardsCount));
        }
    }

    public void saveSet() {
        if(!newSet.isEmpty() && !fillName.getText().isEmpty()) {
            String setName = fillName.getText();
            newCardSet = new Deck(newSet, setName);
            System.out.println(newCardSet.getName());
            System.out.println(newCardSet.getDeck());

            File file = Deck.createFileSet(newCardSet);
            Deck.writeFileSet(file, newCardSet);
            Deck.appendDeckName(allDecks, setName);

            initialize();
        }
    }
}
