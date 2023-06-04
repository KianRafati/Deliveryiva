package lib.Page.RestaurantPage;

public enum RestaurantPageCommands {
    EDIT_TYPE("edit food type to \\S+"), // 0
    EDIT_LOC("edit location to node \\d+"), // 1
    SHOW_LOC("show location"), // 2
    ADD_FOOD("add food \\S+ with price \\d+ to the menu"), // 3
    DEL_FOOD("remove food \\S+ from the menu"), // 4
    ACTIVE_FOOD("activate food \\S+"), // 5
    DEACT_FODD("deactivate food \\S+"), // 6
    SHOW_MENU("show menu"), // 7
    SELECT_FOOD("select food \\S+"), // 8 
    SHOW_ORDER_HIS("show order history"), // 9
    EDIT_ORDER("edit order with ID \\d+ with state \\S+"), // 10
    DISPLAY_OPEN_ORDERS("display currently open orders"); // 11

    String content;
    RestaurantPageCommands(String content){
        this.content = content;
    }
}
