import java.util.ArrayList;

public class Page {
    private String name;
    private String[] commands;
    static ArrayList<Page> allPages = new ArrayList<>();

    Page(String name,String[] commands){
        this.name = name;
        this.commands = commands;
    }

    Page getPage(String name){
        for (Page page : allPages) 
            if(page.name.equals(name))
                return page;
        return null;
    }

    void initializer(){
        //*********************************************************StartPage********************************************************
        String[] StartPageCommands = new String[]{"add restaurant owner \\S+ \\S+",
                                                  "add customer \\S+ \\S+",
                                                  "login restaurant owner \\S+ \\S+",
                                                  "login customer \\S+ \\S+",
                                                  "logout",
                                                  "forgot password"};
        Page StartPage = new Page("StartPage", StartPageCommands);
        allPages.add(StartPage);
        //***************************************************************************************************************************

    }






}
