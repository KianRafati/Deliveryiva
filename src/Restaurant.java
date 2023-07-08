package src;

import java.util.ArrayList;

import lib.Page.Page;
import lib.Page.FoodPage.FoodPage;
import lib.Page.RestaurantPage.RestaurantPage;

public class Restaurant {
    // ==============================================================================

    private String name;
    private int ID;
    private Node location;
    public RestaurantPage page;
    private String foodType = null;
    private ArrayList<Food> menu = new ArrayList<>();
    private ArrayList<Order> OrderHistory = new ArrayList<>();
    private ArrayList<Order> OpenOrders = new ArrayList<>();
    private boolean order = false;
    private RestaurantAdmin owner;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Rating> ratings = new ArrayList<>();

    // ==============================================================================
    public Restaurant(String name, Node location, int ID) {
        this.name = name;
        this.location = location;
        this.ID = ID;
    }

    public String getFoodType() {
        return this.foodType;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public Node getLoc() {
        return this.location;
    }

    public void setPage(RestaurantPage page) {
        this.page = page;
    }

    public RestaurantPage getPage(){
        return this.page;
    }
    
    public boolean changeFT(String ft) {
        switch (ft) {
            case "FastFood":
                this.foodType = "FastFood";
                break;
            case "PersianFood":
                this.foodType = "PersianFood";
                break;
            case "Convenience":
                this.foodType = "Convenience";
                break;
            case "PetSupplies":
                this.foodType = "Pet Supplies";
                break;
            case "PrescriptionAndPharmaceutical":
                this.foodType = "Prescription and Pharmaceutical";
                break;
            case "Grocery":
                this.foodType = "Grocery";
                break;
            default:
                System.out.println("Please choose a valid category:");
                System.out.println("1. Fast food");
                System.out.println("2. Persian food");
                System.out.println("3. Convenience");
                System.out.println("4. Pet Supplies");
                System.out.println("5. Prescription and Pharmaceutical");
                System.out.println("6. Grocery");
                return false;
        }
        System.out.println(this.name + "'s food type changed to " + ft);
        return true;
    }

    public boolean changeLoc(int nodeNum) {
        if (nodeNum >= Node.nodes.size() && nodeNum < 0) {
            System.out.println("this place does not exist in our data base!");
            return false;
        }

        for (Node node : Node.occupiedNodes) {
            if (node.getNum() == nodeNum) {
                if (node.getNodeHolder().getName().equals(this.getName())) {
                    System.out.println("this is your current location!");
                    return false;
                } else {
                    System.out
                            .println("you cannot change your restaurant's location here, it is currently occupied by: "
                                    + node.getNodeHolder().getName());
                    return false;
                }
            }
        }

        Node.occupiedNodes.remove(this.location);
        this.location = Node.nodes.get(nodeNum);
        this.location.setNodeHolder(this);
        Node.occupiedNodes.add(this.location);
        System.out.println("Location changed successfully!");

        return true;
    }

    public void showLoc() {
        System.out.println("Located on Node #" + this.location.getNum());
    }

    public boolean addFood(String foodName, double price) {
        for (Food food : this.menu) {
            if (food.getName().equals(foodName)) {
                System.out.println("a food with this name already exists!");
                return false;
            }
        }

        if (price < DeliveryivaSettings.DeliveryivaMin) {
            System.out.println("price cannot be set to this value!");
            return false;
        }

        Food food = new Food(foodName, price, this.menu.size() + 1);
        FoodPage page = new FoodPage(food, this);
        food.setPage(page);
        this.menu.add(food);
        System.out.println("food successfully added to restaurant's menu");
        return true;
    }

    public boolean delFood(String foodName) {
        for (Food food : this.menu) {
            if (food.getName().equals(foodName)) {
                System.out.println("Are you sure you want to delete this item from the menu? Y/N");
                return true;
            }
        }

        System.out.println("There's no item with " + foodName + " name in the menu!");
        return false;
    }

    public void delFoodSure(String foodName) {
        for (Food food : this.menu) {
            if (food.getName().equals(foodName)) {
                this.menu.remove(food);
                food = null;
                return;
            }
        }
    }

    public boolean actFood(String foodName) {
        for (Food food : this.menu)
            if (food.getName().equals(foodName)) {
                food.activeFood();
                System.out.println(food.getName() + " has been activated and is ready for orders!");
                return true;
            }
        System.out.println("the food " + foodName + " is not in the menu!");
        System.out.println("please re-enter your request");
        return false;
    }

    public boolean deactFood(String foodName) {
        for (Food food : this.menu)
            if (food.getName().equals(foodName)) {
                food.activeFood();
                System.out.println(food.getName() + " has been deactivated and is out of order!");
                return true;
            }
        System.out.println("the food " + foodName + " is not in the menu!");
        System.out.println("please re-enter your request");
        return false;
    }

    public void showMenu() {
        System.out.println(this.name + "'s menu is:");
        if (!this.menu.isEmpty())
            for (Food food : this.menu) {
                System.out.println(food.getName());
                System.out.println(food.getPrice() + "$");
                System.out.println("-------------------------------");
            }
        else {
            System.out.println("EMPTY");
            System.out.println("-------------------------------");
        }
    }

    public boolean selectFood(String foodName) {
        for (Food food : this.menu)
            if (food.getName().equals(foodName)) {
                food.getPage().previousPage = PageHandler.currPage;
                PageHandler.changePage(food.getPage());
                return true;
            }
        System.out.println("the food " + foodName + " is not in the menu!");
        System.out.println("please re-enter your request");
        return false;
    }

    public void showOrderHis() {
        System.out.println(this.name + "'s Order History:");
        if (this.OrderHistory.isEmpty())
            System.out.println("EMPTY");
        else {
            for (Order order : OrderHistory) {
                System.out.println("===========================================");
                System.out.println("Order ID: " + order.getID());
                System.out.println("to Customer " + order.getCustomer().username);
                System.out.println("cart:");
                order.showcart();
            }
        }
    }

    public void showOpenOrders() {
        System.out.println(this.name + "'s Open Orders:");
        if (this.OrderHistory.isEmpty())
            System.out.println("EMPTY");
        else {
            for (Order order : OpenOrders) {
                System.out.println("===========================================");
                System.out.println("Order ID: " + order.getID());
                System.out.println("to Customer " + order.getCustomer().username);
                System.out.println("cart:");
                order.showcart();
            }
        }
    }

    public boolean editOrder(int ID, String status) {

        int flag = 0;
        for (String stat : Order.statuses)
            if (stat.equals(status))
                flag++;

        if (flag == 0) {
            System.out.println("Invalid Status");
            System.out.println("Please re-enter you request");
            return false;
        }

        for (Order order : OpenOrders)
            if (order.getID() == ID) {
                order.setStatus(status);
                System.out.println("Order with ID " + order.getID() + "'s status edited successfully");
                return true;
            }

        System.out.println("Order with ID " + ID + " cannot be found");
        System.out.println("Please re-enter you request");
        return false;
    }

    public ArrayList<Food> getMenu() {
        return this.menu;
    }

    public ArrayList<Rating> getRatings() {
        return this.ratings;
    }

    public void DisplayRatings() {
        System.out.println(this.name + "'s ratings:");

        if (!this.ratings.isEmpty())
            for (Rating rate : this.ratings)
                System.out.println(rate.ID + ". " + rate.amount);
        else
            System.out.println("EMPTY");

    }

    public ArrayList<Comment> getComments(){
        return this.comments;
    }

    public void setComment(User user,String content) {
        Comment comment = new Comment(this.comments.size(), user, content, this);
        this.comments.add(comment);
        System.out.println("comment set successfully");
        System.out.println("thank you for your feedback");
    }

    public void DisplayComments(){
        if(this.comments.isEmpty()){
            System.out.println(this.name+" has no comments!");
            return;
        }

        System.out.println(this.name+"'s comments:");
        for (Comment comment : this.comments) {
            comment.displayComment();
        }

    }

    public boolean editComment(int commentID, User user, String content) {
        Comment comment = this.comments.get(commentID-1);
        if(!comment.commenter.equals(user)){
            System.out.println("This is not your comment!");
            System.out.println("please re-enter your request");
            return false;
        }

        comment.setContent(content);
        System.out.println("Comment updated successfully");
        return true;
    }

    public void setResond(int commentID,User user, String input) {
        this.comments.get(commentID-1).setReply(user, input);
        System.out.println("reply set successfully");
    }

    public boolean editResond(int commentID,int replyID, User user, String input) {
        Comment reply = this.comments.get(commentID-1).replies.get(replyID-1);
        if(!reply.commenter.equals(user)){
            System.out.println("This is not your comment!");
            System.out.println("please re-enter your request");
            return false;
        }

        reply.setContent(input);
        System.out.println("comment edited successfully");
        return true;

    }

    public void setRate(Customer customer,int amount){
        for (Rating rating : this.ratings) {
            if(customer.equals(rating.customer)){
                System.out.println("you can not use this command you've rated this restaurant");
                return;
            }
        }
        Rating rating = new Rating(this.ratings.size(), amount, customer, this);
        this.ratings.add(rating);
        System.out.println("Reply set successfully");
    }

    public boolean editRating(User user,int newAmount){
        for (Rating rating : this.ratings) {
            if(rating.customer.equals(user)){
                rating.amount = newAmount;
                System.out.println("rating edited successfully");
                return true;
            }
        }

        System.out.println("you have not rated this restaurant!");
        return false;
    }
}
