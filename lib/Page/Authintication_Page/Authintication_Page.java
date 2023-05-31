package lib.Page.Authintication_Page;

import java.util.Scanner;

import lib.Page.Page;

public class Authintication_Page extends Page {
    private static Authintication_Page instance = null;
    private static Scanner scanner = new Scanner(System.in);

    public static Authintication_Page getInstance() {
        if (instance == null)
            instance = new Authintication_Page();
        return instance;
    }

    @Override
    public void run() {
        String input = scanner.nextLine();
        int counter = 0;
        for (Authintication_Page_Commmands command : Authintication_Page_Commmands.values()) {
            if (input.matches(command.content))
                break;
            counter++;
        }

        switch (counter) {
            case 0:
                
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:
                break;
        }
    }

}
