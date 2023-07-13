package lib.Page.RestaurantAdminPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.Page.Page;
import src.PageHandler;
import src.Restaurant;
import javafx.scene.Node;

public class RestaurantAdminPageController {
    RestaurantAdminPage restAdmPage = (RestaurantAdminPage)PageHandler.currPage;

    @FXML
    private Button BackBtn;
    
    @FXML
    private AnchorPane scrollAnchor;

    @FXML
    void Back(ActionEvent event) {
        restAdmPage.run("back");
    }

    
    public void init() {
        VBox container = new VBox(); // Create a container to hold the boxes
        container.setSpacing(10); // Set the spacing between the boxes
        
        for (Restaurant restaurant : this.restAdmPage.getUser().getRests()) {
            ImageView imageView = new ImageView(); // Create an ImageView for the image
            imageView.setImage(new Image(restaurant.getImagePath())); // Set the image path
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            
            Label nameLabel = new Label(restaurant.getName()); // Create a label for the restaurant name
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set the font for the label
            
            Label descriptionLabel = new Label(restaurant.getDescription()); // Create a label for the description
            descriptionLabel.setFont(Font.font("Arial", 12)); // Set the font for the description
            
            GridPane gridPane = new GridPane(); // Create a GridPane to hold the image, name, and description
            gridPane.setHgap(10); // Set the horizontal gap between columns
            gridPane.add(imageView, 0, 0, 1, 2); // Add the image to the GridPane at column 0, row 0 and span 1 column, 2 rows
            gridPane.add(nameLabel, 0, 2); // Add the name label to the GridPane at column 0, row 2
            gridPane.add(descriptionLabel, 1, 0, 1, 3); // Add the description label to the GridPane at column 1, row 0 and span 1 column, 3 rows
            
            AnchorPane anchorPane = new AnchorPane(gridPane); // Create an AnchorPane to hold the GridPane
            AnchorPane.setLeftAnchor(gridPane, 0.0); // Align the GridPane to the left of the AnchorPane
            AnchorPane.setRightAnchor(gridPane, 0.0); // Align the GridPane to the right of the AnchorPane
            
            // Add hover and click functionality to the box
            anchorPane.setOnMouseEntered(event -> highlightBox(anchorPane)); // Highlight the box on mouse enter
            anchorPane.setOnMouseExited(event -> removeHighlight(anchorPane)); // Remove highlight on mouse exit
            anchorPane.setOnMouseClicked(event -> handleBoxClick(restaurant)); // Invoke a method on box click
            
            container.getChildren().add(anchorPane); // Add the AnchorPane to the container
        }
        
        // Clear existing content in scrollAnchor
        scrollAnchor.getChildren().clear();
        
        // Add the container to the scroll anchor
        scrollAnchor.getChildren().add(container);
        
        // Adjust the height of scrollAnchor based on the content
        adjustScrollAnchorHeight();
    }
    
    private void highlightBox(AnchorPane anchorPane) {
        anchorPane.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
    }
    
    private void removeHighlight(AnchorPane anchorPane) {
        anchorPane.setStyle(null);
    }
    
    private void handleBoxClick(Restaurant restaurant) {
        restaurant.getPage().previousPage = PageHandler.currPage;
        PageHandler.changePage(restaurant.getPage());
    }    
    
    //TODO fix logout
    @FXML
    void Logout(ActionEvent event){
        PageHandler.Logout();
    }

    private void adjustScrollAnchorHeight() {
        double totalHeight = 0;
        
        // Calculate the total height of all the boxes in the container
        for (Node node : ((VBox)scrollAnchor.getChildren().get(0)).getChildren()) {
            totalHeight += node.getBoundsInParent().getHeight();
        }
        
        // Set the height of the scrollAnchor to the total height
        scrollAnchor.setMinHeight(totalHeight);
        scrollAnchor.setPrefHeight(totalHeight);
    }
    

}
