package lib.Page.Authintication_Page;

import lib.Page.Page;
import lib.Page.CustomerPage.CustomerPage;
import lib.Page.DeliveryPage.DeliveryPage;
import lib.Page.RestaurantAdminPage.RestaurantAdminPage;
import src.PageHandler;
import src.User;

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

        switch (counter) {
            case 0: // to restAdmin page
                String[] temp0 = input.split("\\s");
                if (User.CreateUser(temp0[3], temp0[4], 1)) {
                    System.out.println("press Enter");
                    RestaurantAdminPage.getInstance().previousPage = PageHandler.currPage;
                    PageHandler.changePage(RestaurantAdminPage.getInstance());
                } else
                    return;

                break;
            case 1: // to customer page
                String[] temp1 = input.split("\\s");
                if (User.CreateUser(temp1[2], temp1[3], 2)) {
                    System.out.println("press Enter");
                    CustomerPage.getInstance().previousPage = PageHandler.currPage;
                    PageHandler.changePage(CustomerPage.getInstance());
                } else
                    return;

                break;
            case 2: // to delivery page
                String[] temp2 = input.split("\\s");
                if (User.CreateUser(temp2[2], temp2[3], 3)) {
                    System.out.println("press Enter");
                    DeliveryPage.getInstance().previousPage = PageHandler.currPage;
                    PageHandler.changePage(DeliveryPage.getInstance());
                } else
                    return;

                break;
            case 3:// to restAdmin page
                String[] temp3 = input.split("\\s");
                if(User.LoginUser(temp3[3], temp3[4])){
                    System.out.println("press Enter");
                    RestaurantAdminPage.getInstance().previousPage = PageHandler.currPage;
                    PageHandler.changePage(RestaurantAdminPage.getInstance());
                }
                break;
            case 4: // to customer page
                String[] temp4 = input.split("\\s");
                if(User.LoginUser(temp4[3], temp4[4])){
                    System.out.println("press Enter");
                    CustomerPage.getInstance().previousPage = PageHandler.currPage;
                    PageHandler.changePage(CustomerPage.getInstance());
                }
                break;
            case 5:// to delivery page
                String[] temp5 = input.split("\\s");
                if(User.LoginUser(temp5[3], temp5[4])){
                    System.out.println("press Enter");
                    DeliveryPage.getInstance().previousPage = PageHandler.currPage;
                    PageHandler.changePage(DeliveryPage.getInstance());
                }
                break;
            default:
                break;
        }
    }

}
