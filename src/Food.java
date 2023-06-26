package src;

import lib.Page.Page;
import lib.Page.FoodPage.FoodPage;

public class Food {
    private String name;
    private double price;
    private int ID;
    private boolean active;
    private FoodPage page;


    public Food(String foodName, double price,int ID) {
        this.name = foodName;
        this.price = price;
        this.ID = ID;
    }


    public String getName() {
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public void activeFood(){
        this.active = true;
    }

    public void deactiveFood(){
        this.active = false;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(FoodPage page) {
        this.page = page;
    }

}
