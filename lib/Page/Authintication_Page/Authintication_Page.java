package lib.Page.Authintication_Page;

import lib.Page.Page;
import lib.Page.CustomerPage.CustomerPage;
import lib.Page.DeliveryPage.DeliveryPage;
import lib.Page.RestaurantAdminPage.RestaurantAdminPage;
import src.*;

public class Authintication_Page extends Page {

    private static Authintication_Page instance = null;

    private Authintication_Page() {
    }

    public static Authintication_Page getInstance() {
        if (instance == null)
            instance = new Authintication_Page();
        return instance;
    }

    @Override
    public void run(String input) {

        System.out.println("***********Authentication page***********");

        int counter = 0;
        for (Authintication_Page_Commmands command : Authintication_Page_Commmands.values()) {
            if (input.matches(command.content))
                break;
            counter++;
        }

        try {
            switch (counter) { //TODO handling wrong class casting
                case 0: // create restAdmin
                    String[] temp0 = input.split("\\s");
                    if (User.CreateUser(temp0[3], temp0[4], 1)) {
                        System.out.println("press Enter");
                        RestaurantAdminPage RestAdminPage = new RestaurantAdminPage((RestaurantAdmin) User.currUser);
                        RestAdminPage.previousPage = PageHandler.currPage;
                        PageHandler.changePage(RestAdminPage);
                        RestAdminPage.getUser().setPage(RestAdminPage);
                    } else
                        return;

                    break;
                case 1: // create customer
                    String[] temp1 = input.split("\\s");
                    if (User.CreateUser(temp1[2], temp1[3], 2)) {
                        System.out.println("press Enter");
                        CustomerPage customerPage = new CustomerPage((Customer) User.currUser);
                        customerPage.previousPage = PageHandler.currPage;
                        PageHandler.changePage(customerPage);
                        customerPage.getUser().setPage(customerPage);
                    } else
                        return;

                    break;
                case 2: // create delivery
                    String[] temp2 = input.split("\\s");
                    if (User.CreateUser(temp2[2], temp2[3], 3)) {
                        System.out.println("press Enter");
                        DeliveryPage deliveryPage = new DeliveryPage((Delivery) User.currUser);
                        deliveryPage.previousPage = PageHandler.currPage;
                        PageHandler.changePage(deliveryPage);
                        deliveryPage.getUser().setPage(deliveryPage);
                    } else
                        return;

                    break;
                case 3:// login restAdmin
                    String[] temp3 = input.split("\\s");
                    if (User.LoginUser(temp3[3], temp3[4])) {
                        System.out.println("press Enter");
                        RestaurantAdminPage RestAdminPage = new RestaurantAdminPage((RestaurantAdmin) User.currUser);
                        User.receiveRests(((RestaurantAdmin) User.currUser).user_id);
                        RestAdminPage.previousPage = PageHandler.currPage;
                        PageHandler.changePage(RestAdminPage);
                        RestAdminPage.getUser().setPage(RestAdminPage);
                    }
                    break;
                case 4: // login customer
                    String[] temp4 = input.split("\\s");
                    if (User.LoginUser(temp4[2], temp4[3])) {
                        System.out.println("press Enter");
                        CustomerPage customerPage = new CustomerPage((Customer) User.currUser);
                        User.receiveLocalRests(((Customer) User.currUser).location.getNum());
                        customerPage.previousPage = PageHandler.currPage;
                        PageHandler.changePage(customerPage);
                        customerPage.getUser().setPage(customerPage);
                    }
                    break;
                case 5:// login delivery
                    String[] temp5 = input.split("\\s");
                    if (User.LoginUser(temp5[2], temp5[3])) {
                        System.out.println("press Enter");
                        DeliveryPage deliveryPage = new DeliveryPage((Delivery) User.currUser);
                        deliveryPage.previousPage = PageHandler.currPage;
                        PageHandler.changePage(deliveryPage);
                        deliveryPage.getUser().setPage(deliveryPage);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error! please retry your request");
        }
    }

}
