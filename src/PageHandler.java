package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import lib.Page.*;
import lib.Page.Authintication_Page.Authintication_Page;
import lib.Page.CustomerPage.CustomerPage;
import lib.Page.DeliveryPage.DeliveryPage;
import lib.Page.RestaurantAdminPage.RestaurantAdminPage;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageHandler {
    public static Page currPage;
    private static boolean run = true;
    public static Scanner scanner = new Scanner(System.in);
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    private static void init() {
        User.receiveDB();
        readFile();

        if (User.currUser == null)
            currPage = StartPage.getInstance();
        else {
            if (User.currUser instanceof RestaurantAdmin) {
                RestaurantAdminPage rstPage = new RestaurantAdminPage((RestaurantAdmin) User.currUser);
                ((RestaurantAdmin) User.currUser).setPage(rstPage);
                User.receiveRests(User.currUser.user_id);
                currPage = ((RestaurantAdmin) User.currUser).getPage();
            } else if (User.currUser instanceof Customer) {
                CustomerPage customerPage = new CustomerPage((Customer) User.currUser);
                ((Customer) User.currUser).setPage(customerPage);
                User.receiveLocalRests(((Customer) User.currUser).location.getNum());
                currPage = ((Customer) User.currUser).getPage();
            } else if (User.currUser instanceof Delivery) {
                DeliveryPage deliveryPage = new DeliveryPage((Delivery) User.currUser);
                ((Delivery) User.currUser).setPage(deliveryPage);
                currPage = ((Delivery) User.currUser).getPage();
            }
        }
    }

    public static void changePage(Page newPage) {
        currPage = newPage;
        // primaryStage.hide();

        // // Get the root element of the new page's scene
        // Parent root = newPage.getRoot();

        // // Create a new scene with the updated root element
        // Scene scene = new Scene(root);

        // // Set the new scene on the primary stage
        // primaryStage.setScene(scene);

        // // Show the primary stage with the new scene
        // primaryStage.show();
    }

    public static void showPage() {
        String input = "";
        init();
        while (run) {
            if (input.equals("exit")) {
                System.out.println("Are you sure you want to quit the application? Y/N");
                input = scanner.nextLine();
                if (input.equals("Y")) {
                    terminate();
                    return;
                } else if (input.equals("N"))
                    continue;
            }
            if (input.equals("logout")) {
                System.out.println("You're about to logout are you sure? Y/N");
                input = scanner.nextLine();
                if (input.equals("Y")) {
                    User.Logout();
                    PageHandler.changePage(Authintication_Page.getInstance());
                } else if (input.equals("N"))
                    continue;
            }
            currPage.run(input);
            input = scanner.nextLine();
        }
    }

    private static void terminate() {
        writeFile();
        run = false;
        scanner.close();
    }

    private static void writeFile() {
        try {
            File file = new File("data.txt");
            FileWriter fWriter = new FileWriter(file);

            if (User.currUser == null)
                fWriter.append("null");
            else
                fWriter.append(User.currUser.username);
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try {
            File file = new File("data.txt");
            FileReader fReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fReader);
            String input = bufferedReader.readLine();
            for (User user : User.users)
                if (user.username.equals(input)) {
                    User.currUser = user;
                    break;
                }
            fReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
