package src;

public class Food {
    private String name;
    private double price;
    private int ID;


    public Food(String foodName, double price,int ID) {
        this.name = foodName;
        this.price = price;
        this.ID = ID;
    }


    public String getName() {
        return this.name;
    }

}
