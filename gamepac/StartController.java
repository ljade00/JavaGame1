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

public class StartController { // a controller for the start menu

	private Stage stage;
	private Scene scene;
	private Parent root;
	private static int x = 1;
	private static int y = 1;
	private static int currentMap = 1;
	private static int potionUses = 1;
	private static int healthPotions = 3;
	private static int prompt = 1;

	@FXML // switches to MapView when the start button in the main menu is hit
	void startButtonPressed(ActionEvent event) throws Exception {
		EscapeController.clearScore();
		Parent root = FXMLLoader.load(getClass().getResource("/res/screens/DifficultyScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// below are set and get for stored values

	public static int getX() {
		return x;
	}

	public static void setX(int newX) {
		x = newX;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int newY) {
		y = newY;
	}

	public static int getCurrentMap() {
		return currentMap;
	}

	public static void setCurrentMap(int newMap) {
		currentMap = newMap;
	}

	public static int getHealthPotions() {
		return healthPotions;
	}

	public static void setHealthPotions(int newHealthPotions) {
		healthPotions = newHealthPotions;
	}

	public static int getPotionUses() {
		return potionUses;
	}

	public static void setPotionUses(int newPotionUses) {
		potionUses = newPotionUses;
	}
	
	public static int getPrompt() {//to display enemies stronger message
		return prompt;
	}

	public static void setPrompt() {
		prompt++;
	}
	
	public static void clearPrompt() {
		prompt = 1;
	}
} // end of StartController
