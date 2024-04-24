package gamepac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class VictoryController { // a controller for the start menu
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML // switches to MapView when the play again button in the main menu is hit
    void victoryButtonPressed(ActionEvent event) throws Exception  {
		if(StartController.getCurrentMap() == 1) {
    	root = FXMLLoader.load(getClass().getResource("/res/map/MapTest.fxml"));
		}
		if(StartController.getCurrentMap() == 2) {
	    root = FXMLLoader.load(getClass().getResource("/res/map/MapTest2.fxml"));
			}
   	 	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	 	scene = new Scene(root);
   	 	stage.setScene(scene);
	 	stage.show();
    }
} // end of VictoryController
