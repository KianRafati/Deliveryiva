package lib.Page.CustomerPage;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import src.Customer;
import src.PageHandler;
import src.Restaurant;
import src.User;

public class CustomerPageSceneController {
    CustomerPage customerPage = (CustomerPage) PageHandler.currPage;

    @FXML
    private Button BackBtn;

    @FXML
    private Label CustomerPageLabel;

    @FXML
    private ImageView SettingMenuButton;

    @FXML
    private Button LogoutBtn;

    @FXML
    private AnchorPane scrollAnchor;

    @FXML
    void Back(ActionEvent event) {
        customerPage.run("back");
    }

    public void init() {
        CustomerPageLabel.setText(customerPage.getUser().username + "'s Page");
        VBox container = new VBox(); // Create a container to hold the boxes
        container.setSpacing(10); // Set the spacing between the boxes

        System.out.println(((Customer) this.customerPage.getUser()).localRests.size());
        for (Restaurant restaurant : ((Customer) this.customerPage.getUser()).localRests) {
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
            gridPane.add(imageView, 0, 0, 1, 2); // Add the image to the GridPane at column 0, row 0 and span 1 column,
                                                 // 2 rows
            gridPane.add(nameLabel, 0, 2); // Add the name label to the GridPane at column 0, row 2
            gridPane.add(descriptionLabel, 1, 0, 1, 3); // Add the description label to the GridPane at column 1, row 0
                                                        // and span 1 column, 3 rows

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

    @FXML
    void Logout(ActionEvent event) {
        User.Logout();
    }

    private void adjustScrollAnchorHeight() {
        double totalHeight = 0;

        // Calculate the total height of all the boxes in the container
        for (Node node : ((VBox) scrollAnchor.getChildren().get(0)).getChildren()) {
            totalHeight += node.getBoundsInParent().getHeight();
        }

        // Set the height of the scrollAnchor to the total height
        scrollAnchor.setMinHeight(totalHeight);
        scrollAnchor.setPrefHeight(totalHeight);
    }

    @FXML
    void SettingsClicked(MouseEvent event) {
        double menuWidth = 100.0; // Width of the settings menu
        double menuHeight = 600.0; // Height of the settings menu
        double animationDuration = 0.3; // Animation duration in seconds

        if (event.getClickCount() == 1) {
            if (scrollAnchor.getTranslateX() == 0) {
                // Show the settings menu by moving it to the left
                scrollAnchor.setTranslateX(-menuWidth);

                VBox settingsMenu = new VBox();
                settingsMenu.setStyle("-fx-background-color: gray; -fx-border-color: black;");
                settingsMenu.setPrefWidth(menuWidth);
                settingsMenu.setPrefHeight(menuHeight);

                // Create event handlers for the menu items
                EventHandler<ActionEvent> cartHandler = e -> handleCart();
                EventHandler<ActionEvent> ordersHandler = e -> handleOrders();
                EventHandler<ActionEvent> profileHandler = e -> handleProfile();

                // Add the menu items/buttons to the settings menu
                Button menuItem1 = new Button("Cart");
                menuItem1.setOnAction(cartHandler);
                Button menuItem2 = new Button("Orders");
                menuItem2.setOnAction(ordersHandler);
                Button menuItem3 = new Button("Profile");
                menuItem3.setOnAction(profileHandler);

                settingsMenu.getChildren().addAll(menuItem1, menuItem2, menuItem3);
                scrollAnchor.getChildren().add(settingsMenu);

                // Apply slide-in animation to the settings menu
                TranslateTransition slideInTransition = new TranslateTransition(Duration.seconds(animationDuration),
                        scrollAnchor);
                slideInTransition.setToX(0);
                slideInTransition.play();
            } else {
                // Hide the settings menu by moving it to the right
                TranslateTransition slideOutTransition = new TranslateTransition(Duration.seconds(animationDuration),
                        scrollAnchor);
                slideOutTransition.setToX(-menuWidth);
                slideOutTransition.setOnFinished(e -> scrollAnchor.getChildren().clear());
                slideOutTransition.play();
            }
        }
    }

    private void handleCart() {
        // Code to handle the "Cart" button click
        System.out.println("Cart button clicked!");
    }

    private void handleOrders() {
        // Code to handle the "Orders" button click
        System.out.println("Orders button clicked!");
    }

    private void handleProfile() {
        // Code to handle the "Profile" button click
        System.out.println("Profile button clicked!");
    }

}