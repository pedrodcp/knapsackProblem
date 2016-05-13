/**
 * Created by Pedro on 22/04/2016.
 */
public class Item {
    private String name    = "";
    private int weight     = 0;
    private int value      = 0;
    private int inKnapsack = 0; // numero de items deste tipo que existem na solução

    public Item() {}

    public Item(Item item) {
        setName(item.name);
        setWeight(item.weight);
        setValue(item.value);
    }

    public Item(int _weight, int _value) {
        setWeight(_weight);
        setValue(_value);
    }

    public Item(int _weight, int _value, int _bounding) {
        setWeight(_weight);
        setValue(_value);
    }

    public Item(String _name, int _weight, int _value) {
        setName(_name);
        setWeight(_weight);
        setValue(_value);
    }

    public Item(String _name, int _weight, int _value, int _bounding) {
        setName(_name);
        setWeight(_weight);
        setValue(_value);
    }

    public void setName(String _name) {name = _name;}
    public void setWeight(int _weight) {weight = Math.max(_weight, 0);}
    public void setValue(int _value) {value = Math.max(_value, 0);}

    public void setInKnapsack(int _inKnapsack) {
        inKnapsack = Math.max(_inKnapsack, 0);
    }


    public void checkMembers() {
        setWeight(weight);
        setValue(value);
        setInKnapsack(inKnapsack);
    }

    public String getName() {return name;}
    public int getWeight() {return weight;}
    public int getValue() {return value;}
    public int getInKnapsack() {return inKnapsack;}
}
