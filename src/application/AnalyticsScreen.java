package application;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.geometry.Pos;

public class AnalyticsScreen extends Window { // The previously defined Analytics Class will now be implemented here.

    // Fields inherited from Window class: scene, stage.

    // Constructors
    public AnalyticsScreen(Stage stage, User user) {
        super(stage, user);
    }

    // Methods

    public double calcProductivityScore(User user){
        // Implement Calculations Here
        return 0.0;
    }

    @Override
    public Parent createContent(User u) {

        if(!u.getComplexityPreferences()[3]) { // The user's preference is simple

            VBox root = new VBox();
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Simple Analytics Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            Button viewAnalytics = new Button("View Analytics");
            Button home = new Button("Home");
            Button toComplex = new Button("Complex");

            // Go Home, i.e. return to Welcome Screen.
            home.setOnAction(e -> {
                WelcomeScreen welcome = new WelcomeScreen(stage, user);
                stage.setScene(welcome.getScene());
            });

            // Switch to complex mode
            toComplex.setOnAction(e -> {
                user.updateAnalyticsComplexity(true);
                AnalyticsScreen complex = new AnalyticsScreen(stage, user);
                stage.setScene(complex.getScene());
            });

            root.getChildren().setAll(title, viewAnalytics, home, toComplex);
            root.setAlignment(Pos.CENTER);
            return root;

        } else { // The user's preference is complex.

            VBox root = new VBox();
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Complex Analytics Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            Button viewAnalytics = new Button("View Analytics");
            Button home = new Button("Home");
            Button toSimple = new Button("Simple");

            // Go Home, i.e. return to Welcome Screen.
            home.setOnAction(e -> {
                WelcomeScreen welcome = new WelcomeScreen(stage, user);
                stage.setScene(welcome.getScene());
            });

            // Switch to simple mode
            toSimple.setOnAction(e -> {
                user.updateAnalyticsComplexity(false);
                AnalyticsScreen simple = new AnalyticsScreen(stage, user);
                stage.setScene(simple.getScene());
            });

            root.getChildren().setAll(title, viewAnalytics, home, toSimple);
            root.setAlignment(Pos.CENTER);
            return root;
        }
    }
}
