package src;

import java.util.ArrayList;


public class Restaurant {
    //==============================================================================
    private String name;
    private String foodType;
    private int ID;

    private ArrayList<Food> menu = new ArrayList<>();

    private ArrayList<Order> OrderHistory = new ArrayList<>();
    private ArrayList<Order> OpenOrders = new ArrayList<>();
    private boolean order = false;
    //==============================================================================


    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.ID;
    }

    public ArrayList<Food> getMenu(){
        return menu;
    }



}