package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ButtonBar;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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

    // Dialog class is used in order to create a small popup to collect the info regarding a new task which is then directly passed into the Task Constructor
    private Task showCreateTaskDialog() {

        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("Create New Task");

        ButtonType createBtn = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createBtn, ButtonType.CANCEL);

        // input fields
        TextField titleField = new TextField();
        titleField.setPromptText("Task title");

        TextField daysField = new TextField();
        daysField.setPromptText("Days remaining");

        CheckBox recurringBox = new CheckBox("Recurring task");

        VBox layout = new VBox(10, titleField, daysField, recurringBox);
        layout.setStyle("-fx-padding: 20;");
        dialog.getDialogPane().setContent(layout);

        dialog.setResultConverter(button -> {
            if (button == createBtn) {
                try {
                    return new Task(
                            titleField.getText(),
                            Integer.parseInt(daysField.getText()),
                            recurringBox.isSelected()
                    );
                } catch (Exception e) {
                    return null; // invalid input
                }
            }
            return null;
        });
        return dialog.showAndWait().orElse(null);
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
            if(t != null) { u.addTask(t); } // we do not want to add null tasks to the list.
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
