package src;

import java.util.ArrayList;

public class Food {
    private ArrayList<Rating> ratings = new ArrayList<>();
    private int ID;
    private double price;
    private String name;
    public int getID(){
        return this.ID;
    }
    public double getPrice(){
        return this.price;
    }
    public String getName(){
        return this.name;
    }
}
