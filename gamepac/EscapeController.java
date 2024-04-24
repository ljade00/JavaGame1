package gamepac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EscapeController { // a controller for the escape menu

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private TextField battleScore;
	@FXML
	private Label byLabel;
	@FXML
	private TextField damageDealtScore;
	@FXML
	private TextField damageTakenScore;
	@FXML
	private Button escapeSceneButton;
	@FXML
	private ImageView escapeSceneImageView;
	@FXML
	private Label escapeSceneLabel;
	@FXML
	private TextField friendshipScore;
	@FXML
	private TextField healthHealedScore;
	@FXML
	private TextField highScore;
	@FXML
	private TextField runScore;

	private int reset = 1;
	private static double score;

	Player player = Player.getInstance(); // get that singleton instance from Player class

	public void initialize() {// loads score and high score
		// UpdateHighScoreFile.loadHighScore();//Initiate the high score
		double highScoreV = UpdateHighScoreFile.getHighScore();// get the high score
		if (score > highScoreV) {// if score is bigger then high score
			System.out.printf("%f > %f %n", score, highScoreV);
			UpdateHighScoreFile.updateHighScore(score);// set the high score
			highScoreV = UpdateHighScoreFile.getHighScore();// get the new high score
		} else {

		}
		runScore.setText("Run Score: " + (score));
		highScore.setText("High Score: " + (highScoreV));
		battleScore.setText("Battles Won: " + (UpdateHighScoreFile.getBattleScore()));
		damageDealtScore.setText("Damage Dealt: " + (UpdateHighScoreFile.getDamageDealtScore()));
		damageTakenScore.setText("Damage Taken: " + (UpdateHighScoreFile.getDamageTakenScore()));
		friendshipScore.setText("Friendship Spread: " + (UpdateHighScoreFile.getFriendshipScore()));
		healthHealedScore.setText("Health Healed: " + (UpdateHighScoreFile.getHealthHealedScore()));
	}

	@FXML // switches to StartMenu when the play again button is hit
	void escapeButtonPressed(ActionEvent event) throws Exception {
		// resets values
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
		player.resetHealth();
		Parent root = FXMLLoader.load(getClass().getResource("/res/screens/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public static double getScore() {
		return score;
	}

	public static void setScore() {// calculates score
		score = score + (100 * Enemy.getDifficultyModifier());
	}

	public static void clearScore() {// resets the score for a new run
		score = 0;
	}

} // end of EscapeController
