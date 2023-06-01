package lib.Page.Authintication_Page;

import lib.Page.Page;
import lib.Page.RestaurantAdminPage.RestaurantAdminPage;
import src.PageHandler;
import src.RestaurantAdmin;
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

        int counter = 0;
        for (Authintication_Page_Commmands command : Authintication_Page_Commmands.values()) {
            if (input.matches(command.content))
                break;
            counter++;
        }

        switch (counter) {
            case 0: // to restAdmin page
                String[] temp0 = input.split("\\s");
                User.CreateUser(temp0[3], temp0[4], 1);
                System.out.println("press Enter");
                PageHandler.changePage(RestaurantAdminPage.getInstance());
                break;
            case 1: // to customer page
                String[] temp1 = input.split("\\s");
                User.CreateUser(temp1[2], temp1[3], 2);
                break;
            case 2: // to delivery page
                String[] temp2 = input.split("\\s");
                User.CreateUser(temp2[2], temp2[3], 3);
                break;
            case 3:// to restAdmin page
                String[] temp3 = input.split("\\s");
                User.LoginUser(temp3[3], temp3[4]);
                break;
            case 4: // to customer page
                String[] temp4 = input.split("\\s");
                User.LoginUser(temp4[2], temp4[3]);
                break;
            case 5:// to delivery page
                String[] temp5 = input.split("\\s");
                User.LoginUser(temp5[2], temp5[3]);
                break;
            case 6:
                User.Logout();
                break;
            default:
                break;
        }
    }

}
