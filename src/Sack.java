import java.util.ArrayList;

public class Sack {
    private int totalValue;

    // old
    // Sack parentSack;
    // Item item;

    // new
    boolean[] sack;
    final Item[] items;

    public Sack(Item[] items){
        this.totalValue = 0;
        // this.parentSack = parentSack;
        this.items = items;
        this.sack = new boolean[items.length];
    }

    // old version using recursion to store Sacks (consumes too much ram for ks_82_0 and ks_106_0)
    // public Sack(Sack parentSack){
    //     this.totalValue = 0;
    //     this.parentSack = parentSack;
    // }

    // public Sack(Sack parentSack, Item newItem){
    //     this.totalValue = parentSack.totalValue + newItem.value;
    //     this.parentSack = parentSack;
    //     this.item = newItem;
    // }

    // public ArrayList<Item> items(int currentSize){
    //     if(parentSack == null)
    //         return new ArrayList<>(currentSize);
    //     ArrayList<Item> items;
    //     items = parentSack.items(currentSize + 1);
    //     items.add(item);
    //     return items;
    // }

    // public ArrayList<Item> items(){
    //     return items(0);
    // }

    public ArrayList<Item> items(){
        ArrayList<Item> list = new ArrayList<>(sack.length);
        for (int i = 0; i < sack.length; i++)
            if(sack[i])
                list.add(items[i]);
        return list;
    }

    public void updateSack(Sack otherSack, Item newItem){
        for (int i = 0; i < otherSack.sack.length; i++) {
            sack[i] = otherSack.sack[i];
        }
        totalValue = otherSack.totalValue + newItem.value;
        sack[newItem.id] = true;
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
