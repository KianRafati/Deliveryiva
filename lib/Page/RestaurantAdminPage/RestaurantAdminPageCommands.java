package lib.Page.RestaurantAdminPage;

public enum RestaurantAdminPageCommands {
    DISPLAY("display my restaurants"), // 0
    SELECT("select \\S+"), // 1
    ADD("add restaurant \\S+ in node \\d+"), // 2
    DELETE("delete restaurant \\d+"); // 3

    String content;
    RestaurantAdminPageCommands(String content){
        this.content = content;
    }
}
