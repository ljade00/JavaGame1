package gamepac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DefeatedController { // a controller for the defeated menu

	private Stage stage;
	private Scene scene;
	private Parent root;

	private int reset = 1;

	@FXML // switches to StartMenu when the defeated button is hit
	void defeatedButtonPressed(ActionEvent event) throws Exception {
		//resets values
		StartController.setCurrentMap(reset);
		StartController.setX(reset);
		StartController.setY(reset);
		StartController.setHealthPotions(reset * 3);
		StartController.setPotionUses(reset);
		UpdateHighScoreFile.clearBattleScore();
		UpdateHighScoreFile.clearDamageDealtScore();
		UpdateHighScoreFile.clearDamageTakenScore();
		UpdateHighScoreFile.clearFriendshipScore();
		UpdateHighScoreFile.clearHealthHealedScore();
		StartController.clearPrompt();
		Parent root = FXMLLoader.load(getClass().getResource("/res/screens/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
} // end of DefeatedController
