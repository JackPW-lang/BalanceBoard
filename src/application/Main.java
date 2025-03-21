package application;
	
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.Group;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400,Color.BLACK);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image("Logo3.jpg");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("BalanceBoard");
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	        primaryStage.setX(screenBounds.getMinX());
	        primaryStage.setY(screenBounds.getMinY());
	        primaryStage.setWidth(screenBounds.getWidth());
	        primaryStage.setHeight(screenBounds.getHeight());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
