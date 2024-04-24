package gamepac;

//import java.awt.event.ActionEvent;

import javafx.application.Application;
import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

// Initiates the program
public class Start extends Application {
	TileManager tileM = new TileManager(this);

	@Override
	public void start(Stage stage) throws Exception {

		UpdateHighScoreFile.loadHighScore();// Initiate the high score

		Parent root = FXMLLoader.load(getClass().getResource("/res/screens/StartMenu.fxml"));

		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("JAVA BEAST"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
	} // end of start

	public static void main(String[] args) {
		// create a StartScreen object and call its start method
		launch(args);
	}

} // end of start
