package src;

public class Rating {
    int ID;
    int amount;
    Customer customer;
    Food food;
    Restaurant restaurant;

    Rating(int ID, int amount, Customer customer, Food food) {
        this.ID = ID;
        this.amount = amount;
        this.customer = customer;
        this.food = food;
    }

    Rating(int ID, int amount, Customer customer, Restaurant restaurant) {
        this.ID = ID;
        this.amount = amount;
        this.customer = customer;
        this.restaurant = restaurant;
    }
}
