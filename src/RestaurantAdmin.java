package src;

import java.util.ArrayList;

import lib.Page.RestaurantAdminPage.RestaurantAdminPage;

public class RestaurantAdmin extends User {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    public Restaurant currRestaurant = null;
    private RestaurantAdminPage restAdminPage;

    RestaurantAdmin(String name,String password){
        this.username = name;
        this.password = password;
    }

    public void setPage(RestaurantAdminPage restAdminPage){
        this.restAdminPage = restAdminPage;
    }
    
    public RestaurantAdminPage getPage(){
        return this.restAdminPage;
    }

    public ArrayList<Restaurant> getRests(){
        return this.restaurants;
    }


    
}
