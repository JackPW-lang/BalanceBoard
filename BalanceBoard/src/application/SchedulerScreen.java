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


public class SchedulerScreen extends Window {

    // Fields inherited from Window Class: scene, stage

    // Constructors
    public SchedulerScreen (Stage stage) {
        super(stage);
    }

    // Methods

    @Override
    public Parent createContent() {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: black;");
        Label title = new Label("A quick look at your week.");
        title.setStyle("-fx-text-fill: #AAAAAA;");
        Button viewSchedule = new Button("View Schedule");
        Button home = new Button("Home");
        home.setOnAction(e -> {
           WelcomeScreen welcome = new WelcomeScreen(stage);
           stage.setScene(welcome.getScene());
        });
        root.getChildren().addAll(title, viewSchedule, home);
        root.setAlignment(Pos.CENTER);
        return root;
    }
}
