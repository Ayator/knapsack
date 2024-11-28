// Item class represents an object that could be added to a sack.
public class Item {
    public final int value, weight, id;

    public Item(int value, int weight, int id){
        this.value = value;
        this.weight = weight;
        this.id = id;
    }

    // Provides a string representation of an Item object.
    // example output: Item: [id: 0, value: 10, weight: 5]
    @Override
    public String toString() {
        return "Item: [id: " + id + ", value: " + value + ", weight: " + weight + "]";
    }
}
