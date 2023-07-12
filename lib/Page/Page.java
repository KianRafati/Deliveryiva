package lib.Page;
import javafx.scene.Node;
import javafx.scene.Parent;


public abstract class Page {
    public Page previousPage = null;
    abstract public void run(String input);
    
    
    // public abstract Parent getRoot();

    // protected Parent root;

    // public abstract Parent getUI();
}
