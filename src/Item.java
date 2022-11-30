public class Item {
    public final int value, weight, id;

    public Item(int value, int weight, int id){
        this.value = value;
        this.weight = weight;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item: [id: " + id + ", value: " + value + ", weight: " + weight + "]";
    }
}
