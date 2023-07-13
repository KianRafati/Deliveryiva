package lib.Page.CustomerPage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lib.Page.Page;
import lib.Page.RestaurantPage.RestaurantPageCommands;
import src.Customer;
import src.PageHandler;
import src.Restaurant;

public class CustomerPage extends Page {

    public CustomerPage(Customer customer){
        this.customer = customer;
    }

    private Customer customer;

    private Parent root;

    private int inputCount = 0;

    private int numOfComment = -1;

    public Page previousPage;

    public Customer getUser(){
        return this.customer;
    }

    @Override
    public void run(String input) {
        if(input.equals("back")){
            PageHandler.changePage(this.previousPage);
            return;
        }

    

        System.out.println("***********" + customer.username + "'s page***********");

        if (inputCount == 0) {
            int counter = 0;
            for (CustomerPageCommands command : CustomerPageCommands.values()) {
                if (input.matches(command.content))
                    break;
                counter++;
            }

            switch (counter) {
                case 0: // search rest
                    String[] temp0 = input.split("\\s");
                    customer.SearchRest(temp0[2]);
                    break;
                case 1: // select rest
                    String[] temp1 = input.split("\\s");
                    if(!customer.SelectRest(Integer.parseInt(temp1[2])))
                        return;
                    break;
                case 2://order his
                    customer.DisplayOrderHis();
                    break;
                case 3:// select order
                    String[] temp2 = input.split("\\s");
                    customer.SelectOrder(Integer.parseInt(temp2[2]));
                    break;
                case 4:// cart status
                    customer.DisplayCart();
                    break;
                case 5:// confirm order
                    customer.confirmOrder();
                    break;
                case 6://charge account
                    String[] temp3 = input.split("\\s");
                    customer.setCustomerCharge(Integer.parseInt(temp3[2]));
                    break;
                case 7:// display charge
                    customer.displayCharge();
                    break;
                case 8://show restaurants
                    customer.ShowLocalRests();
                default:
                    break;
            }
        } else if (inputCount == 1) { 
            inputCount = 0;
        }
    }

    @Override
    public FXMLLoader getLoader() {
        try {
            root = FXMLLoader.load(getClass().getResource("CustomerPageScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    
}
