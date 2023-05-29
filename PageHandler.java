import java.util.ArrayList;
import java.util.Scanner;

public class PageHandler {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean run = true;
    // static ArrayList<Page> visitedPages = new ArrayList<>();
    
    protected static void Program(){
        String input;
        while(run){
            input = scanner.nextLine();
            if(input.equals("CLOSE APP"))
                run = false;
        }
        terminate();
    }

    private static void terminate(){
        scanner.close();
    }
}
