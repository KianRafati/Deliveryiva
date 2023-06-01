package lib.Page;

import lib.Page.Authintication_Page.Authintication_Page;
import src.PageHandler;

public class StartPage0 extends Page {
    private static StartPage0 instance = null;

    private StartPage0() {
    }

    public static StartPage0 getInstance() {
        if (instance == null)
            instance = new StartPage0();
        return instance;
    }

    @Override
    public void run(String input) {
        System.out.println("Welcome to Deliveryiva!");
        System.out.print("please press any button to start..");
        PageHandler.changePage(Authintication_Page.getInstance());
    }
}
