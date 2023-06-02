package lib.Page.RestaurantAdminPage;

import lib.Page.Page;
import src.Restaurant;
import src.RestaurantAdmin;
import src.User;

public class RestaurantAdminPage extends Page {

    private static RestaurantAdminPage instance = null;

    private RestaurantAdminPage() {
    }

    public static RestaurantAdminPage getInstance() {
        if (instance == null)
            instance = new RestaurantAdminPage();
        return instance;
    }

    private int inputCount = 0;

    @Override
    public void run(String input) {
        System.out.println("***********Restaurant admin page***********");

        if (inputCount == 0) {
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
                    addRest(temp2[2]);
                    break;
                case 3:// del rest
                    String[] temp3 = input.split("\\s");
                    deleteRest(temp3[2], Integer.valueOf(temp3[5]));
                    break;
                default:
                    break;
            }
        } else if (inputCount == 1) { // gets the ID to select the rest
            int enteredID = Integer.valueOf(input);
            selectRest(enteredID);
            inputCount = 0;
        }

    }

    private void addRest(String name) {

    }

    private void deleteRest(String name, int id) {

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
            if (restName.equals(restaurant.getName()))
                rcount++;
            
        } else {
            showRestWithSameName(restName);
            System.out.println("Please enter ID of your restaurant");
            inputCount = 1;
            return; // returns to PageHandler() for user input
        }
    }

    private void selectRest(int id){
        for (Restaurant restaurant : ((RestaurantAdmin) User.currUser).getRests()) {
            if(restaurant.getID() == id){
                ((RestaurantAdmin) User.currUser).currRestaurant = restaurant;
                
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

}
