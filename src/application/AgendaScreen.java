package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

public class AgendaScreen extends Window {

    // Fields inherited from Window: scene, stage, user

    // Constructors
    public AgendaScreen(Stage stage, User user) {
        super(stage, user);
    }

    // Methods

    // Dialog class is used in order to create a small popup to collect the info regarding a new task which is then directly passed into the Task Constructor
    private Task showCreateTaskDialog() {

        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("Create New Task");

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
        titleField.setPromptText("Task title (required)");
        titleField.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #888888;"
        );

        TextField daysField = new TextField();
        daysField.setPromptText("Days remaining (required)");
        daysField.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #888888;"
        );

        CheckBox recurringBox = new CheckBox("Recurring task?");
        recurringBox.setStyle(
                "-fx-background-color: #222222;" +
                        "-fx-text-fill: #888888;"
        );

        VBox layout = new VBox(10, titleField, daysField, recurringBox);
        layout.setStyle("-fx-padding: 20;");
        dialog.getDialogPane().setContent(layout);


        dialog.setResultConverter(button -> { // inputs: any button clicked on addTask popup
            if (button == createBtn) {
                try {
                    return new Task(
                            titleField.getText(),
                            Integer.parseInt(daysField.getText()),
                            recurringBox.isSelected()
                    );
                } catch (Exception e) { // note that emptiness also counts as an exception when using parseInt.
                    return null; // invalid input
                }
            }
            return null;
        });

        // add here the logic to run if one or both required fields (daysRemaining, title) are not filled.
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
            if (daysField.getText().isBlank()) {
                daysField.setStyle(
                        "-fx-background-color: #222222;" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #888888;" +
                                "-fx-border-color: red; -fx-border-width: 2px;"
                );
                e.consume();
            } else {
                try {
                    Integer.parseInt(daysField.getText());
                } catch (NumberFormatException exception) {
                    daysField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    e.consume();
                }
            }
        });

        return dialog.showAndWait().orElse(null); // User hits Create -> result. If close or cancel -> return null
        // null returned if user closes or hits cancel, or returning a task creates an exception, or if button clicked is not create.
    }

    // refreshTaskList Method to convert the ArrayList field in the user class to UI elements
    private void refreshTaskList(User u, VBox taskContainer) {

        taskContainer.getChildren().clear(); // Rid tree of nodes (tasks) before updating with the current list

        for(Task t : u.getTaskList()) { // Iterating through every task the user has

            // taskRow - Each task has a dedicated row
            HBox taskRow = new HBox(10);
            taskRow.setAlignment(Pos.BASELINE_CENTER);

            // Defining  a UI element for each characteristic of a given task - title, days left, completed.
            Label titleLabel = new Label(t.getTitle());
            titleLabel.setStyle("-fx-text-fill: #AAAAAA;");

            Label daysLabel = new Label("("+t.getDaysRemaining() + " Days Left.)");
            daysLabel.setStyle("-fx-text-fill: #AAAAAA;");

            CheckBox doneBox = new CheckBox();
            doneBox.setSelected(t.getCompleted());

            // When the user checks the doneBox, this removes the task from their list, and we refresh the list visually as well.
            doneBox.setOnAction(e -> {
                u.removeTask(t);
                refreshTaskList(u, taskContainer);
            });

            // Adding the elements defined above to the scene graph
            taskRow.getChildren().addAll(titleLabel, daysLabel, doneBox);
            taskContainer.getChildren().add(taskRow);
        }
    }

    @Override
    public Parent createContent(User u) {

        VBox root = new VBox();
        VBox taskContainer = new VBox(10);
        root.setStyle("-fx-background-color: black;");
        Label title = new Label("(Agenda Screen)");
        title.setStyle("-fx-text-fill: #AAAAAA;");

        Button add_Task = new Button("Add Task");
        Button home = new Button("Home");

        // Add a new Task
        add_Task.setOnAction(e -> {
            Task t = showCreateTaskDialog();
            if (t != null) {
                    u.addTask(t);
            }// we do not want to add null tasks to the list.
            refreshTaskList(u, taskContainer);
        });

        // Go Home i.e. to Welcome Screen
        home.setOnAction(e -> {
            WelcomeScreen welcome = new WelcomeScreen(stage, user);
            stage.setScene(welcome.getScene());
        });
        // Initial rendering of the Task List
        refreshTaskList(u, taskContainer);

        root.getChildren().setAll(title, add_Task, home, taskContainer);
        root.setAlignment(Pos.CENTER);
        return root;
    }
}
