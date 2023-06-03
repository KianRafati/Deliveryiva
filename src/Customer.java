package src;

import java.util.ArrayList;
import java.util.Objects;

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

    void SearchRest(String restName){
        ArrayList<Integer> ID = new ArrayList<>();
        ID.clear();
        for (Restaurant rest: localRests) {
            if (rest.getName().contains(restName))
                ID.add(rest.getID());
        }
        for (Integer restID: ID) {
            for (Restaurant rest: localRests){
                if (rest.getID() == restID)
                    System.out.println(rest.getName() + " " + rest.getID());
            }
        }
    }

    void SelectRest(int id){
        for (Restaurant rest: localRests){
            if (rest.getID() == id) {
                currRest = rest;
                clickedRests.add(rest);
                ShowMenu(currRest);
            }
        }
    }

    void ShowMenu(Restaurant rest){
        for (Food food: rest.getMenu()) {
            System.out.println(food.getName()+" "+food.getPrice()+" "+food.getID());
        }
    }
    void SearchFood(String name){
        ArrayList<Integer> ID = new ArrayList<>();
        ID.clear();
        for (Food food: currRest.getMenu()) {
            if (food.getName().contains(name))
                ID.add(food.getID());
        }
        for (Integer foodID: ID) {
            for (Food food: currRest.getMenu()){
                if (food.getID() == foodID)
                    System.out.println(food.getName()+" "+food.getPrice()+" "+food.getID());
            }
        }
    }
     void SelectFood (int ID){

     }


}
