import java.util.ArrayList;
import java.util.BitSet;

public class Sack {
    private int totalValue;

    // old
    // Sack parentSack;
    // Item item;

    // new
    // boolean[] sack;
    final Item[] items;

    BitSet bits;

    public BitSet getBits() {
        return bits;
    }

    public Sack(Item[] items){
        this.totalValue = 0;
        // v1
        // this.parentSack = parentSack;
        // v2
        // this.items = items;
        // this.sack = new boolean[items.length];
        // v3
        this.bits = new BitSet(items.length);
        this.items = items;
    }

    // v1
    // old version using recursion to store Sacks (consumes too much ram for ks_82_0 and ks_106_0)
    // public Sack(Sack parentSack){
    //     this.totalValue = 0;
    //     this.parentSack = parentSack;
    // }

    // v1
    // public Sack(Sack parentSack, Item newItem){
    //     this.totalValue = parentSack.totalValue + newItem.value;
    //     this.parentSack = parentSack;
    //     this.item = newItem;
    // }

    // v1
    // public ArrayList<Item> items(int currentSize){
    //     if(parentSack == null)
    //         return new ArrayList<>(currentSize);
    //     ArrayList<Item> items;
    //     items = parentSack.items(currentSize + 1);
    //     items.add(item);
    //     return items;
    // }

    // v1
    // public ArrayList<Item> items(){
    //     return items(0);
    // }

    // v2
    // public ArrayList<Item> items(){
    //     ArrayList<Item> list = new ArrayList<>(sack.length);
    //     for (int i = 0; i < sack.length; i++)
    //         if(sack[i])
    //             list.add(items[i]);
    //     return list;
    // }

    // v2
    // public void updateSack(Sack otherSack, Item newItem){
    //     for (int i = 0; i < otherSack.sack.length; i++) {
    //         sack[i] = otherSack.sack[i];
    //     }
    //     totalValue = otherSack.totalValue + newItem.value;
    //     sack[newItem.id] = true;
    // }

    // v3
    // assume number of items is less than maximum integer value
    public ArrayList<Item> items(){
        ArrayList<Item> list = new ArrayList<>(bits.cardinality());
        for(int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1))
            list.add(items[i]);
        return list;
    }

    // v3
    public void updateSack(Sack otherSack, Item newItem){
        bits.clear();
        bits.or(otherSack.bits);
        bits.set(newItem.id);
        totalValue = otherSack.totalValue + newItem.value;
    }

    public int totalValue() {
        return totalValue;
    }

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
