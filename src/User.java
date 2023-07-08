package src;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class User {
    // ********************************************************************

    public String username;
    public String password;
    public String securityQ;
    public int user_id;
    public static User currUser = null;
    static ArrayList<User> users = new ArrayList<>();
    static String SQLuser = "root";
    static String SQLpassword = "darkman.85";
    static String url = "jdbc:mysql://localhost:3306/deliveryivadb";
    static Statement statement = null;

    // ********************************************************************

    void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    private static void establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, SQLuser, SQLpassword);
        statement = connection.createStatement();
    }

    private static void terminateConnection() throws SQLException {
        statement.close();
    }

    static void receiveDB() {
        try {
            establishConnection();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                switch (resultSet.getInt("user_type")) {
                    case 1:
                        RestaurantAdmin restAdmin = new RestaurantAdmin(resultSet.getString("username"),
                                resultSet.getString("user_password"));
                        users.add(restAdmin);
                        break;
                    case 2:
                        Customer customer = new Customer(resultSet.getString("username"),
                                resultSet.getString("user_password"));
                        users.add(customer);
                        break;
                    case 3:
                        Delivery delivery = new Delivery(resultSet.getString("username"),
                                resultSet.getString("user_password"));
                        users.add(delivery);
                        break;
                    default:
                        break;
                    // throw new SQLException();
                }
            }
            terminateConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void receiveRestDB() {
        try {
            establishConnection();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM restaurants;");
            while (resultSet.next()) {
                Node node = new Node();
                node.setNumber(Integer.parseInt(resultSet.getString("location")));
                Restaurant restaurant = new Restaurant(resultSet.getString("restaurant_name"), node,
                        Integer.parseInt(resultSet.getString("restaurant_id")));
                DeliveryivaSettings.getInstance().restaurants.add(restaurant);
            }
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean CreateUser(String name, String password, int type)
            throws ClassNotFoundException, SQLException {
        for (User user : users) {
            if (user.username.equals(name)) {
                System.out.println("The entered username must be unique");
                return false;
            }
        }
        establishConnection();
        switch (type) {
            case 1:
                User user1 = new RestaurantAdmin(name, password);
                user1.user_id = users.size();
                User.users.add(user1);
                String DBop1 = "INSERT INTO users(username,user_password,user_type) VALUES(\"" + user1.username
                        + "\",\"" + user1.password + "\"," + 1 + ");";
                statement.execute(DBop1);
                currUser = user1;
                break;
            case 2:
                User user2 = new Customer(name, password);
                user2.user_id = users.size();
                User.users.add(user2);
                String DBop2 = "INSERT INTO users(username,user_password,user_type) VALUES(\"" + user2.username
                        + "\",\"" + user2.password + "\"," + 2 + ");";
                statement.execute(DBop2);
                currUser = user2;
                break;
            case 3:
                User user3 = new Delivery(name, password);
                user3.user_id = users.size();
                User.users.add(user3);
                String DBop3 = "INSERT INTO users(username,user_password,user_type) VALUES(\"" + user3.username
                        + "\",\"" + user3.password + "\"," + 2 + ");";
                statement.execute(DBop3);
                currUser = user3;
                break;
            default:
                System.out.println("INVALID USER TYPE ERROR!");
                return false;
        }

        System.out.println("Registered successfully!");
        terminateConnection();
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
