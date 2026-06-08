package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.util.Duration;

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

        taskContainer.getChildren().clear();
        taskContainer.setAlignment(Pos.CENTER);// Rid tree of nodes (tasks) before updating with the current list

        for (int i = 0; i < u.getTaskList().size(); i++) {
            Task t = u.getTaskList().get(i); // Iterating through every task the user has

            // taskRow - Each task has a dedicated row
            HBox taskRow = new HBox(10);
            taskRow.setStyle("-fx-background-color: #1e1e1e; -fx-background-radius: 12;");
            taskRow.setPadding(new Insets(12, 16, 12, 16));
            taskRow.maxWidthProperty().bind(stage.widthProperty().multiply(0.5));
            taskRow.setAlignment(Pos.CENTER);


            // Defining  a UI element for each characteristic of a given task - title, days left, completed.
            Text titleLabel = new Text(t.getTitle());
            titleLabel.setFill(Color.web("#AAAAAA"));

            Label daysLabel = new Label("("+t.getDaysRemaining() + " Days Left.)");
            daysLabel.setStyle("-fx-text-fill: #AAAAAA;");

            Button doneBtn = new Button();
            doneBtn.setStyle("-fx-background-radius: 8; -fx-min-width: 20; -fx-min-height: 20; " +
                    "-fx-max-width: 20; -fx-max-height: 20; " +
                    "-fx-background-color: transparent; -fx-border-color: grey; -fx-border-radius: 3;");

            // Apply strikethrough if completed to appear crossed out.
            if (t.getCompleted()) {
                titleLabel.setStrikethrough(true);
                doneBtn.setStyle("-fx-background-radius: 8; -fx-min-width: 20; -fx-min-height: 20; " +
                        "-fx-max-width: 20; -fx-max-height: 20; " +
                        "-fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 3;");
                daysLabel.setText("Done!");
            } else {
                titleLabel.setStyle("-fx-text-fill: #AAAAAA;");
            }

            Button deleteBtn = new Button("X");
            deleteBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff4444; -fx-font-size: 14;");
            deleteBtn.setOnAction(e -> {
                u.removeTask(t);
                refreshTaskList(u, taskContainer);
            });

            doneBtn.setOnAction(e -> {
                // u.removeTask(t);
                t.complete();
                refreshTaskList(u, taskContainer);
            });

            // Spacer for button to appear to right
            HBox spacer = new HBox();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            taskRow.getChildren().addAll(deleteBtn, titleLabel, daysLabel, spacer, doneBtn);
            taskContainer.getChildren().add(taskRow);


            // Fade Animation
            taskRow.setOpacity(0);
            FadeTransition fade = new FadeTransition(Duration.millis(500), taskRow);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.setDelay(Duration.millis(i * 200));
            fade.play();
        }
    }

    @Override
    public Parent createContent(User u) {

        Button add_Task = new Button("Add Task");
        Button home = new Button("Home");

        String btnStyle = "-fx-background-color: #1a1a1a; -fx-text-fill: #AAAAAA; -fx-font-family: 'Times New Roman'; -fx-font-size: 20;" +
                "-fx-background-radius: 12; -fx-border-color: #2a2a2a; " +
                "-fx-border-radius: 12; -fx-padding: 30 25 30 25; " +
                "-fx-font-size: 13; -fx-cursor: hand;";

        add_Task.setStyle(btnStyle);
        home.setStyle(btnStyle);

        HBox midContent = new HBox(20);
        midContent.setAlignment(Pos.CENTER);
        midContent.getChildren().addAll(add_Task, home);

        Label title = new Label("Your Agenda Today.");
        title.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 24;");

        // Title fade animation
        FadeTransition titleFade = new FadeTransition(Duration.millis(1500), title);
        titleFade.setFromValue(0.0);
        titleFade.setToValue(1.0);
        titleFade.play();

        Button[] buttons = {add_Task, home};
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
        VBox taskContainer = new VBox(10);
        //root.setCenter(taskContainer);

        VBox middle = new VBox();
        middle.setAlignment(Pos.CENTER);
        middle.setSpacing(40);
        middle.getChildren().addAll(title, midContent, taskContainer);
        root.setCenter(middle);

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

        //root.getChildren().setAll(title, add_Task, home, taskContainer);
        return root;
    }
}
