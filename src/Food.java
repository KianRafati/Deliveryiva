package src;

import java.util.ArrayList;

import lib.Page.Page;
import lib.Page.FoodPage.FoodPage;

public class Food {
    private String name;
    private double price;
    private double discountAmount;
    private double discountTimeStamp;
    private int ID;
    private boolean active;
    private FoodPage page;
    private Rating totRating;
    private ArrayList<Rating> Ratings = new ArrayList<>();
    private boolean isAvailable;
    private boolean hasDiscount;
    public ArrayList<Comment> comments = new ArrayList<>();
    private String FoodDescription;


    public Food(String foodName, double price,int ID) {
        this.name = foodName;
        this.price = price;
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public boolean setDiscount(double discount,double timestamp){
        if(discount < 0 || discount > 50){
            System.out.println("the discount percentage must be within 0 to 50!");
            System.out.println("please re-enter your request");
            return false;
        }

        if(timestamp < 0){
            System.out.println("the discount timestamp must be positive!");
            System.out.println("please re-enter your request");
            return false;
        }

        this.discountAmount = discount;
        this.discountTimeStamp = timestamp;
        System.out.println("discount set successfully");
        return true;
    }

    public void activeFood(){
        this.active = true;
    }

    public void deactiveFood(){
        this.active = false;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(FoodPage page) {
        this.page = page;
    }

    public void DisplayRatings(){
        if(this.Ratings.isEmpty()){
            System.out.println(this.name+" has no ratings!");
            return;
        }

        System.out.println(this.name+"'s rating:"+this.totRating.amount);
    }

    public void DisplayComments(){
        if(this.comments.isEmpty()){
            System.out.println(this.name+" has no comments!");
            return;
        }

        System.out.println(this.name+"'s comments:");
        for (Comment comment : this.comments) {
            comment.displayComment();
        }

    }

    public boolean Respond(int CommentID,User commenter,String reply){
        if(CommentID > this.comments.size()){
            System.out.println("comment does not exist");
            System.out.println("please re-enter your request");
            return false;
        }

        this.comments.get(CommentID - 1).setReply(commenter, reply);
        System.out.println("reply set successfully!");
        return true;
    }

    private double calculateRating(){
        if(this.Ratings.isEmpty())
            return 0;

        double rate = 0;
        for (Rating rating : this.Ratings) 
            rate += rating.amount;
        rate = rate/this.Ratings.size();

        return rate;
    }

    public void setComment(User user,String content) {
        Comment comment = new Comment(this.comments.size(), user, content, this);
        this.comments.add(comment);
        System.out.println("comment set successfully");
        System.out.println("thank you for your feedback");
    }

    public boolean editComment(int commentID,User user, String content){
        Comment comment = this.comments.get(commentID-1);
        if(!comment.commenter.equals(user)){
            System.out.println("This is not your comment!");
            System.out.println("please re-enter your request");
            return false;
        }

        comment.setContent(content);
        System.out.println("Comment updated successfully");
        return true;
    }

    public void setResond(int commentID,User user, String input) {
        this.comments.get(commentID-1).setReply(user, input);
        System.out.println("reply set successfully");
    }

    public boolean editResond(int commentID,int replyID, User user, String input) {
        Comment reply = this.comments.get(commentID-1).replies.get(replyID-1);
        if(!reply.commenter.equals(user)){
            System.out.println("This is not your comment!");
            System.out.println("please re-enter your request");
            return false;
        }

        reply.setContent(input);
        System.out.println("comment edited successfully");
        return true;

    }

    public Integer getID() {
        return this.ID;
    }

    public Rating getRating(){
        return this.totRating;
    }

    public ArrayList<Rating> getRatings(){
        return this.Ratings;
    }

    public void setRate(Customer customer,int amount){
        for (Rating rating : this.Ratings) {
            if(customer.equals(rating.customer)){
                System.out.println("you can not use this command you've rated this food");
                return;
            }
        }
        Rating rating = new Rating(this.Ratings.size(), amount, customer, this);
        this.Ratings.add(rating);
        System.out.println("Reply set successfully");
    }

    public boolean editRating(User user,int newAmount){
        for (Rating rating : this.Ratings) {
            if(rating.customer.equals(user)){
                rating.amount = newAmount;
                System.out.println("rating edited successfully");
                return true;
            }
        }

        System.out.println("you have not rated this food!");
        return false;
    }
}