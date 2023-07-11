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

    public final double DELIVERYIVA_MIN_FOOD_PRICE = 1.00;
    public final int DELIVERYIVA_LOCAL_RANGE = 50;


}
