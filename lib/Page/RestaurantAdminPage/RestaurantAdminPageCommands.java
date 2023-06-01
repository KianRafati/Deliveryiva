package lib.Page.RestaurantAdminPage;

public enum RestaurantAdminPageCommands {
    DISPLAY("display my restaurants"), // 0
    SELECT("select \\S+"), // 1
    ADD("add restaurant \\S+"), // 2
    DELETE("delete restaurant \\S+ with ID \\d+"); // 3

    String content;
    RestaurantAdminPageCommands(String content){
        this.content = content;
    }
}
