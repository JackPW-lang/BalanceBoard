package application;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;


public class SchedulerScreen extends Window {

    // Fields inherited from Window Class: scene, stage

    // Constructors
    public SchedulerScreen (Stage stage, User user) {
        super(stage, user);
    }

    // Methods

    @Override
    public Parent createContent(User u) {

        if(!u.getComplexityPreferences()[2]) { // The user's preference is simple

            VBox root = new VBox();
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Simple Scheduler Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            Button viewSchedule = new Button("View Schedule");
            Button home = new Button("Home");
            Button toComplex = new Button("Complex");

            // Go Home, i.e. go to Welcome Screen
            home.setOnAction(e -> {
                WelcomeScreen welcome = new WelcomeScreen(stage, user);
                stage.setScene(welcome.getScene());
            });

            // Switch to Complex
            toComplex.setOnAction(e -> {
                user.updateSchedulerComplexity(true);
                SchedulerScreen complex = new SchedulerScreen(stage, user);
                stage.setScene(complex.getScene());
            });

            root.getChildren().addAll(title, viewSchedule, home, toComplex);
            root.setAlignment(Pos.CENTER);
            return root;

        } else { //  The user's preference is complex

            VBox root = new VBox();
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Complex Scheduler Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            Button viewSchedule = new Button("View Schedule");
            Button home = new Button("Home");
            Button toSimple = new Button("Simple");

            // Go Home, i.e. go to Welcome Screen
            home.setOnAction(e -> {
                WelcomeScreen welcome = new WelcomeScreen(stage, user);
                stage.setScene(welcome.getScene());
            });

            // Switch to Simple Mode
            toSimple.setOnAction(e -> {
               user.updateSchedulerComplexity(false);
               SchedulerScreen simple = new SchedulerScreen(stage, user);
               stage.setScene(simple.getScene());
            });

            root.getChildren().addAll(title, viewSchedule, home, toSimple);
            root.setAlignment(Pos.CENTER);
            return root;
        }
    }
}
