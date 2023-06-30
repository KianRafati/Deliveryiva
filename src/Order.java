package src;

import java.util.ArrayList;

public class Order {
    private int ID;
    private Restaurant restaurant;
    private Customer customer;
    private ArrayList<Food> contents = new ArrayList<>();
    private String status;
    public static String[] statuses = {
        "Waiting for restuarant's approval..",
        "Processing your order",
        "Delivery on the way",
        "Your order has been delivered"
    };

    Order(int ID,Restaurant restaurant,Customer customer,ArrayList<Food> contents){
        this.ID = ID;
        this.restaurant = restaurant;
        this.customer = customer;
        this.contents = contents;
        this.status = statuses[0];
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public int getID() {
        return this.ID;
    }

    public Restaurant getRest() {
        return this.restaurant;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public ArrayList<Food> getContents(){
        return this.contents;
    }

    public void EditOrder(){

    }

    public void getTotalCost(){

    }

    public String showEstTime(){
        return null;
    }

    public void ShowBestPath(){

    }

    public void ShowPath(){

    }

    public void showContents(){
        for (Food food : contents) {
            System.out.println("--------------------------");
            System.out.println(food.getName());
            System.out.println(food.getPrice());
        }
    }
}
