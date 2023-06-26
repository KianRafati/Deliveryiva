package src;

import java.util.ArrayList;

public abstract class User {
    // ********************************************************************

    public String username;
    public String password;
    public String securityQ;
    public static User currUser = null;
    static ArrayList<User> users = new ArrayList<>();

    // ********************************************************************

    public static boolean CreateUser(String name, String password, int type) {
        for (User user : users) {
            if (user.username.equals(name)) {
                System.out.println("The entered username must be unique");
                return false;
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
                return false;
        }
        System.out.println("Registered successfully!");
        return true;
    }

    public static boolean LoginUser(String name, String password) {
        for (User user : users) {
            if (user.username.equals(name)) {
                if (user.password.equals(password)) {
                    currUser = user;
                    System.out.println("Logged in successfully!");
                    return true;
                }
                System.out.println("Password does not match!");
                return false;
            }
        }
        System.out.println("User does not exist, please enter a valid username.");
        return false;
    }

    public static void Logout() {
        if (currUser == null) {
            System.out.println("Please log in first!");
        }
        currUser = null;
        System.out.println("Logged out successfully");
        return;
    }

    void ChangePass(String oldPass, String newPass) {

    }

    void ForgetPass(String answer) {

    }

}
