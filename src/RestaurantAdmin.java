package src;

import java.util.ArrayList;

public class RestaurantAdmin extends User {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    public Restaurant currRestaurant = null;

    RestaurantAdmin(String name,String password){
        this.username = name;
        this.password = password;
    }
    
    public ArrayList<Restaurant> getRests(){
        return this.restaurants;
    }


    
}
