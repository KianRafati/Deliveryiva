package lib.Page.OrderPage;

import lib.Page.Page;
import lib.Page.RestaurantPage.RestaurantPageCommands;
import src.*;

public class OrderPage extends Page {
    Order order;

    @Override
    public void run(String input) {

        int counter = 0;
        if (input.equals("back")) {
            PageHandler.changePage(this.previousPage);
            return;
        }

        if (User.currUser instanceof Customer) {
            System.out.println("***********Your order's page***********");

            for (RestaurantPageCommands command : RestaurantPageCommands.values()) {
                if (input.matches(command.content))
                    break;
                counter++;
            }

            switch (counter) {
                case 1:
                    order.DisplayStatus();
                    break;
                case 2:
                    order.ConfirmOrder();
                    break;
                case 3:
                    order.showEstTime();
                    break;
                case 4:
                    order.ShowPath();
                    break;
                default:
                    break;
            }

        } else if (User.currUser instanceof RestaurantAdmin) {
            System.out.println("***********Order " + this.order.getID() + "'s page***********");

        } else if (User.currUser instanceof Delivery) {
            System.out.println(
                    "***********Order " + this.order.getID() + " of " + this.order.getRest() + "'s page***********");

        }
    }

}
