package lib.Page.FoodPage;

import lib.Page.Page;
import src.Food;
import src.PageHandler;
import src.Restaurant;

public class FoodPage extends Page {
    private Food food;
    private Restaurant restaurant;
    
    public FoodPage(Food food , Restaurant restaurant){
        this.food = food;
        this.restaurant = restaurant;
    }
    
    @Override
    public void run(String input) {

        if(input.equals("back")){
            PageHandler.changePage(this.previousPage);
            return;
        }
        
        System.out.println("***********"+this.food.getName()+"'s page***********");

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
