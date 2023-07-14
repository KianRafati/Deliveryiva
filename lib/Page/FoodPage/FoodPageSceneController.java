package lib.Page.FoodPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
import javafx.scene.paint.Color;
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
    private Label FoodDescriptionLabel;

    @FXML
    private ImageView FoodImage;

    @FXML
    private Label FoodPageLabel;

    @FXML
    private Button LogoutBtn;

    @FXML
    private ImageView RatingImage;

    @FXML
    private Label PriceLabel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane scrollAnchor;

    private TextField editLabelTextField; // New TextField for editing the label

    private TextField editDescriptionTextField; // New TextField for editing the description

    private TextField editCommentTextField;

    private Label clickCountLabel = new Label();

    private Comment clickedComment;

    private Button activateButton; // New Button to activate or deactivate the food

    public void init() {
        FoodImage.setImage(new Image(foodPage.food.getImagePath()));
        FoodDescriptionLabel.setText(foodPage.food.getDescription());
        FoodPageLabel.setText(foodPage.food.getName());
        RatingImage.setImage(new Image(User.setRatingImage(foodPage.food.getRating())));
        User.receiveComments(foodPage.food);
        PriceLabel.setText(Double.toString(foodPage.food.getPrice()) + "$");

        if (User.currUser instanceof RestaurantAdmin) {
            // Enable label, description, and activation editing for RestaurantAdmin
            FoodPageLabel.setOnMouseClicked(this::handleLabelEdit);
            FoodDescriptionLabel.setOnMouseClicked(this::handleDescriptionEdit);
            PriceLabel.setOnMouseClicked(this::handlePriceEdit);
            createActivateButton();

            // Adjust the layout of the elements
            AnchorPane.setLeftAnchor(FoodPageLabel, 14.0);
            AnchorPane.setTopAnchor(FoodPageLabel, 79.0);
            AnchorPane.setLeftAnchor(FoodDescriptionLabel, 14.0);
            AnchorPane.setTopAnchor(FoodDescriptionLabel, 229.0);
            AnchorPane.setBottomAnchor(activateButton, 38.0);
            AnchorPane.setLeftAnchor(activateButton, 14.0);
        } else if (User.currUser instanceof Customer) {
            // Disable label, description, and activation editing for Customer
            mainPane.getChildren().add(clickCountLabel);
            if(!((Customer)User.currUser).cart.isEmpty())
                clickCountLabel.setText(Integer.toString(((Customer)User.currUser).getQuantity(foodPage.food)));
            clickCountLabel.setLayoutX(100);
            clickCountLabel.setLayoutY(250);
            createAddToCartButton();
        }

        VBox container = new VBox(); // Create a container to hold the boxes
        container.setSpacing(10); // Set the spacing between the boxes

        for (Comment comment : foodPage.food.comments) {

            Label nameLabel = new Label(comment.commenter.username); // Create a label for the restaurant name
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set the font for the label

            Label contentLabel = new Label(comment.content); // Create a label for the description
            contentLabel.setFont(Font.font("Arial", 12)); // Set the font for the description

            GridPane gridPane = new GridPane(); // Create a GridPane to hold the image, name, and description
            gridPane.setHgap(10); // Set the horizontal gap between columns
            gridPane.add(nameLabel, 0, 0, 1, 2); // Add the name label to the GridPane at column 0, row 2
            gridPane.add(contentLabel, 1, 0, 1, 3); // Add the description label to the GridPane at column 1, row 0 and
                                                    // span 1 column, 3 rows

            AnchorPane anchorPane = new AnchorPane(gridPane); // Create an AnchorPane to hold the GridPane
            AnchorPane.setLeftAnchor(gridPane, 0.0); // Align the GridPane to the left of the AnchorPane
            AnchorPane.setRightAnchor(gridPane, 0.0); // Align the GridPane to the right of the AnchorPane

            // Add hover and click functionality to the box
            anchorPane.setOnMouseEntered(event -> highlightBox(anchorPane)); // Highlight the box on mouse enter
            anchorPane.setOnMouseExited(event -> removeHighlight(anchorPane)); // Remove highlight on mouse exit
            anchorPane.setOnMouseClicked(event -> handleBoxClick(event, comment)); // Invoke a method on box click

            container.getChildren().add(anchorPane); // Add the AnchorPane to the container
        }
        scrollAnchor.getChildren().add(container); // Add the container to the scrollAnchor
    }

    private void highlightBox(AnchorPane anchorPane) {
        anchorPane.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
    }

    private void removeHighlight(AnchorPane anchorPane) {
        anchorPane.setStyle(null);
    }

    private void handleBoxClick(MouseEvent event, Comment comment) {
        clickedComment = comment;
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2 && clickedComment.commenter.equals(User.currUser)) {
                // Double-click to start editing the label
                editCommentTextField = new TextField(clickedComment.content);
                editCommentTextField.setPrefWidth(400);
                editCommentTextField.setLayoutX(mainPane.getLayoutX());
                editCommentTextField.setLayoutY(mainPane.getLayoutY());
                mainPane.getChildren().add(editCommentTextField);
                editCommentTextField.requestFocus();

                // Set a listener to handle label editing completion
                editCommentTextField.setOnAction(this::handleCommentEdit);
            }
        }
    }

    private void handleCommentEdit(ActionEvent event) {
        String newContent = editCommentTextField.getText();
        editCommentTextField.setText(newContent);
        mainPane.getChildren().remove(editCommentTextField);
        clickedComment.setContent(newContent);
        User.updateSQL("comments", "content", "comment_id = " + clickedComment.ID, "\"" + newContent + "\"");
    }

    private void createActivateButton() {
        activateButton = new Button();
        activateButton.setPrefWidth(90.0);
        activateButton.setText(foodPage.food.getStatus() ? "Deactivate" : "Activate");
        activateButton.setOnAction(this::handleActivateButtonClick);

        // Set the position of the activate button
        activateButton.setLayoutX(14.0);
        activateButton.setLayoutY(100.0);

        mainPane.getChildren().add(activateButton);
    }

    private void handleActivateButtonClick(ActionEvent event) {
        boolean isActive = foodPage.food.getStatus();
        foodPage.food.setStatus(!isActive);
        activateButton.setText(foodPage.food.getStatus() ? "Deactivate" : "Activate");
        if (foodPage.food.getStatus())
            User.updateSQL("foods", "food_status", "food_id = " + foodPage.food.getID(), "1");
        else
            User.updateSQL("foods", "food_status", "food_id = " + foodPage.food.getID(), "0");
    }

    private void handlePriceEdit(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                // Double-click to start editing the label
                editLabelTextField = new TextField(Double.toString(foodPage.food.getPrice()));
                editLabelTextField.setPrefWidth(PriceLabel.getWidth());
                editLabelTextField.setLayoutX(PriceLabel.getLayoutX());
                editLabelTextField.setLayoutY(PriceLabel.getLayoutY());
                mainPane.getChildren().add(editLabelTextField);
                editLabelTextField.requestFocus();

                // Set a listener to handle label editing completion
                editLabelTextField.setOnAction(this::handlePriceEditComplete);
            }
        }
    }

    private void handlePriceEditComplete(ActionEvent event) {
        String newPrice = editLabelTextField.getText();
        PriceLabel.setText(newPrice + "$");
        mainPane.getChildren().remove(editLabelTextField);
        foodPage.food.setPrice(Double.parseDouble(newPrice));
        User.updateSQL("foods", "price", "food_id = " + foodPage.food.getID(), newPrice);
    }

    private void handleLabelEdit(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                // Double-click to start editing the label
                editLabelTextField = new TextField(FoodPageLabel.getText());
                editLabelTextField.setPrefWidth(FoodPageLabel.getWidth());
                editLabelTextField.setLayoutX(FoodPageLabel.getLayoutX());
                editLabelTextField.setLayoutY(FoodPageLabel.getLayoutY());
                mainPane.getChildren().add(editLabelTextField);
                editLabelTextField.requestFocus();

                // Set a listener to handle label editing completion
                editLabelTextField.setOnAction(this::handleLabelEditComplete);
            }
        }
    }

    private void handleLabelEditComplete(ActionEvent event) {
        String newLabel = editLabelTextField.getText();
        FoodPageLabel.setText(newLabel);
        mainPane.getChildren().remove(editLabelTextField);
        foodPage.food.setName(newLabel);
        User.updateSQL("foods", "food_name", "food_id = " + foodPage.food.getID(), "\"" + newLabel + "\"");
    }

    private void handleDescriptionEdit(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                // Double-click to start editing the description
                editDescriptionTextField = new TextField(FoodDescriptionLabel.getText());
                editDescriptionTextField.setPrefWidth(FoodDescriptionLabel.getWidth());
                editDescriptionTextField.setPrefHeight(FoodDescriptionLabel.getHeight());
                editDescriptionTextField.setLayoutX(FoodDescriptionLabel.getLayoutX());
                editDescriptionTextField.setLayoutY(FoodDescriptionLabel.getLayoutY());
                mainPane.getChildren().add(editDescriptionTextField);
                editDescriptionTextField.requestFocus();

                // Set a listener to handle description editing completion
                editDescriptionTextField.setOnAction(this::handleDescriptionEditComplete);
            }
        }
    }

    private void handleDescriptionEditComplete(ActionEvent event) {
        String newDescription = editDescriptionTextField.getText();
        FoodDescriptionLabel.setText(newDescription);
        mainPane.getChildren().remove(editDescriptionTextField);
        foodPage.food.setDescription(newDescription);
        User.updateSQL("foods", "food_description", "food_id = " + foodPage.food.getID(), "\"" + newDescription + "\"");
    }

    @FXML
    void Back(ActionEvent event) {
        foodPage.run("back");
    }

    @FXML
    void Logout(ActionEvent event) {
        User.Logout();
    }

    private void createAddToCartButton() {
        ImageView plusImageView = new ImageView(new Image("E:\\Sharif University of Technology\\2th semester\\OOP\\Project\\Deliveryiva\\Deliveryiva\\lib\\Assets\\PlusButton.png"));
        plusImageView.setFitWidth(100);
        plusImageView.setFitHeight(100);
        plusImageView.setOnMousePressed(event -> {
            ActionEvent actionEvent = new ActionEvent();
            handleAddToCartButtonClick(actionEvent);
        });
        
        
        // Set the position of the plus image view
        plusImageView.setLayoutX(0.0);
        plusImageView.setLayoutY(200.0);
    
        mainPane.getChildren().add(plusImageView);
    }
    
    private void handleAddToCartButtonClick(ActionEvent event) {
        if (User.currUser instanceof Customer) {
            Customer customer = (Customer) User.currUser;
            customer.addFoodToCart(foodPage.food, 1);
            int clickCount = customer.getQuantity(foodPage.food);
            String labelString = "<" + clickCount + ">";
    
            clickCountLabel.setText(labelString);
            clickCountLabel.setTextFill(Color.RED);
    
        }
    }
    

}
