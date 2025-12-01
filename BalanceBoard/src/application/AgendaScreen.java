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

public class AgendaScreen extends Window {

    // Fields inherited from Window: Scene, Stage

    // Constructors
    public AgendaScreen(Stage stage, User user) {
        super(stage, user);
    }

    // Methods

    @Override
    public Parent createContent(User u) {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: black;");
        Label title = new Label("(Agenda Screen)");
        title.setStyle("-fx-text-fill: #AAAAAA;");

        Button add_Task = new Button("Add Task");
        Button home = new Button("Home");

        // Go Home i.e. to Welcome Screen
        home.setOnAction(e -> {
            WelcomeScreen welcome = new WelcomeScreen(stage, user);
            stage.setScene(welcome.getScene());
        });

        root.getChildren().setAll(title, add_Task, home);
        root.setAlignment(Pos.CENTER);
        return root;
    }
}
