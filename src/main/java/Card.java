import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    String question;
    String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public static ArrayList<Card> makeFlashcards() {
        ArrayList<Card> flashcards = new ArrayList<>();

        flashcards.add(new Card("q1. green", "a5. green"));
        flashcards.add(new Card("q2. green", "a5. green"));
        flashcards.add(new Card("q3. green", "a5. green"));
        flashcards.add(new Card("q4. green", "a5. green"));
        flashcards.add(new Card("q5. green", "a5. green"));

        flashcards.add(new Card("q6. yellow", "a6. yellow"));
        flashcards.add(new Card("q7. yellow", "a7. yellow"));
        flashcards.add(new Card("q8. yellow", "a8. yellow"));
        flashcards.add(new Card("q9. yellow", "a9. yellow"));
        flashcards.add(new Card("q10. yellow", "a10. yellow"));

        flashcards.add(new Card("q11. red", "a11. red"));
        flashcards.add(new Card("q12. red", "a12. red"));
        flashcards.add(new Card("q13. red", "a13. red"));
        flashcards.add(new Card("q14. red", "a14. red"));
        flashcards.add(new Card("q15. red", "a15. red"));

        return flashcards;
    }

    public static ArrayList<Integer> getProbability() {
        ArrayList<Integer> probability = new ArrayList<>(Arrays.asList(3, 3, 3));
        return probability;
    }
}
