package src;

import java.util.ArrayList;

import lib.Page.RestaurantAdminPage.RestaurantAdminPage;

public class RestaurantAdmin extends User {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    public Restaurant currRestaurant = null;
    private RestaurantAdminPage restAdminPage;

    RestaurantAdmin(String name,String password,int id){
        this.username = name;
        this.password = password;
        this.user_id = id;
    }

    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
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
