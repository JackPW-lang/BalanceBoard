package application;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.geometry.Pos;
import javafx.util.Duration;

import javax.naming.ldap.StartTlsRequest;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SchedulerScreen extends Window {

    // Fields inherited from Window Class: scene, stage, user

    // Constructors
    public SchedulerScreen (Stage stage, User user) {
        super(stage, user);
    }

    // Methods
    private Event showCreateEventDialog() {

        Dialog<Event> dialog = new Dialog<>();
        dialog.setTitle("Create New Event");

        DialogPane dialogpane = dialog.getDialogPane();
        dialogpane.setStyle("-fx-background-color: #2C2F33;");

        ButtonType createBtn = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createBtn, ButtonType.CANCEL);

        Button createButton = (Button) dialog.getDialogPane().lookupButton(createBtn);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);

        createButton.setStyle(
                "-fx-background-color: #4CAF50;" +  // green
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        cancelButton.setStyle(
                "-fx-background-color: #555555;" +  // grey
                        "-fx-text-fill: white;"
        );

        // input fields
        TextField titleField = new TextField();
        titleField.setPromptText("Event title (required)");
        titleField.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #888888;"
        );

        TextField dateField = new TextField();
        dateField.setPromptText("Date (mm/dd; required)");
        dateField.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #888888;"
        );

        TextField startTimeField = new TextField();
        startTimeField.setPromptText("Start Time (hh:mm; required)");
        startTimeField.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #888888;"
        );

        TextField endTimeField = new TextField();
        endTimeField.setPromptText("End Time (hh:mm; required)");
        endTimeField.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #888888;"
        );

        CheckBox recurringBox = new CheckBox("Weekly event");

        VBox layout = new VBox(10, titleField, dateField, startTimeField, endTimeField, recurringBox);
        layout.setStyle("-fx-padding: 20;");
        dialog.getDialogPane().setContent(layout);

        dialog.setResultConverter(button -> {
            if (button == createBtn) {
                try {
                    return new Event(
                            titleField.getText(),
                            LocalTime.parse(startTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")),
                            LocalTime.parse(endTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")),
                            LocalDate.parse(dateField.getText() + "/" + LocalDate.now().getYear(),
                                    DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                            recurringBox.isSelected()
                    );
                } catch (Exception e) {
                    System.out.println("Invalid");
                    return null; // invalid input
                }
            }
            return null;
        });

        Button createBtnNode = (Button) dialog.getDialogPane().lookupButton(createBtn);
        createBtnNode.addEventFilter(ActionEvent.ACTION, e -> {
            if (titleField.getText().isBlank()) {
                titleField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
            if (dateField.getText().isBlank()) {
                dateField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
            if (startTimeField.getText().isBlank()) {
                startTimeField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
            if (endTimeField.getText().isBlank()) {
                endTimeField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
            try {
                LocalDate.parse(dateField.getText() + "/" + LocalDate.now().getYear(),
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (Exception exc) { // invalid input
                dateField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
            try {
                LocalTime.parse(startTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception exc) {
                startTimeField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
            try {
                LocalTime.parse(endTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception exc) {
                endTimeField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            }
        });
        return dialog.showAndWait().orElse(null);
    }

    private void refreshEventList(User u, VBox eventContainer) {

        eventContainer.getChildren().clear(); // Rid tree of nodes (event) before updating with the current list
        eventContainer.setAlignment(Pos.CENTER);

        for (int i = 0; i < u.getEventList().size(); i++) {
            Event ev = u.getEventList().get(i); // Iterating through every task the user has

            // taskRow - Each task has a dedicated row
            HBox eventRow = new HBox(10);
            eventRow.setStyle("-fx-background-color: #1e1e1e; -fx-background-radius: 12;");
            eventRow.setPadding(new Insets(12, 16, 12, 16));
            eventRow.maxWidthProperty().bind(stage.widthProperty().multiply(0.5));
            eventRow.setAlignment(Pos.CENTER);

            // Defining  a UI element for each characteristic of a given task - title, days left, completed.
            Label titleLabel = new Label(ev.getTitle()+":");
            titleLabel.setStyle("-fx-text-fill: #AAAAAA;");

            Label dateLabel;
            dateLabel = new Label(ev.getDate().toString());
            dateLabel.setStyle("-fx-text-fill: #AAAAAA;");

            Label startLabel;
            startLabel = new Label(ev.getStart().toString()+"  to");
            startLabel.setStyle("-fx-text-fill: #AAAAAA;");

            Label endLabel;
            endLabel = new Label(ev.getEnd().toString());
            endLabel.setStyle("-fx-text-fill: #AAAAAA;");

            Button deleteBtn = new Button("🗑️");
            deleteBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff4444; -fx-font-size: 14;");
            deleteBtn.setOnAction(e -> {
                u.removeEvent(ev);
                refreshEventList(u, eventContainer);
            });

            Button doneBtn = new Button();
            doneBtn.setStyle("-fx-background-radius: 8; -fx-min-width: 20; -fx-min-height: 20; " +
                    "-fx-max-width: 20; -fx-max-height: 20; " +
                    "-fx-background-color: transparent; -fx-border-color: grey; -fx-border-radius: 3;");

            // When the user checks the doneBox, this removes the task from their list, and we refresh the list visually as well.
            doneBtn.setOnAction(e -> {
                u.removeEvent(ev);
                refreshEventList(u, eventContainer);
            });

            // Spacer that enables doneBtn to be on right hand side
            HBox spacer = new HBox();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            // Adding the elements defined above to the scene graph
            eventRow.getChildren().addAll(deleteBtn, titleLabel, dateLabel, startLabel, endLabel, spacer, doneBtn);
            eventContainer.getChildren().add(eventRow);

            // Fade animation
            eventRow.setOpacity(0);
            FadeTransition fade = new FadeTransition(Duration.millis(500), eventRow);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.setDelay(Duration.millis(i * 200));
            fade.play();
        }
    }

    @Override
    public Parent createContent(User u) {

        if(!u.getComplexityPreferences()[2]) { // The user's preference is simple

            //eventContainer.setFillWidth(true);

            Button addEvent = new Button("Add Event");
            Button home = new Button("Home");
            Button toComplex = new Button("Switch to Complex Mode (Not Yet Implemented.)");

            toComplex.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-family: 'Times New Roman';" +
                    "-fx-font-size: 11; -fx-cursor: hand;");
            String btnStyle = "-fx-background-color: #1a1a1a; -fx-text-fill: #AAAAAA; -fx-font-family: 'Times New Roman'; -fx-font-size: 20;" +
                    "-fx-background-radius: 12; -fx-border-color: #2a2a2a; " +
                    "-fx-border-radius: 12; -fx-padding: 30 25 30 25; " +
                    "-fx-font-size: 13; -fx-cursor: hand;";

            home.setStyle(btnStyle);
            addEvent.setStyle(btnStyle);

            HBox midContent = new HBox(20);
            midContent.setAlignment(Pos.CENTER);
            midContent.getChildren().addAll(addEvent, home);

            Button[] buttons = {addEvent, home};
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
            VBox eventContainer = new VBox(10);
            root.setCenter(eventContainer);

            Label title = new Label("Your Upcoming Events.");
            title.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 24;");

            VBox middle = new VBox();
            middle.setAlignment(Pos.CENTER);
            middle.setSpacing(40);
            middle.getChildren().addAll(title, midContent, eventContainer);
            root.setCenter(middle);

            root.setBottom(toComplex);

            // Go Home, i.e. go to Welcome Screen
            home.setOnAction(e -> {
                WelcomeScreen welcome = new WelcomeScreen(stage, user);
                stage.setScene(welcome.getScene());
            });

            // Add a new Event
            addEvent.setOnAction(e -> {
                Event ev = showCreateEventDialog();
                if(ev != null) { u.addEvent(ev); }
                refreshEventList(u, eventContainer);
            });

            // Switch to Complex
            toComplex.setOnAction(e -> {
                user.updateSchedulerComplexity(true);
                SchedulerScreen complex = new SchedulerScreen(stage, user);
                stage.setScene(complex.getScene());
            });

            // Initial rendering of the Task List
            refreshEventList(u, eventContainer);

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
