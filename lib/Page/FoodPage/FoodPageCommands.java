package lib.Page.FoodPage;

public enum FoodPageCommands {
    SHOW_DESCRIPTION("show description"), // 0
    EDIT_NAME("edit name \\S+"), // 1
    EDIT_PRICE("edit price \\d+(\\.\\d+)?"), // 2
    ADD_DISCOUNT("add discount \\d+(\\.\\d+)? for \\d+ days"), // 3
    DISPLAY_RATING("display rating"), // 4
    DISPLAY_COMMENTS("display comments"), // 5
    COMMENT("comment"), // 6
    RESPOND("respond to comment with ID \\d+"), // 7
    EDIT_RESPOND("edit respond with ID \\d+ to comment with ID \\d+"), // 8
    DISPLAY_REPLAIES("display replies to comment with ID \\d+"), // 9
    ADD_TO_CART("add to cart \\d+"), // 10
    EDIT_COMMENT("edit comment with ID \\d+"), // 11
    RATE("rate \\d+"), // 12
    EDIT_RATE("edit rating \\d+"); // 13


    String content;
    FoodPageCommands(String content){
        this.content = content;
    }
}