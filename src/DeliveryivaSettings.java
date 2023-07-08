package src;

import java.util.ArrayList;

public class DeliveryivaSettings {
    private static DeliveryivaSettings instance = null;

    private DeliveryivaSettings() {}

    public static DeliveryivaSettings getInstance() {
        if (instance == null)
            instance = new DeliveryivaSettings();
        return instance;
    }

    public static double DeliveryivaMin = 1000;
    public ArrayList<Restaurant> restaurants = new ArrayList<>();

}
