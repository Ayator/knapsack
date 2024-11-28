import java.util.ArrayList;
import java.util.BitSet;

/* Sack class is designed to represent a knapsack-like object.
 * It uses the BitSet class to track which items are in the sack and
 * includes methods to manipulate and query the sack's contents.
 */
public class Sack {
    // Stores the total value of all items in the sack. This is updated as items are added or removed.
    private int totalValue;
    // An array of Item objects representing all available items that could be placed in the sack.
    // This array is passed into the constructor.
    final Item[] items;
    // A BitSet object used to track which items are in the sack.
    //  * Each index in the BitSet corresponds to the index of an Item in the items array.
    //  * If bits.get(i) is true, the item at items[i] is in the sack; otherwise, it is not.
    BitSet bits;

    // Returns the current BitSet (bits) of the sack. 
    public BitSet getBits() {
        return bits;
    }

    // Constructor initializes a Sack object with a given array of Item objects.
    public Sack(Item[] items){
        this.totalValue = 0;
        // Initialize BitSet with a size equal to the number of items.
        this.bits = new BitSet(items.length);
        this.items = items;
    }

    // Generates a list of items currently in the sack based on the bits BitSet.
    // Assumes number of items is less than maximum integer value.
    public ArrayList<Item> items(){
        // bits.cardinality() is the number of set bits, i.e., items in the sack
        ArrayList<Item> list = new ArrayList<>(bits.cardinality());
        // nextSetBit(int) finds the next index in the BitSet where a bit is true
        for(int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1))
            list.add(items[i]);
        return list;
    }

    // Updates the current sack by copying the contents of another sack and adding a new item to it.
    public void updateSack(Sack otherSack, Item newItem){
        // remove all items
        bits.clear();
        // copy bits from otherSack to current sack
        bits.or(otherSack.bits);
        // adds newItem to its respective bit position
        bits.set(newItem.id);
        // update totalValue by adding new item's value
        totalValue = otherSack.totalValue + newItem.value;
    }

    // Returns the current totalValue of the sack.
    public int totalValue() {
        return totalValue;
    }

    // Provides a string representation of the sack, including its total value and a list of the items it contains.
    @Override
    public String toString() {
        String str = "Sack: [totalValue: " + totalValue + ", [\n";
        ArrayList<Item> sackItems = items();
        for (int i = 0; i < sackItems.size() - 1; i++) {
            str += sackItems.get(i).toString() + ",\n";
        }
        str += sackItems.get(sackItems.size() - 1).toString() + "\n]";
        return str;
    }
}
