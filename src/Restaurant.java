package src;

import java.util.ArrayList;
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

    public void setPage(RestaurantPage page){
        this.page = page;
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
        System.out.println(this.name+"'s food type changed to "+ft);
        return true;
    }

    public boolean changeLoc(int nodeNum) {
        if(nodeNum >= Node.nodes.size() && nodeNum < 0){
            System.out.println("this place does not exist in our data base!");
            return false;
        }

        for (Node node : Node.occupiedNodes) {
            if(node.getNum() == nodeNum){
                if(node.getNodeHolder().getName().equals(this.getName())){
                    System.out.println("this is your current location!");
                    return false;
                }
                else{
                    System.out.println("you cannot change your restaurant's location here, it is currently occupied by: "+node.getNodeHolder().getName());
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
        System.out.println("Located on Node #"+this.location.getNum());
    }

    public boolean addFood(String foodName, double price) {
        for (Food food : this.menu) {
            if(food.getName().equals(foodName)){
                System.out.println("a food with this name already exists!");
                return false;
            }
        }

        if(price < DeliveryivaSettings.DeliveryivaMin){
            System.out.println("price cannot be set to this value!");
            return false;
        }

        Food food = new Food(foodName,price,this.menu.size()+1);
        this.menu.add(food);
        System.out.println("food successfully added to restaurant's menu");
        return true;
    }

    public boolean delFood(String foodName) {
        for (Food food : this.menu) {
            if(food.getName().equals(foodName)){
                System.out.println("Are you sure you want to delete this item from the menu? Y/N");
                    return true;
            }
        }     
        
        System.out.println("There's no item with "+foodName+" name in the menu!");
        return false;
    }

    public void delFoodSure(String foodName) {
        for (Food food : this.menu) {
            if(food.getName().equals(foodName)){
                this.menu.remove(food);
                food = null;
                return;
            }
        }    
    }




}
