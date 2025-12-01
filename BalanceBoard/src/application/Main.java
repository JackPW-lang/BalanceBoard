package application;
	
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			// Welcome Screen is launched first
			User user = new User("Jack", true);
			WelcomeScreen welcome = new WelcomeScreen(primaryStage, user);
			primaryStage.setScene(welcome.getScene());
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
