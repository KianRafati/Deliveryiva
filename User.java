import java.util.ArrayList;

public abstract class User {
    //********************************************************************

    String username;
    String password;
    String securityQ;
    User currUser = null;
    static ArrayList<User> users;
    
    //********************************************************************

    void CreateUser(String name, String password){
        
    }

    void LoginUser(String name,String username){

    }

    void Logout(){

    }

    void ChangePass(String oldPass, String newPass){

    }

    void ForgetPass(String answer){

    }

}
