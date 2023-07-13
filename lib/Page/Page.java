package lib.Page;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface Page {
    abstract public void run(String input);
    public abstract Parent getRoot();
}
