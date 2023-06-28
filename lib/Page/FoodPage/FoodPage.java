package lib.Page.FoodPage;

import lib.Page.Page;
import src.Food;
import src.PageHandler;
import src.Restaurant;
import src.User;

public class FoodPage extends Page {
    private Food food;
    private Restaurant restaurant;

    private int inputCount = 0;
    private int commentID = 0;
    private int respondID = 0;

    public FoodPage(Food food, Restaurant restaurant) {
        this.food = food;
        this.restaurant = restaurant;
    }

    @Override
    public void run(String input) {

        if (input.equals("back")) {
            PageHandler.changePage(this.previousPage);
            return;
        }

        System.out.println("***********" + this.food.getName() + "'s page***********");

        if (inputCount == 0) {
            int counter = 0;
            for (FoodPageCommands command : FoodPageCommands.values()) {
                if (input.matches(command.content))
                    break;
                counter++;
            }

            switch (counter) {
                case 0: // show description
                    this.food.DisplayComments();
                    break;
                case 1: // edit name
                    String[] temp0 = input.split("\\s");
                    this.food.setName(temp0[2]);
                    break;
                case 2: // edit price
                    String[] temp1 = input.split("\\s");
                    this.food.setPrice(Double.parseDouble(temp1[2]));
                    break;
                case 3:// add discount
                    String[] temp2 = input.split("\\s");
                    if (!this.food.setDiscount(Double.parseDouble(temp2[2]), Double.parseDouble(temp2[4])))
                        return;
                    break;
                case 4: // display rating
                    this.food.DisplayRatings();
                    break;
                case 5: // display comments
                    this.food.DisplayComments();
                    break;
                case 6: // comment
                    inputCount = 1;
                    return;
                case 7: // respond
                    String[] temp3 = input.split("\\s");
                    commentID = Integer.parseInt(temp3[5]);
                    if (commentID < 0 || commentID > this.food.comments.size()) {
                        System.out.println("this comment does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    inputCount = 2;
                    return;
                case 8: // edit respond
                    String[] temp4 = input.split("\\s");
                    commentID = Integer.parseInt(temp4[9]);
                    respondID = Integer.parseInt(temp4[5]);
                    if (commentID < 0 || commentID > this.food.comments.size()) {
                        System.out.println("this comment does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    if (respondID < 0 || respondID > this.food.comments.get(commentID - 1).replies.size()) {
                        System.out.println("this reply does not exist");
                        System.out.println("please re-enter your request");
                        return;
                    }
                    inputCount = 3;
                    return;
                case 9: // display replies
                    String[] temp5 = input.split("\\s");
                    commentID = Integer.parseInt(temp5[6]);
                    this.food.comments.get(commentID-1).displayReplies();
                    break;
                default:
                    break;
            }
        } else if (inputCount == 1) { // comments
            inputCount = 0;
            this.food.setComment(User.currUser, input);
        } else if (inputCount == 2) { // responds
            inputCount = 0;
            this.food.setResond(commentID, User.currUser, input);
        } else if (inputCount == 3) { // edit respond
            inputCount = 0;
            if (!this.food.editResond(commentID, respondID, User.currUser, input))
                return;
        }
    }

}
