package src;

import java.util.ArrayList;

public class Customer extends User {

    Customer(String name,String password){
        this.username = name;
        this.password = password;
    }
    //********************************************************************

    ArrayList<Restaurant> localRests = new ArrayList<>();

    ArrayList<Restaurant> favRests = new ArrayList<>();

    ArrayList<Rating> ratings = new ArrayList<>();

    ArrayList<Restaurant> clickedRests = new ArrayList<>();

    Restaurant currRest;

    ArrayList<Order> orderHistory = new ArrayList<>();

    ArrayList<Food> cart = new ArrayList<>();

    double CustomerCharge;

    double OfferLimit;

    ArrayList<Double> Offers = new ArrayList<>();

    //********************************************************************

    void ShowLocalRests(){
        for (Restaurant rest:localRests) {

        }
    }

    void Search(String restName){
        ArrayList<Integer> ID = new ArrayList<>();
        ID.clear();
        for (Restaurant rest: localRests) {
            if (rest.name.contains(restName))
                ID.add(rest.ID);
        }
        for (Integer restID: ID) {
            for (Restaurant rest: localRests){
                if (rest.ID == restID)
                    System.out.println(rest.name + " " + rest.ID);
            }
        }
    }

    void Select(int id){
        for (Restaurant rest: localRests){
            if (rest.ID == id)
                currRest = rest;
        }
    }
}
