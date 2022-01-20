import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CardSet {
    private ArrayList<Card> set;
    private String setName;

    public CardSet(ArrayList<Card> set, String setName) {
        this.set = set;
        this.setName = setName;
    }

    public static ArrayList<Integer> getProbability() {
        ArrayList<Integer> probability = new ArrayList<>(Arrays.asList(3, 3, 3));
        return probability;
    }

    public ArrayList<Card> getSet() {
        return set;
    }

    public void setSet(ArrayList<Card> set) {
        this.set = set;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public static File createFileSet(CardSet cardSet) {
        File file = new File("src\\main\\resources\\" + cardSet.getSetName() + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static void writeFileSet(File file, CardSet cardSet) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(cardSet.getSetName());

            int size = cardSet.getSet().size();
            for(int i = 0; i < size; i++) {
                writer.newLine();
                writer.write(cardSet.getSet().get(i).getQuestion());
                writer.newLine();
                writer.write(cardSet.getSet().get(i).getAnswer());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
