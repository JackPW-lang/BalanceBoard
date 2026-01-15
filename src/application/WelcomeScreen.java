package application;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class WelcomeScreen extends Window {

    // Fields

    // Constructors - once basic framework is established, implement the intro questionnaire within the WelcomeScreen constructor.
    public WelcomeScreen(Stage stage, User user) {
        super(stage, user);
    }

    // Methods

    @Override
    public Parent createContent(User u) { // Creates the content specific for the WelcomeScreen class.

        if (!u.getComplexityPreferences()[0]) { // The preference is Simple

            VBox root = new VBox();
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Simple Welcome Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            // Screen Switching Buttons:
            Button toAgenda = new Button("Agenda");
            Button toSchedule = new Button("Schedule");
            Button toAnalytics = new Button("Analytics");
            Button toComplex = new Button("Complex Mode");

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

            root.getChildren().addAll(title, toAgenda, toAnalytics, toSchedule, toComplex);
            root.setAlignment(Pos.CENTER);
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