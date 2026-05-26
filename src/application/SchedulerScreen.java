package application;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.geometry.Pos;

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

        ButtonType createBtn = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createBtn, ButtonType.CANCEL);

        // input fields
        TextField titleField = new TextField();
        titleField.setPromptText("Event title (required)");

        TextField dateField = new TextField();
        dateField.setPromptText("Date (mm/dd; required)");

        TextField startTimeField = new TextField();
        startTimeField.setPromptText("Start Time (hh:mm; required)");

        TextField endTimeField = new TextField();
        endTimeField.setPromptText("End Time (hh:mm; required)");

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
                titleField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
            if (dateField.getText().isBlank()) {
                dateField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
            if (startTimeField.getText().isBlank()) {
                startTimeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
            if (endTimeField.getText().isBlank()) {
                endTimeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
            try {
                LocalDate.parse(dateField.getText() + "/" + LocalDate.now().getYear(),
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (Exception exc) { // invalid input
                dateField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
            try {
                LocalTime.parse(startTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception exc) {
                startTimeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
            try {
                LocalTime.parse(endTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception exc) {
                endTimeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                e.consume();
            }
        });
        return dialog.showAndWait().orElse(null);
    }

    private void refreshEventList(User u, VBox eventContainer) {

        eventContainer.getChildren().clear(); // Rid tree of nodes (event) before updating with the current list

        for(Event ev : u.getEventList()) { // Iterating through every task the user has

            // taskRow - Each task has a dedicated row
            HBox eventRow = new HBox(10);
            eventRow.setAlignment(Pos.BASELINE_CENTER);

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

            CheckBox doneBox = new CheckBox();
            doneBox.setSelected(ev.getCompleted());

            // When the user checks the doneBox, this removes the task from their list, and we refresh the list visually as well.
            doneBox.setOnAction(e -> {
                u.removeEvent(ev);
                refreshEventList(u, eventContainer);
            });

            // Adding the elements defined above to the scene graph
            eventRow.getChildren().addAll(titleLabel, dateLabel, startLabel, endLabel, doneBox);
            eventContainer.getChildren().add(eventRow);
        }
    }

    @Override
    public Parent createContent(User u) {

        if(!u.getComplexityPreferences()[2]) { // The user's preference is simple

            VBox root = new VBox();
            VBox eventContainer = new VBox(10);
            eventContainer.setFillWidth(true);
            root.setStyle("-fx-background-color: black;");
            Label title = new Label("(Simple Scheduler Screen)");
            title.setStyle("-fx-text-fill: #AAAAAA;");

            Button addEvent = new Button("Add Event");
            Button home = new Button("Home");
            Button toComplex = new Button("Complex");

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

            root.getChildren().addAll(title, addEvent, home, toComplex, eventContainer);
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
