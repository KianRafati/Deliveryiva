package lib.Page.RestaurantPage;

public class RestaurantPage {
    private RestaurantPage(){}
    private static RestaurantPage instance = null;
    public static RestaurantPage getInstance() {
        if (instance == null)
            instance = new RestaurantPage();
        return instance;
    }

    
}
