package lib.Page.Authintication_Page;

public enum Authintication_Page_Commmands {
    ADD_REST_OWNER("add restaurant admin \\S+ \\S+"), //0
    ADD_CUSTOMER("add customer \\S+ \\S+"), //1
    ADD_DELIVERY("add delivery \\S+ \\S+"), //2
    LOGIN_REST_OWNER("login restaurant admin \\S+ \\S+"), //3
    LOGIN_CUSTOMER("login customer \\S+ \\S+"), //4
    LOGIN_DELIVERY("login delivery \\S+ \\S+"), //5
    LOGOUT("logout"); //6

    String content;
    Authintication_Page_Commmands(String content){
        this.content = content;
    }
}
