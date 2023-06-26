package lib.Page.RestaurantPage;

import lib.Page.Page;
import src.PageHandler;
import src.Restaurant;

public class RestaurantPage extends Page {
    private Restaurant restaurant;
    private int inputCount = 0;

    public RestaurantPage(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run(String input) {
        String delfood = null;

        if(input.equals("back")){
            PageHandler.changePage(this.previousPage);
            return;
        }

        System.out.println("***********" + restaurant.getName() + "'s Restaurant page***********");

        if (inputCount == 0) {
            int counter = 0;
            for (RestaurantPageCommands command : RestaurantPageCommands.values()) {
                if (input.matches(command.content))
                    break;
                counter++;
            }

            switch (counter) {
                case 0: // edit type
                    String[] temp0 = input.split("\\s");
                    if (!restaurant.changeFT(temp0[4]))
                        return;
                    break;
                case 1: // edit loc
                    String[] temp1 = input.split("\\s");
                    if (!restaurant.changeLoc(Integer.valueOf(temp1[4])))
                        return;
                    break;
                case 2: // show loc
                    restaurant.showLoc();
                    break;
                case 3:// add food
                    String[] temp3 = input.split("\\s");
                    if (!restaurant.addFood(temp3[2], Double.valueOf(temp3[5])))
                        return;
                    break;
                case 4: // del food
                    String[] temp4 = input.split("\\s");
                    delfood = temp4[2];
                    if (!restaurant.delFood(temp4[2]))
                        return;
                    else {
                        inputCount = 1;
                        return;
                    }
                case 5: // act food
                    String[] temp5 = input.split("\\s");
                    if (!restaurant.actFood(temp5[2]))
                        return;
                    break;
                case 6: // deact food
                    String[] temp6 = input.split("\\s");
                    if (!restaurant.actFood(temp6[2]))
                        return;
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
                    restaurant.showOrderHis();
                    break;
                case 10: // edit order state
                    String[] temp10 = input.split("\\s");
                    if (!restaurant.editOrder(Integer.parseInt(temp10[4]), temp10[7]))
                        return;
                    break;
                case 11:// display open orders
                    restaurant.showOpenOrders();
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
        }

    }

}
