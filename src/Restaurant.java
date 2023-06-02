package src;

import java.util.ArrayList;
import lib.Page.RestaurantPage.RestaurantPage;

public class Restaurant {
    //==============================================================================
    private String name;
    private String foodType;
    private int ID;
    private RestaurantPage page;
    private ArrayList<Food> menu = new ArrayList<>();
    private Node location;
    private ArrayList<Order> OrderHistory = new ArrayList<>();
    private ArrayList<Order> OpenOrders = new ArrayList<>(); 
    private boolean order = false;
    //==============================================================================
    public Restaurant(String name,Node location,int ID){
        this.name = name;
        this.location = location;
        this.ID = ID;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.ID;
    }

    public Node getLoc(){
        return this.location;
    }

}
