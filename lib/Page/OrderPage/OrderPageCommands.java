package lib.Page.OrderPage;

public enum OrderPageCommands {
    EDIT_ORDER("edit order"), // 0
    DISPLAY_CART_STATUS("display cart status"), // 1
    CONFIRM_ORDER("confirm my order"),// 2
    SHOW_TIME("show estimated delivery time"), // 3
    SHOW_PATH("show path"); // 4

    String content;
    OrderPageCommands(String content){
        this.content = content;
    }
}
