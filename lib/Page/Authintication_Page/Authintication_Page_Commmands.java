package lib.Page.Authintication_Page;

public enum Authintication_Page_Commmands {
    ADD_REST_OWNER("restaurant admin add  \\S+ \\S+"), //0
    ADD_CUSTOMER("customer add \\S+ \\S+"), //1
    ADD_DELIVERY("delivery add \\S+ \\S+"), //2
    LOGIN_REST_OWNER("restaurant admin login \\S+ \\S+"), //3
    LOGIN_CUSTOMER("customer login \\S+ \\S+"), //4
    LOGIN_DELIVERY("delivery login \\S+ \\S+"); //5

    String content;
    Authintication_Page_Commmands(String content){
        this.content = content;
    }
}
