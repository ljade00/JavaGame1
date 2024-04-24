package gamepac;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DifficultyController {

	@FXML
	private ImageView difficultyImageView;
	@FXML
	private Button difficultyInfernoButton;
	@FXML
	private Button difficultyNightmareButton;
	@FXML
	private Button difficultyNormalButton;
	@FXML
	private Label selectDifficultyLabel;
	@FXML
	private AnchorPane difficultyPane;

	private Stage stage;

	private void loadViewController() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/res/map/MapTest.fxml"));
		stage = (Stage) difficultyPane.getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void difficultyNormalButtonPressed() throws IOException {
		System.out.println("Normal difficulty selected!");
		Enemy.setDifficultyModifier(0.8);
		loadViewController();
	}

	@FXML
	void difficultyNightmareButtonPressed() throws IOException {
		System.out.println("Nightmare difficulty selected!");
		Enemy.setDifficultyModifier(1);
		loadViewController();
	}

	@FXML
	void difficultyInfernoButtonPressed() throws IOException {
		System.out.println("Inferno difficulty selected!");
		Enemy.setDifficultyModifier(1.2);
		loadViewController();
	}

} // end of DifficultyController
