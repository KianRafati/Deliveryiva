package src;

import java.util.ArrayList;

public class Comment {
    public int ID;
    public Comment toComment;
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

    public void setReply(User commenter, Food food, String content) {
        Comment reply = new Comment(User.receiveID("comment_id", "comments"), commenter, content, food);
        reply.toComment = this;
        this.replies.add(reply);
        User.addSQLrow("comment11", reply);
    }

    public boolean editRespond(int replyID, String newreply, User user) {
        if (replyID > this.replies.size() || replyID < 0) {
            System.out.println("reply does not exist");
            System.out.println("please re-enter your request");
            return false;
        }

        Comment reply = this.replies.get(replyID - 1);

        if (!reply.commenter.equals(user)) {
            System.out.println("This is not your reply!");
            System.out.println("please re-enter your request");
            return false;
        }

        reply.setContent(newreply);
        User.updateSQL("comments", "content", "comment_id = " + reply.ID, newreply);
        System.out.println("reply edited successfully");
        return true;
    }

    public void displayComment() {
        if (this.toComment != null)
            return;
        System.out.println(this.commenter.username + ":");
        System.out.println(this.content);
        System.out.println("---------------------------------------------------------");
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

    public void setReply(User user, Restaurant restaurant, String input) {
        Comment reply = new Comment(User.receiveID("comment_id", "comments"), commenter, content, restaurant);
        reply.toComment = this;
        this.replies.add(reply);
        User.addSQLrow("comment12", reply);
    }

    public void setTo(int to_comment_id, Food food) {
        this.toComment = food.comments.get(to_comment_id - 1);
    }

}
