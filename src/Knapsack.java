import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;

public class Knapsack {
    public final int itemCount, capacity;
    private final Item[] items;
    private Sack solution;

    public Knapsack(int capacity, Item[] items){
        this.itemCount = items.length;
        this.capacity = capacity;
        this.items = items;
        this.solution = null;
    }

    public Sack GetSolutionSack(){
        if(solution != null)
            return solution;
        Sack[] sacks = new Sack[capacity + 1];
        // Sack nullSack = new Sack(null);
        for (int i = 0; i < capacity + 1; i++) {
            sacks[i] = new Sack(items);
            // sacks[i] = nullSack;
        }
        for (Item item : items) {
            for (int i = capacity; i >= item.weight; i--) {
                Sack sack = sacks[i - item.weight];
                if(sack.totalValue() + item.value > sacks[i].totalValue()){
                    sacks[i].updateSack(sack, item);
                    // sacks[i] = new Sack(sack, item);
                }
            }
        }
        solution = sacks[capacity];
        return solution;
    }

    public static Knapsack LoadFromInputFile(String inputFilepath){
        try{
            File file = new File(inputFilepath);
            Scanner sc = new Scanner(file);
            int n = readNextInt(sc);
            int capacity = readNextInt(sc);
            Item[] items = new Item[n];
            for(int i = 0; i < n; i++){
                int value = readNextInt(sc);
                int weight = readNextInt(sc);
                items[i] = new Item(value, weight, i);
            }
            sc.close();
            return new Knapsack(capacity, items);
        } catch (FileNotFoundException e) {
            System.out.println("Error while reading the file.");
            e.printStackTrace();
        }
        return null;
    }

    public static int readNextInt(Scanner sc){
        if(!sc.hasNextInt()){
            sc.close();
            System.out.println("There is no next integer to read.");
            System.exit(1);
        }
        return sc.nextInt();
    }

    public void saveToFile(String outputFilepath){
        try {
            // save to output
            FileWriter outputFile = new FileWriter(outputFilepath);
            // ArrayList<Item> bestItems = solution.items();
            StringBuilder stringBuilder = new StringBuilder(itemCount * 2 - 1);
            BitSet bits = solution.getBits();
            stringBuilder.append(bits.get(0) ? "1" : "0");
            for (int i = 1; i < itemCount; i++) {
                stringBuilder.append(bits.get(i) ? " 1" : " 0");
            }
            // for (int i = 0, j = 0; i < itemCount; i++) {
            //     if(j < bestItems.size() && items[i] == bestItems.get(j)){
            //         stringBuilder.append('1');
            //         j++;
            //     }
            //     else{
            //         stringBuilder.append('0');
            //     }
            //     if(i < itemCount - 1)
            //         stringBuilder.append(' ');
            // }
            outputFile.write(solution.totalValue() + "\n" + stringBuilder.toString());
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Error while saving the file.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String str = "Knapsack: [\n";
        for (int i = 0; i < itemCount - 1; i++) {
            str += items[i].toString() + ",\n";
        }
        str += items[itemCount - 1].toString() + "\n]";
        return str;
    }
}
