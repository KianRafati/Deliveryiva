package src;

import java.util.Scanner;

import lib.Page.*;
import lib.Page.Authintication_Page.Authintication_Page;

public class PageHandler {
    public static Page currPage;
    private static boolean run = true;
    private static Scanner scanner = new Scanner(System.in);

    private static void init() {
       
        User.receiveDB();

        if (User.currUser == null)
            currPage = StartPage0.getInstance();
        // else
        //     currPage = new StartPage1();
    }

    public static void changePage(Page newPage) {
        currPage = newPage;
    }

    public static void showPage() {
        String input = "";
        init();
        while (run) {
            if (input.equals("exit")) {
                System.out.println("Are you sure you want to quit the application? Y/N");
                input = scanner.nextLine();
                if(input.equals("Y")){
                    terminate();
                    return;
                }else if(input.equals("N"))
                    continue;
            }
            if(input.equals("logout")){
                System.out.println("You're about to logout are you sure? Y/N");
                input = scanner.nextLine();
                if(input.equals("Y")){
                    User.Logout();
                    PageHandler.changePage(Authintication_Page.getInstance());
                }else if(input.equals("N"))
                    continue;
            }
            currPage.run(input);
            input = scanner.nextLine();
        }
    }

    private static void terminate() {
        run = false;
        scanner.close();
    }
}
