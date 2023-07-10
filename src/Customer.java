package src;

import java.util.ArrayList;
import java.util.Objects;

import lib.Page.CustomerPage.CustomerPage;

public class Customer extends User {

    Customer(String name, String password,int id) {
        this.username = name;
        this.password = password;
        this.user_id = id;
    }
    // ********************************************************************

    public Node location;
    ArrayList<Restaurant> localRests = new ArrayList<>();
    ArrayList<Restaurant> favRests = new ArrayList<>();
    ArrayList<Rating> foodRatings = new ArrayList<>();
    ArrayList<Rating> restRatings = new ArrayList<>();
    ArrayList<Restaurant> clickedRests = new ArrayList<>();
    Restaurant currRest;
    Food currFood;
    private CustomerPage page;
    ArrayList<Order> orderHistory = new ArrayList<>();
    public ArrayList<Food> cart = new ArrayList<>();
    double CustomerCharge;
    double OfferLimit;
    ArrayList<Double> Offers = new ArrayList<>();

    // ********************************************************************

    public CustomerPage getPage() {
        return this.page;
    }

    public void ShowLocalRests() {
        System.out.println("Your local restaurants are the following:");
        for (Restaurant restaurant : this.localRests) {
            System.out.println(restaurant.getName());
            System.out.println("ID: "+restaurant.getID());
            System.out.println("Rating: "+restaurant.calculateRating());
            System.out.println("FoodType: "+restaurant.getFoodType());
            System.out.println("location: "+restaurant.getLoc().getNum());
            System.out.println("------------------------------------------------");
        }
    }

    public void SearchRest(String restName) {
        ArrayList<Integer> ID = new ArrayList<>();
        ID.clear();
        for (Restaurant rest : localRests) {
            if (rest.getName().contains(restName))
                ID.add(rest.getID());
        }
        for (Integer restID : ID) {
            for (Restaurant rest : localRests) {
                if (rest.getID() == restID)
                    System.out.println(rest.getName() + " " + rest.getID());
            }
        }
    }

    public boolean SelectRest(int id) {
        if (id > this.localRests.size() && id < 0) {
            System.out.println("the restaurant with ID " + id + " is not in the menu!");
            System.out.println("please re-enter your request");
            return false;
        }

        this.localRests.get(id-1).getPage().previousPage = PageHandler.currPage;
        User.receiveMenu(this.localRests.get(id-1));
        PageHandler.changePage(this.localRests.get(id-1).getPage());
        return true;
    }

    public void DisplayOrderHis() {
        for (Order order : orderHistory) {
            order.showcart();
        }
    }

    public void addFoodToCart(Food food, int amount) {
        for (int i = 0; i < amount; i++)    
            cart.add(food);
        System.out.println("food(s) added to your cart successfully");
    }

    public void SelectOrder(int orderID) {
        for (Order order : orderHistory) {
            if (order.getID() == orderID) {
                order.showcart();
            }
        }
    }

    public void DisplayCart() {
        for (Food food : cart) {
            System.out.println("--------------------------");
            System.out.println(food.getName());
            System.out.println(food.getPrice());
        }
    }

    public void confirmOrder() {
        favRests.add(currRest);
        System.out.println("order confirmed");
    }

    public void setCustomerCharge(double charge) {
        this.CustomerCharge = charge;
    }

    public void displayCharge() {
        System.out.println("charge: " + CustomerCharge + "$");
    }

    public void setPage(CustomerPage page) {
        this.page = page;
    }

}