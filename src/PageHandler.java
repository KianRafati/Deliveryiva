package src;

import lib.Page.*;
import lib.Page.StartPage1.StartPage1;

public class PageHandler {
    private static Page currPage;

    private static void init() {
        if (User.currUser == null)
            currPage = StartPage0.getInstance();
        else
            currPage = new StartPage1();
    }

    public static void changePage(Page newPage) {
        currPage = newPage;
    }

    public static void showPage() {
        init();
        if (currPage == null) {
            terminate();
            return;
        }
        currPage.run();
    }

    private static void terminate() {

    }
}
