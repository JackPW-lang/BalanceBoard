package application;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public abstract class Window {

    // An abstract class which has subclasses WelcomeScreen, AgendaScreen and ScheduleScreen.

    // Fields
    protected Stage stage;
    protected Scene scene;

    // Constructors
    public Window(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(createContent(), 800, 600, Color.BLACK); // parameters: Parent (root node of Scene Graph), width, height.
        stage.setTitle("BalanceBoard");
        stage.getIcons().add(new Image("Logo3.jpg"));
    }

    // Methods
    public Scene getScene() {
        return this.scene;
    }

    // This method is to be implemented differently in each child class: WelcomeScreen, AgendaScreen & ScheduleScreen.
    public abstract Parent createContent();
}
