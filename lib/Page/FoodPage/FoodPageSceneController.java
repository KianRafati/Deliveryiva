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

    private Button activateButton; // New Button to activate or deactivate the food

    public void init() {
        FoodImage.setImage(new Image(foodPage.food.getImagePath()));
        FoodDescriptionLabel.setText(foodPage.food.getDescription());
        FoodPageLabel.setText(foodPage.food.getName());
        RatingImage.setImage(new Image(User.setRatingImage(foodPage.food.getRating())));
        User.receiveComments(foodPage.food);
        PriceLabel.setText(Double.toString(foodPage.food.getPrice())+"$");

        if (User.currUser instanceof RestaurantAdmin) {
            // Enable label, description, and activation editing for RestaurantAdmin
            FoodPageLabel.setOnMouseClicked(this::handleLabelEdit);
            FoodDescriptionLabel.setOnMouseClicked(this::handleDescriptionEdit);
            PriceLabel.setOnMouseClicked(this::handlePriceEdit);
            createActivateButton();
        } else if (User.currUser instanceof Customer) {
            // Disable label, description, and activation editing for Customer
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
            anchorPane.setOnMouseClicked(event -> handleBoxClick(comment)); // Invoke a method on box click

            container.getChildren().add(anchorPane); // Add the AnchorPane to the container
        }
        scrollAnchor.getChildren().add(container); // Add the container to the scrollAnchor

        // Adjust the layout of the elements
        AnchorPane.setLeftAnchor(FoodPageLabel, 14.0);
        AnchorPane.setTopAnchor(FoodPageLabel, 79.0);
        AnchorPane.setLeftAnchor(FoodDescriptionLabel, 14.0);
        AnchorPane.setTopAnchor(FoodDescriptionLabel, 229.0);
        AnchorPane.setBottomAnchor(activateButton, 38.0);
        AnchorPane.setLeftAnchor(activateButton, 14.0);
    }

    private void highlightBox(AnchorPane anchorPane) {
        anchorPane.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
    }

    private void removeHighlight(AnchorPane anchorPane) {
        anchorPane.setStyle(null);
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

        mainPane.getChildren().add(activateButton);
    }

    private void handleActivateButtonClick(ActionEvent event) {
        boolean isActive = foodPage.food.getStatus();
        foodPage.food.setStatus(!isActive);
        activateButton.setText(foodPage.food.getStatus() ? "Deactivate" : "Activate");
    }

    private void handlePriceEdit(MouseEvent event){
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
        String newLabel = editLabelTextField.getText();
        PriceLabel.setText(newLabel+"$");
        mainPane.getChildren().remove(editLabelTextField);
        foodPage.food.setPrice(Double.parseDouble(newLabel));;
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
    }

    @FXML
    void Back(ActionEvent event) {
        foodPage.run("back");
    }

    @FXML
    void Logout(ActionEvent event) {
        User.Logout();
    }
}
