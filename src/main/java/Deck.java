import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Deck {
    private ArrayList<Card> deck;
    private String name;

    public Deck(ArrayList<Card> deck, String name) {
        this.deck = deck;
        this.name = name;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Integer> getProbability() {
        ArrayList<Integer> probability = new ArrayList<>(Arrays.asList(3, 3, 3));
        return probability;
    }

    public static File createFileSet(Deck deck) {
        File file = new File("src\\main\\resources\\savedDecks\\" + deck.getName() + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static void writeFileSet(File file, Deck deck) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            int size = deck.getDeck().size();
            writer.write(String.valueOf(size));
            for(int i = 0; i < size; i++) {
                writer.newLine();
                writer.write(deck.getDeck().get(i).getQuestion());
                writer.newLine();
                writer.write(deck.getDeck().get(i).getAnswer());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Card> readFileDeck(File file) {
        ArrayList<Card> newDeck = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String question, answer;

        int size = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            question = scanner.nextLine();
            answer = scanner.nextLine();
            newDeck.add(new Card(question, answer));
        }

        return newDeck;
    }

    public static ArrayList<String> readInDecks() {
        ArrayList<String> allDecks = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src\\main\\resources\\savedDecks\\deckNames.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numDecks = scanner.nextInt();
        for(int i = 0; i < numDecks; i++) {
            allDecks.add(scanner.nextLine());
        }

        return allDecks;
    }

    public static void appendDeckName(ArrayList<String> allDecks, String newDeckName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src\\main\\resources\\savedDecks\\deckNames.txt")));

            int size = allDecks.size();
            writer.write(String.valueOf(size + 1));
            for(int i = 0; i < size; i++) {
                writer.newLine();
                writer.write(allDecks.get(i));
            }

            writer.newLine();
            writer.write(newDeckName);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
