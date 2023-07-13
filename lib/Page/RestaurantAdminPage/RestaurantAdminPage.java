package lib.Page.RestaurantAdminPage;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lib.Page.Page;
import lib.Page.RestaurantPage.RestaurantPage;
import src.Node;
import src.PageHandler;
import src.Restaurant;
import src.RestaurantAdmin;
import src.User;

public class RestaurantAdminPage implements Page {
    int id;
    private RestaurantAdmin restAdmin;
    public Page previousPage;
    Parent root;

    public RestaurantAdminPage(RestaurantAdmin restAdmin) {
        this.restAdmin = restAdmin;
    }

    @Override
    public void run(String input) {

        if (input.equals("back")) {
            PageHandler.changePage(this.previousPage);
            return;
        }

        int counter = 0;
        for (RestaurantAdminPageCommands command : RestaurantAdminPageCommands.values()) {
            if (input.matches(command.content))
                break;
            counter++;
        }

        switch (counter) {
            case 0: // display rests
                showMyRests();
                break;
            case 1: // select rest
                String[] temp1 = input.split("\\s");
                selectRest(temp1[1]);
                break;
            case 2: // add rest
                String[] temp2 = input.split("\\s");
                addRest(temp2[2], Integer.valueOf(temp2[5]));
                break;
            case 3:// del rest
                String[] temp3 = input.split("\\s");
                id = Integer.valueOf(temp3[2]);
                AreYouSure();
                break;
            default:
                break;
        }

    }

    private void addRest(String name, int nodeNum) {

        for (Node node : Node.occupiedNodes) {
            if (node.getNum() == nodeNum) {
                System.out.println("You can't place a restaurant here! it is occupied by a another restaurant.");
                return;
            }
        }

        Node location = new Node();
        location.setNumber(nodeNum);
        Node.occupiedNodes.add(location);
        Restaurant restaurant = new Restaurant(name, location, User.receiveID("restaurant_id", "restaurants"),
                this.restAdmin.user_id);
        RestaurantPage page = new RestaurantPage(restaurant);
        restaurant.setPage(page);
        this.previousPage = this;
        ((RestaurantAdmin) User.currUser).getRests().add(restaurant);
        User.addSQLrow("restaurant", restaurant);
        System.out.println("Restaurant added successfully");
    }

    private void AreYouSure() {
        System.out.println(
                "Are you sure you want to delete this restaurant? (all orders and profit will be deleted) Y/N");
    }

    private void deleteRest(int id) {
        for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests()) {
            if (id == restaurant.getID()) {
                ((RestaurantAdmin) User.currUser).getRests().remove(restaurant);
                restaurant = null;
                System.out.println("Restaurant deleted successfully");
                return;
            }
        }
    }

    private void selectRest(String restName) {
        int rcount = 0;
        for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests())
            if (restName.equals(restaurant.getName()))
                rcount++;

        if (rcount == 0)
            System.out.println("There's no restaurant named " + restName);
        else if (rcount == 1) {
            for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests())
                if (restName.equals(restaurant.getName())) {
                    ((RestaurantAdmin) User.currUser).currRestaurant = restaurant;
                    PageHandler.changePage((Page) restaurant.page);
                    User.receiveMenu(restaurant);
                    System.out
                            .println("Selected restaurant " + restaurant.getName() + " with ID " + restaurant.getID());
                    return;
                }
        } else {
            showRestWithSameName(restName);
            System.out.println("Please enter ID of your restaurant");
            return; // returns to PageHandler() for user input
        }
    }

    private void selectRest(int id) {
        for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests()) {
            if (restaurant.getID() == id) {
                ((RestaurantAdmin) User.currUser).currRestaurant = restaurant;
                PageHandler.changePage((Page) restaurant.page);
                User.receiveMenu(restaurant);
                System.out.println("Selected restaurant " + restaurant.getName() + " with ID " + restaurant.getID());
                return;
            }
        }
    }

    private void showRestWithSameName(String string) {
        int counter = 1;
        for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests()) {
            System.out.println(counter + ". " + restaurant.getName() + " ID: " + restaurant.getID());
            counter++;
        }
    }

    private void showMyRests() {
        int counter = 1;
        System.out.println("Your current restaurants are listed below:");
        if (((RestaurantAdmin) User.currUser).getRests().isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests()) {
            System.out.println(counter + ". " + restaurant.getName() + " ID: " + restaurant.getID());
            counter++;
        }
    }

    public RestaurantAdmin getUser() {
        return this.restAdmin;
    }

    @Override
    public Parent getRoot() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantAdminPageScene.fxml"));
            root = loader.load();
            RestaurantAdminPageController controller = loader.getController();
            User.receiveRests(restAdmin.user_id);
            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }


}
