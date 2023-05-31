package lib.Page;

import java.util.Scanner;

import lib.Page.Authintication_Page.Authintication_Page;
import src.PageHandler;

public class StartPage0 extends Page {
    private static StartPage0 instance = null;
    private static Scanner scanner = new Scanner(System.in);
    private boolean run = true;
    private StartPage0(){}
    
    public static StartPage0 getInstance() {
        if (instance == null)
            instance = new StartPage0();
        return instance;
    }

    @Override
    public void run() {
        while(run){
            System.out.println("Choose your user type:");
            System.out.println("1. Customer");
            System.out.println("2. Restaurant admin");
            System.out.println("3. Delivery");
            int type = 0;
            try {
                type = scanner.nextInt();
                switch (type) {
                    case 1:
                        PageHandler.changePage(Authintication_Page.getInstance());
                        break;
                    case 2:
                        PageHandler.changePage(Authintication_Page.getInstance());
                        break;
                    case 3:
                        PageHandler.changePage(Authintication_Page.getInstance());
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 3!");
                        break;
                }
                run = false;
                scanner.close();
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 3!");
            }
        }
    }
}
