package application;

import javafx.animation.FadeTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class WelcomeScreen extends Window {

    // Fields inherited from Window: scene, stage, user

    // Constructors - once basic framework is established, implement the intro questionnaire within the WelcomeScreen constructor.
    public WelcomeScreen(Stage stage, User user) {
        super(stage, user);
    }

    // Methods

    @Override
    public Parent createContent(User u) { // Creates the content specific for the WelcomeScreen class.

        if (!u.getComplexityPreferences()[0]) { // The preference is Simple

            // Button definitions
            Button toAgenda = new Button("Agenda");
            Button toSchedule = new Button("Schedule");
            Button toAnalytics = new Button("Analytics");
            Button toComplex = new Button("Switch to Complex Mode (Not Yet Implemented.)");

            // Button Style
            toComplex.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-family: 'System';" +
                    "-fx-font-size: 11; -fx-cursor: hand;");
            String btnStyle = "-fx-background-color: #1a1a1a; -fx-text-fill: #AAAAAA; -fx-font-family: 'System'; -fx-font-size: 16;" +
                    "-fx-background-radius: 12; -fx-border-color: #2a2a2a; " +
                    "-fx-border-radius: 12; -fx-padding: 30 25 30 25; " +
                    " -fx-cursor: hand;";

            // ensures all buttons are the same size
            toAgenda.setMinSize(120, 90);
            toSchedule.setMinSize(120, 90);
            toAnalytics.setMinSize(120, 90);

            toAgenda.setStyle(btnStyle);
            toSchedule.setStyle(btnStyle);
            toAnalytics.setStyle(btnStyle);

            // Navigation buttons
            HBox navButtons = new HBox(20);
            navButtons.setAlignment(Pos.CENTER);
            navButtons.getChildren().addAll(toAgenda, toSchedule, toAnalytics);

            // Welcome message
            Label title = new Label("Welcome, "+u.getName()+".");
            title.setStyle("-fx-text-fill: white; -fx-font-family: 'System'; -fx-font-size: 24;");

            // Title fade animation
            FadeTransition titleFade = new FadeTransition(Duration.millis(1500), title);
            titleFade.setFromValue(0.0);
            titleFade.setToValue(1.0);
            titleFade.play();

            Button[] buttons = {toAgenda, toSchedule, toAnalytics};
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setOpacity(0);
                FadeTransition fade = new FadeTransition(Duration.millis(500), buttons[i]);
                fade.setFromValue(0.0);
                fade.setToValue(1.0);
                fade.setDelay(Duration.millis(i * 300));
                fade.play();
            }

            BorderPane root = new BorderPane();
            root.setStyle("-fx-background-color: black;");
            root.setCenter(navButtons);
            root.setBottom(toComplex);

            VBox welcome = new VBox();
            welcome.setAlignment(Pos.CENTER);
            welcome.setSpacing(40);
            welcome.getChildren().addAll(title, navButtons);
            root.setCenter(welcome);

            // Navigate to Agenda
            toAgenda.setOnAction(e -> {
                AgendaScreen agenda = new AgendaScreen(stage, user);
                stage.setScene(agenda.getScene());
            });

            // Navigate to Schedule
            toSchedule.setOnAction(e -> {
                SchedulerScreen schedule = new SchedulerScreen(stage, user);
                stage.setScene(schedule.getScene());
            });

            // Navigate to Analytics
            toAnalytics.setOnAction(e -> {
                AnalyticsScreen analytics = new AnalyticsScreen(stage, user);
                stage.setScene(analytics.getScene());
            });

            // Switch to Complex Mode - this button will need to update the preference field in the User class, as well as construct a new screen with this in mind.
            toComplex.setOnAction(e -> {
                user.updateWelcomeComplexity(true);
                WelcomeScreen complex = new WelcomeScreen(stage, user);
                stage.setScene(complex.getScene());
            });
            return root;

        } else { // The preference is Complex

            VBox root = new VBox();
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Complex Welcome Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            // Screen Switching Buttons:
            Button toAgenda = new Button("Agenda");
            Button toSchedule = new Button("Schedule");
            Button toAnalytics = new Button("Analytics");
            Button toSimple = new Button("Simple Mode");

            // Navigate to Agenda
            toAgenda.setOnAction(e -> {
                AgendaScreen agenda = new AgendaScreen(stage, user);
                stage.setScene(agenda.getScene());
            });

            // Navigate to Schedule
            toSchedule.setOnAction(e -> {
                SchedulerScreen schedule = new SchedulerScreen(stage, user);
                stage.setScene(schedule.getScene());
            });

            // Navigate to Analytics
            toAnalytics.setOnAction(e -> {
                AnalyticsScreen analytics = new AnalyticsScreen(stage, user);
                stage.setScene(analytics.getScene());
            });

            // Switch to Simple Mode - this button will need to update the preference field in the User class, as well as construct a new screen with this in mind.
            toSimple.setOnAction(e -> {
                user.updateWelcomeComplexity(false); // Update the user's preference by modifying the field.
                WelcomeScreen simple = new WelcomeScreen(stage, user);
                stage.setScene(simple.getScene());
            });

            root.getChildren().addAll(title, toAgenda, toAnalytics, toSchedule, toSimple);
            root.setAlignment(Pos.CENTER);
            return root;
        }
    }
}