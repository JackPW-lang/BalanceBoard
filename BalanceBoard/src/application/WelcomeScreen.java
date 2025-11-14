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

public class WelcomeScreen extends Window {

    // Fields

    // Constructors - once basic framework is established, implement the intro questionnaire within the WelcomeScreen constructor.
    public WelcomeScreen(Stage stage) {
        super(stage);
    }

    // Methods

    @Override
    public Parent createContent() { // Creates the content specific for the WelcomeScreen class.

        VBox root = new VBox();
        root.setStyle("-fx-background-color: black;");
        Label title = new Label("Welcome to BalanceBoard.");
        title.setStyle("-fx-text-fill: #AAAAAA;");

        // Screen Switching Buttons:
        Button toAgenda = new Button("Agenda");
        Button toSchedule = new Button("Schedule");
        Button toAnalytics = new Button("Analytics");

        // Navigate to Agenda
        toAgenda.setOnAction(e -> {
            AgendaScreen agenda = new AgendaScreen(stage);
            stage.setScene(agenda.getScene());
        });

        // Navigate to Schedule
        toSchedule.setOnAction(e -> {
            SchedulerScreen schedule = new SchedulerScreen(stage);
            stage.setScene(schedule.getScene());
        });

        // Navigate to Analytics
        toAnalytics.setOnAction(e -> {
            AnalyticsScreen analytics = new AnalyticsScreen(stage);
            stage.setScene(analytics.getScene());
        });

        root.getChildren().addAll(title, toAgenda, toAnalytics, toSchedule);
        root.setAlignment(Pos.CENTER);
        return root;
    }
}
