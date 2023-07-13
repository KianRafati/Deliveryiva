package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lib.Page.*;
import lib.Page.Authintication_Page.Authintication_Page;
import lib.Page.CustomerPage.CustomerPage;
import lib.Page.DeliveryPage.DeliveryPage;
import lib.Page.RestaurantAdminPage.RestaurantAdminPage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PageHandler {

    public static Page currPage;
    public static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    private static void init() {
        User.receiveDB();
        readFile();

        if (User.currUser == null)
            currPage = new StartPage();
        else {
            if (User.currUser instanceof RestaurantAdmin) {
                RestaurantAdminPage rstPage = new RestaurantAdminPage((RestaurantAdmin) User.currUser);
                ((RestaurantAdmin) User.currUser).setPage(rstPage);
                User.receiveRests(User.currUser.user_id);
                currPage = (Page) ((RestaurantAdmin) User.currUser).getPage();
            } else if (User.currUser instanceof Customer) {
                CustomerPage customerPage = new CustomerPage((Customer) User.currUser);
                ((Customer) User.currUser).setPage(customerPage);
                User.receiveLocalRests(((Customer) User.currUser).location.getNum());
                currPage = (Page) ((Customer) User.currUser).getPage();
            } else if (User.currUser instanceof Delivery) {
                DeliveryPage deliveryPage = new DeliveryPage((Delivery) User.currUser);
                ((Delivery) User.currUser).setPage(deliveryPage);
                currPage = ((Delivery) User.currUser).getPage();
            }
            startPage(currPage);
        }
    }

    private static void startPage(Page page) {    
        StartPage.loged_in = true;
        StartPage startPage = new StartPage();
        startPage.run("");
    }

    public static void changePage(Page newPage, KeyEvent event) {
        currPage = newPage;
    
        Scene scene = (Scene) event.getSource();
        Parent root = newPage.getRoot();
        primaryStage = (Stage) scene.getWindow();
        Image icon = new Image("E:\\Sharif University of Technology\\2th semester\\OOP\\Project\\Deliveryiva\\Deliveryiva\\lib\\Assets\\Deliveryiva_logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void changePage(Page newPage, ActionEvent event) {
        currPage = newPage;
    
        Scene scene = (Scene) event.getSource();
        Parent root = newPage.getRoot();
        primaryStage = (Stage) scene.getWindow();
        Image icon = new Image("E:\\Sharif University of Technology\\2th semester\\OOP\\Project\\Deliveryiva\\Deliveryiva\\lib\\Assets\\Deliveryiva_logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void changePage(Page newPage) {
        currPage = newPage;
    
        Scene scene = new Scene(newPage.getRoot());
        Image icon = new Image("E:\\Sharif University of Technology\\2th semester\\OOP\\Project\\Deliveryiva\\Deliveryiva\\lib\\Assets\\Deliveryiva_logo.png");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
    

    public static void showPage() {
        String input = "";
        init();
        currPage.run(input);
    }

    private static void terminate() {
        writeFile();
        primaryStage.close();
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

    public static void Logout(){
    
    }

}
