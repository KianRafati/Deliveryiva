package lib.Page;

import javafx.scene.Parent;
import lib.Page.Authintication_Page.Authintication_Page;
import src.PageHandler;

public class StartPage extends Page {
    private static StartPage instance = null;
    StartPageGUIHandler guiHandler = new StartPageGUIHandler();
    
    private StartPage() {}
    
    public static StartPage getInstance() {
        if (instance == null)
        instance = new StartPage();
        return instance;
    }
    
    @Override
    public void run(String input) {
        System.out.println("Welcome to Deliveryiva!");
        System.out.print("please press any button to start..");
        
        // guiHandler.launchGUI();

        PageHandler.changePage(Authintication_Page.getInstance());
        
    }


}
