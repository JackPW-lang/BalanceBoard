package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class AnalyticsScreen extends Window { // The previously defined Analytics Class will now be implemented here.

    // Fields inherited from Window class: scene, stage.

    // Constructors
    public AnalyticsScreen(Stage stage) {
        super(stage);
    }

    // Methods

    public double calcProductivityScore(User user){
        // Implement Calculations Here
        return 0.0;
    }

    @Override
    public Parent createContent() {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: black;");
        Label title = new Label("A breakdown of your performance this week.");
        title.setStyle("-fx-text-fill: #AAAAAA;");
        Button viewAnalytics = new Button("View Analytics");
        Button home = new Button("Home");
        home.setOnAction(e -> {
           WelcomeScreen welcome = new WelcomeScreen(stage);
           stage.setScene(welcome.getScene());
        });
        root.getChildren().setAll(title, viewAnalytics, home);
        root.setAlignment(Pos.CENTER);
        return root;
    }
}
