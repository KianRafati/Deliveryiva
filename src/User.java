package src;

import java.util.ArrayList;

import lib.Page.FoodPage.FoodPage;
import lib.Page.RestaurantPage.RestaurantPage;

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

    public static void establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, SQLuser, SQLpassword);
        statement = connection.createStatement();
    }

    public static void terminateConnection() throws SQLException {
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
                                resultSet.getString("user_password"),
                                Integer.parseInt(resultSet.getString("user_id")));
                        users.add(restAdmin);
                        break;
                    case 2:
                        Customer customer = new Customer(resultSet.getString("username"),
                                resultSet.getString("user_password"),
                                Integer.parseInt(resultSet.getString("user_id")));
                        Node node = new Node();
                        customer.location = node;
                        customer.location.setNumber(Integer.parseInt(resultSet.getString("location")));
                        users.add(customer);
                        break;
                    case 3:
                        Delivery delivery = new Delivery(resultSet.getString("username"),
                                resultSet.getString("user_password"),
                                Integer.parseInt(resultSet.getString("user_id")));
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

    private static User getUser(int id) {
        for (User user : users)
            if (id == user.user_id)
                return user;

        return null;
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
                User user1 = new RestaurantAdmin(name, password, users.size());
                User.users.add(user1);
                String DBop1 = "INSERT INTO users(username,user_password,user_type) VALUES(\"" + user1.username
                        + "\",\"" + user1.password + "\"," + 1 + ");";
                statement.execute(DBop1);
                currUser = user1;
                break;
            case 2:
                User user2 = new Customer(name, password, users.size());
                User.users.add(user2);
                String DBop2 = "INSERT INTO users(username,user_password,user_type) VALUES(\"" + user2.username
                        + "\",\"" + user2.password + "\"," + 2 + ");";
                statement.execute(DBop2);
                currUser = user2;
                break;
            case 3:
                User user3 = new Delivery(name, password, users.size());
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

    public boolean ChangePass(String oldPass, String newPass) {
        if (!oldPass.matches(this.password)) {
            System.out.println("Password incorrect");
            System.out.println("please re-enter your request");
            return false;
        }

        this.password = newPass;
        updateSQL("users", "password", "user_id = " + this.user_id, newPass);
        System.out.println("password changed successfully");
        return true;
    }

    void ForgetPass(String answer) {

    }

    public static void receiveRests(int owner_id) {
        try {
            establishConnection();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM restaurants WHERE owner_id = " + owner_id + ";");
            while (resultSet.next()) {
                Node node = new Node();
                node.setNumber(Integer.parseInt(resultSet.getString("location")));
                Restaurant restaurant = new Restaurant(
                        resultSet.getString("restaurant_name"),
                        node,
                        Integer.parseInt(resultSet.getString("restaurant_id")),
                        Integer.parseInt(resultSet.getString("owner_id")));
                // DeliveryivaSettings.getInstance().restaurants.add(restaurant);
                ((RestaurantAdmin) getUser(Integer.parseInt(resultSet.getString("owner_id"))))
                        .addRestaurant(restaurant);
                RestaurantPage page = new RestaurantPage(restaurant);
                restaurant.setPage(page);
            }
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void receiveLocalRests(int user_location) {
        try {
            establishConnection();
            int min = user_location - DeliveryivaSettings.getInstance().DELIVERYIVA_LOCAL_RANGE;
            int max = user_location + DeliveryivaSettings.getInstance().DELIVERYIVA_LOCAL_RANGE;
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM restaurants WHERE location > " + min + " AND location < " + max + ";");
            while (resultSet.next()) {
                Node node = new Node();
                node.setNumber(Integer.parseInt(resultSet.getString("location")));
                Restaurant restaurant = new Restaurant(
                        resultSet.getString("restaurant_name"),
                        node,
                        Integer.parseInt(resultSet.getString("restaurant_id")),
                        Integer.parseInt(resultSet.getString("owner_id")));
                ((Customer) User.currUser).localRests.add(restaurant);
                RestaurantPage page = new RestaurantPage(restaurant);
                restaurant.setPage(page);
            }
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void receiveComments(Food food) {
        try {
            establishConnection();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM comments WHERE food_id = "+food.getID()+";");
            while (resultSet.next()) {
                User user = getUser(Integer.parseInt(resultSet.getString("commenter_id")));
                Comment comment = new Comment(Integer.parseInt(resultSet.getString("comment_id")), user, resultSet.getString("content"), food);
                food.comments.add(comment);
                if(resultSet.getString("to_comment_id") != null)
                    comment.setTo(Integer.parseInt(resultSet.getString("to_comment_id")),food);
            }
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void receiveMenu(Restaurant restaurant) {
        try {
            establishConnection();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM foods WHERE restaurant_id = " + restaurant.getID() + " ;");
            while (resultSet.next()) {
                Food food = new Food(resultSet.getString("food_name"), Double.parseDouble(resultSet.getString("price")),
                        Integer.parseInt(resultSet.getString("food_id")), restaurant);
                restaurant.setMenu(food);
                FoodPage foodPage = new FoodPage(food, restaurant);
                food.setPage(foodPage);
            }
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static int receiveID(String column_name, String table_name) {
        try {
            establishConnection();
            ResultSet resultSet = statement
                    .executeQuery("SELECT MAX(" + column_name + ") AS maximum FROM " + table_name + ";");
            int max = 0;
            if (resultSet.next())
                max = resultSet.getInt("maximum");
            System.out.println(max);
            terminateConnection();
            return max;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void addSQLrow(String dataType, Object object) {
        switch (dataType) {
            case "restaurant":
                Restaurant restaurant = (Restaurant) object;
                try {
                    establishConnection();
                    String dbOp = "INSERT INTO restaurants(restaurant_name,owner_id,location) VALUES(\""
                            + restaurant.getName()
                            + "\",\"" + restaurant.getOwner().user_id + "\"," + restaurant.getLoc().getNum() + ");";
                    statement.execute(dbOp);
                    terminateConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "food":
                Food food = (Food) object;
                try {
                    establishConnection();
                    String dbOp = "INSERT INTO foods(food_name,price,restaurant_id) VALUES(\"" + food.getName()
                            + "\",\"" + food.getPrice() + "\"," + food.getRestaurant().getID() + ");";
                    statement.execute(dbOp);
                    terminateConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "comment01":
                Comment comment01 = (Comment) object;
                try {
                    establishConnection();
                    String dbOp = "INSERT INTO comments(commenter_id,content,food_id) VALUES(\"" + comment01.ID
                            + "\",\"" + comment01.content + "\"," + comment01.food.getID() + ");";
                    statement.execute(dbOp);
                    terminateConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "comment02":
                Comment comment02 = (Comment) object;
                try {
                    establishConnection();
                    String dbOp = "INSERT INTO comments(commenter_id,content,restaurant_id) VALUES(\"" + comment02.ID
                            + "\",\"" + comment02.content + "\"," + comment02.restaurant.getID() + ");";
                    statement.execute(dbOp);
                    terminateConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "comment11":
                Comment comment11 = (Comment) object;
                try {
                    establishConnection();
                    String dbOp = "INSERT INTO comments(commenter_id,content,food_id,to_comment_id) VALUES(\""
                            + comment11.ID
                            + "\",\"" + comment11.content + "\"," + comment11.food.getID() + "\","
                            + comment11.toComment.ID + ");";
                    statement.execute(dbOp);
                    terminateConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "comment12":
                Comment comment12 = (Comment) object;
                try {
                    establishConnection();
                    String dbOp = "INSERT INTO comments(commenter_id,content,restaurant_id,to_comment_id) VALUES(\""
                            + comment12.ID
                            + "\",\"" + comment12.content + "\"," + comment12.restaurant.getID() + "\","
                            + comment12.toComment.ID + ");";
                    statement.execute(dbOp);
                    terminateConnection();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public static void updateSQL(String table_name, String column_name, String condition, String updatedData) {
        try {
            establishConnection();
            String dbOp = "UPDATE " + table_name + " SET " + column_name + " = " + updatedData + " WHERE " + condition
                    + ";";
            statement.executeQuery(dbOp);
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSQLRow(String table_name, String condition) {
        try {
            establishConnection();
            String dbOp = "DELETE FROM " + table_name + " WHERE " + condition + ";";
            statement.executeQuery(dbOp);
            terminateConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



}