package src;
import java.util.ArrayList;

public abstract class User {
    //********************************************************************

    String username;
    String password;
    String securityQ;
    static User currUser = null;
    static ArrayList<User> users;
    
    //********************************************************************

    static void CreateUser(String name, String password ,int type){
        for (User user : users) {
            if(user.username.equals(name)){
                System.out.println("The entered username must be unique");
                return;
            }
        }
        switch (type) {
            case 1:
                User user1 = new RestaurantAdmin(name, password);
                User.users.add(user1);
                currUser = user1;
                break;
            case 2:
                User user2 = new Customer(name, password);
                User.users.add(user2);
                currUser = user2;
                break;
            case 3:
                User user3 = new Delivery(name, password);
                User.users.add(user3);
                currUser = user3;
                break;
            default:
                System.out.println("INVALID USER TYPE ERROR!");
                return;
        }
        System.out.println("Registered successfully!");
    }

    static void LoginUser(String name,String password){
        for (User user : users) {
            if(user.username.equals(name)){
                if(user.password.equals(password)){
                    currUser = user;
                    System.out.println("Logged in successfully!");
                    return;
                }
                System.out.println("Password does not match!");
                return;
            }
        }
        System.out.println("User does not exist, please enter a valid username.");
    }

    void Logout(){
        if(currUser == null){
            System.out.println("Please log in first!");
        }
        currUser = null;
        return;
    }

    void ChangePass(String oldPass, String newPass){

    }

    void ForgetPass(String answer){

    }

}
