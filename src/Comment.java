package src;

import java.util.ArrayList;

public class Comment {
    public int ID;
    public ArrayList<Comment> replies = new ArrayList<>();
    public String content;
    public User commenter;
    public Food food;
    public Restaurant restaurant;

    Comment(int ID, User commenter, String content, Food food) {
        this.ID = ID;
        this.commenter = commenter;
        this.content = content;
        this.food = food;
    }

    Comment(int ID, User commenter, String content, Restaurant restaurant) {
        this.ID = ID;
        this.commenter = commenter;
        this.content = content;
        this.restaurant = restaurant;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReply(User commenter, String content) {
        Comment reply = new Comment(this.replies.size() - 1, commenter, content, this.food);
    }

    public boolean editRespond(int replyID, String newreply) {
        if (replyID > this.replies.size()) {
            System.out.println("reply does not exist");
            System.out.println("please re-enter your request");
            return false;
        }

        this.replies.get(replyID - 1).setContent(newreply);
        System.out.println("reply edited successfully");
        return true;
    }

    public void displayComment() {
        System.out.println("---------------------------------------------------------");
        System.out.println(this.commenter.username + ":");
        System.out.println(this.content);
    }

    public void displayReplies() {
        if (this.replies.isEmpty()) {
            System.out.println("this comment has no replies!");
            return;
        }

        System.out.println("=================================");
        for (Comment comment : this.replies)
            comment.displayComment();
        System.out.println("=================================");
    }
}
