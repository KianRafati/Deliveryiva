package lib.Page.RestaurantPage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lib.Page.Page;
import src.Customer;
import src.PageHandler;
import src.Restaurant;
import src.RestaurantAdmin;
import src.User;

public class RestaurantPage implements Page {
    public Restaurant restaurant;
    private int inputCount = 0;
    private int commentID = 0;
    private int respondID = 0;
    public Page previousPage;
    String delfood = null;
    Parent root;

    public RestaurantPage(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run(String input) {
        
        if (input.equals("back")) {
            PageHandler.changePage(this.previousPage);
            return;
        }

        if (inputCount == 0) {
            int counter = 0;
            for (RestaurantPageCommands command : RestaurantPageCommands.values()) {
                if (input.matches(command.content))
                    break;
                counter++;
            }

            switch (counter) {
                case 0: // edit type
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp0 = input.split("\\s");
                        if (!restaurant.changeFT(temp0[4]))
                            return;
                    }
                    break;
                case 1: // edit loc
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp1 = input.split("\\s");
                        if (!restaurant.changeLoc(Integer.valueOf(temp1[4])))
                            return;
                    }
                    break;
                case 2: // show loc
                    restaurant.showLoc();
                    break;
                case 3:// add food
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp3 = input.split("\\s");
                        if (!restaurant.addFood(temp3[2], Double.valueOf(temp3[5])))
                            return;
                    }
                    break;
                case 4: // del food
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp4 = input.split("\\s");
                        delfood = temp4[2];
                        if (!restaurant.delFood(temp4[2]))
                            return;
                        else {
                            inputCount = 1;
                            return;
                        }
                    }
                    break;
                case 5: // act food
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp5 = input.split("\\s");
                        if (!restaurant.actFood(temp5[2]))
                            return;
                    }
                    break;
                case 6: // deact food
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp6 = input.split("\\s");
                        if (!restaurant.actFood(temp6[2]))
                            return;
                    }
                    break;
                case 7: // show menu
                    restaurant.showMenu();
                    break;
                case 8: // select food
                    String[] temp7 = input.split("\\s");
                    if (!restaurant.selectFood(temp7[2]))
                        return;
                    break;
                case 9: // order his
                    if (User.currUser instanceof RestaurantAdmin)
                        restaurant.showOrderHis();
                    break;
                case 10: // edit order state
                    if (User.currUser instanceof RestaurantAdmin) {
                        String[] temp10 = input.split("\\s");
                        if (!restaurant.editOrder(Integer.parseInt(temp10[4]), temp10[7]))
                            return;
                    }
                    break;
                case 11:// display open orders
                    if (User.currUser instanceof RestaurantAdmin)
                        restaurant.showOpenOrders();
                    break;
                case 12: // display comments
                    restaurant.DisplayComments();
                    break;
                case 13: // add comment
                    inputCount = 2;
                    return;
                case 14: // edit comment
                    String[] temp = input.split("\\s");
                    commentID = Integer.parseInt(temp[4]);
                    if (commentID < 0 || commentID > this.restaurant.getComments().size()) {
                        System.out.println("this comment does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    inputCount = 3;
                    break;
                case 15: // respond
                    String[] temp3 = input.split("\\s");
                    commentID = Integer.parseInt(temp3[5]);
                    if (commentID < 0 || commentID > this.restaurant.getComments().size()) {
                        System.out.println("this comment does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    inputCount = 4;
                    return;
                case 16: // edit respond
                    String[] temp4 = input.split("\\s");
                    commentID = Integer.parseInt(temp4[9]);
                    respondID = Integer.parseInt(temp4[5]);
                    if (commentID < 0 || commentID > this.restaurant.getComments().size()) {
                        System.out.println("this comment does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    if (respondID < 0 || respondID > this.restaurant.getComments().get(commentID - 1).replies.size()) {
                        System.out.println("this reply does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    inputCount = 5;
                    return;
                case 17: // display replies
                    String[] temp5 = input.split("\\s");
                    commentID = Integer.parseInt(temp5[6]);
                    this.restaurant.getComments().get(commentID - 1).displayReplies();
                    break;
                case 18: // display ratings
                    restaurant.DisplayRatings();
                    break;
                case 19: // rate
                    if (User.currUser instanceof Customer) {
                        String[] temp6 = input.split("\\s");
                        int amount = Integer.parseInt(temp6[1]);
                        restaurant.setRate((Customer) User.currUser, amount);
                    }
                    break;
                case 20: // edit rating
                    if (User.currUser instanceof Customer) {
                        String[] temp1 = input.split("\\s");
                        int amount = Integer.parseInt(temp1[2]);
                        restaurant.editRating(User.currUser, amount);
                    }
                    break;
                default:
                    break;
            }

        } else if (inputCount == 1) { // deletes the selected food
            inputCount = 0;
            if (input.equals("Y"))
                restaurant.delFoodSure(delfood);
            else if (input.equals("N"))
                return;
        } else if (inputCount == 2) { // sets comment
            inputCount = 0;
            restaurant.setComment(User.currUser, input);
        } else if (inputCount == 3) { // edits comment
            inputCount = 0;
            if (!restaurant.editComment(commentID, User.currUser, input))
                return;
        } else if (inputCount == 4) { // respond
            inputCount = 0;
            restaurant.setResond(commentID, User.currUser, input);
        } else if (inputCount == 5) {
            inputCount = 0;
            restaurant.editResond(commentID, respondID, User.currUser, input);
        }
    }

    @Override
    public Parent getRoot() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantPageScene.fxml"));
            root = loader.load();
            RestaurantPageSceneController controller = loader.getController();
            User.receiveMenu(restaurant);
            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }


}
