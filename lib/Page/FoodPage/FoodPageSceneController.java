package lib.Page.FoodPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import src.Comment;
import src.Customer;
import src.PageHandler;
import src.RestaurantAdmin;
import src.User;

public class FoodPageSceneController {
    FoodPage foodPage = (FoodPage) PageHandler.currPage;

    @FXML
    private Button BackBtn;

    @FXML
    private ImageView RatingImage;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label FoodDescriptionLabel;

    @FXML
    private ImageView FoodImage;

    @FXML
    private Label FoodPageLabel;

    @FXML
    private Button LogoutBtn;

    @FXML
    private AnchorPane scrollPane;

    private TextField editLabelTextField; // New TextField for editing the label

    private TextField editDescriptionTextField; // New TextField for editing the description

    private Button activateButton; // New Button to activate or deactivate the food

    public void init() {
        FoodImage.setImage(new Image(foodPage.food.getImagePath()));
        FoodDescriptionLabel.setText(foodPage.food.getDescription());
        FoodPageLabel.setText(foodPage.food.getName());
        RatingImage.setImage(new Image(User.setRatingImage(foodPage.food.getRating())));
        User.receiveComments(foodPage.food);
    
        if (User.currUser instanceof RestaurantAdmin) {
            // Enable label, description, and activation editing for RestaurantAdmin
            FoodPageLabel.setOnMouseClicked(this::handleLabelEdit);
            FoodDescriptionLabel.setOnMouseClicked(this::handleDescriptionEdit);
            createActivateButton();
        } else if (User.currUser instanceof Customer) {
            // Disable label, description, and activation editing for Customer
        }
    
        VBox container = new VBox(); // Create a container to hold the boxes
        container.setSpacing(10); // Set the spacing between the boxes
    
        for (Comment comment : foodPage.food.comments) {
            // Create labels for the commenter's name and comment content
            Label nameLabel = new Label(comment.commenter.username);
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    
            Label descriptionLabel = new Label(comment.content);
            descriptionLabel.setFont(Font.font("Arial", 12));
    
            VBox commentBox = new VBox(nameLabel, descriptionLabel); // Create a VBox to hold the labels
            commentBox.getStyleClass().add("comment-box"); // Apply CSS style to the comment box
    
            commentBox.setOnMouseEntered(event -> highlightBox(commentBox)); // Highlight the comment box on mouse enter
            commentBox.setOnMouseExited(event -> removeHighlight(commentBox)); // Remove highlight on mouse exit
            commentBox.setOnMouseClicked(event -> handleBoxClick(comment)); // Invoke a method on comment box click
    
            container.getChildren().add(commentBox); // Add the comment box to the container
        }
    
        // scrollPane.setFitToWidth(true); // Adjust the ScrollPane to fit the width
        // scrollPane.setFitToHeight(true); // Adjust the ScrollPane to fit the height
        // scrollPane.setContent(container); // Set the container as the content of the ScrollPane
        
    
        // Adjust the layout of the elements
        AnchorPane.setLeftAnchor(FoodPageLabel, 14.0);
        AnchorPane.setTopAnchor(FoodPageLabel, 79.0);
        AnchorPane.setLeftAnchor(FoodDescriptionLabel, 14.0);
        AnchorPane.setTopAnchor(FoodDescriptionLabel, 229.0);
        AnchorPane.setBottomAnchor(activateButton, 38.0);
        AnchorPane.setLeftAnchor(activateButton, 14.0);
    }
    
    private void highlightBox(VBox commentBox) {
        commentBox.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
    }
    
    private void removeHighlight(VBox commentBox) {
        commentBox.setStyle(null);
    }
    
    private void handleBoxClick(Comment comment) {
        // comment.getPage().previousPage = PageHandler.currPage;
        // PageHandler.changePage(restaurant.getPage());
    }    
    

    private void createActivateButton() {
        activateButton = new Button();
        activateButton.setPrefWidth(90.0);
        activateButton.setText(foodPage.food.getStatus() ? "Deactivate" : "Activate");
        activateButton.setOnAction(this::handleActivateButtonClick);

        // Set the position of the activate button
        activateButton.setLayoutX(14.0);
        activateButton.setLayoutY(100.0);

        scrollPane.getChildren().add(activateButton);
    }

    private void handleActivateButtonClick(ActionEvent event) {
        boolean isActive = foodPage.food.getStatus();
        foodPage.food.setStatus(!isActive);
        activateButton.setText(foodPage.food.getStatus() ? "Deactivate" : "Activate");
    }

    private void handleLabelEdit(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                // Double-click to start editing the label
                editLabelTextField = new TextField(FoodPageLabel.getText());
                editLabelTextField.setPrefWidth(FoodPageLabel.getWidth());
                editLabelTextField.setLayoutX(FoodPageLabel.getLayoutX());
                editLabelTextField.setLayoutY(FoodPageLabel.getLayoutY());
                scrollPane.getChildren().add(editLabelTextField);
                editLabelTextField.requestFocus();

                // Set a listener to handle label editing completion
                editLabelTextField.setOnAction(this::handleLabelEditComplete);
            }
        }
    }

    private void handleLabelEditComplete(ActionEvent event) {
        String newLabel = editLabelTextField.getText();
        FoodPageLabel.setText(newLabel);
        scrollPane.getChildren().remove(editLabelTextField);
        foodPage.food.setName(newLabel);
    }

    private void handleDescriptionEdit(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                // Double-click to start editing the description
                editDescriptionTextField = new TextField(FoodDescriptionLabel.getText());
                editDescriptionTextField.setPrefWidth(FoodDescriptionLabel.getWidth());
                editDescriptionTextField.setPrefHeight(FoodDescriptionLabel.getHeight());
                editDescriptionTextField.setLayoutX(FoodPageLabel.getLayoutX());
                editDescriptionTextField.setLayoutY(FoodPageLabel.getLayoutY());
                scrollPane.getChildren().add(editDescriptionTextField);
                editDescriptionTextField.requestFocus();

                // Set a listener to handle description editing completion
                editDescriptionTextField.setOnAction(this::handleDescriptionEditComplete);
            }
        }
    }

    private void handleDescriptionEditComplete(ActionEvent event) {
        String newDescription = editDescriptionTextField.getText();
        FoodDescriptionLabel.setText(newDescription);
        scrollPane.getChildren().remove(editDescriptionTextField);
        foodPage.food.setDescription(newDescription);
    }

    @FXML
    void Back(ActionEvent event) {
        foodPage.run("back");
    }

    @FXML
    void Logout(ActionEvent event) {
        // Add logout functionality here
    }
}
