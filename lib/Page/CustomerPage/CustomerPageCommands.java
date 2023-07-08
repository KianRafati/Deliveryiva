package lib.Page.CustomerPage;

public enum CustomerPageCommands {
    SEARCH_REST("search restaurant \\S+"), // 0
    SELECT_REST("select restaurant \\d+"), // 1
    ACCESS_ORDER_HIS("access order history"), // 2
    SELECT_ORDER("select order \\d+"), // 3
    DISPLAY_CART_STATUS("display cart status"), // 4
    CONFIRM_ORDER("confirm order"), // 5
    CHARGE_ACCOUNT("charge account \\d+"), // 6
    DISPLAY_ACCOUNT_CHARGE("display account charge"), // 7
    DISPLAY_RESTS("show restaurants");// 8


    String content;
    CustomerPageCommands(String content){
        this.content = content;
    }
    
}
