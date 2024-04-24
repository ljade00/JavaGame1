package gamepac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleController {

	Player player = Player.getInstance();
	private Enemy enemy;

	@FXML
	private Button playerAttack;
	@FXML
	private Button playerServe;
	@FXML
	private Button playerHeal;
	@FXML
	private ProgressBar progressBarPlayer;
	@FXML
	private ProgressBar progressBarEnemy;
	@FXML
	private ProgressBar progressBarFriendship;
	// @FXML private TextArea display;

	@FXML
	public void initialize() {
		enemy = new Enemy();
		// enemy.updateAttributes();

		// Set initial values for health and friendship bars
		progressBarPlayer.setProgress(1.0);
		progressBarEnemy.setProgress(1.0);
		progressBarFriendship.setProgress(0.0);
		updateHealthBars();
	}

	@FXML // player hits attack button
	public void playerAttack(ActionEvent event) throws Exception {
		double eDamage = (player.attack() - enemy.getDef());
		enemy.takeDamage(eDamage);
		// update screen & clear old text
		updateHealthBars();
		removeTextNodes(event);

		displayText("You have done " + eDamage + " damage", 50, 50, event);
		UpdateHighScoreFile.setDamageDealtScore(eDamage);
		if (enemy.getHp() <= 0) {
			victory(event);
		} else {
			enemyAttack(event);
		}
	}

	@FXML // player hits the heal button
	public void playerHeal(ActionEvent event) throws Exception {
		double totalHeal = player.heal();
		removeTextNodes(event);
		if (totalHeal > 0) {
			displayText("You have healed " + totalHeal + " hit points\n" + "\n", 50, 50, event);
			UpdateHighScoreFile.setHealthHealedScore(totalHeal);
			enemyAttack(event);
		} else {
			displayText("No remaining Health Potions", 50, 50, event);
		}
	}

	@FXML // player hits the serve button
	public void playerServe(ActionEvent event) throws Exception {
		double friend = player.serve();
		enemy.friended(friend);
		updateHealthBars();
		updateFriendshipBar();
		removeTextNodes(event);
		displayText("You have attempted\nto befriend the enemy" + "\n", 50, 50, event);

		if (enemy.getFriend() >= 100) {
			victory(event);
		} else {
			enemyAttack(event);
		}
	}

	// enemy attacks
	public void enemyAttack(ActionEvent event) throws Exception {
		double pDamage = (enemy.attack() - player.getDef());
		player.takeDamage(pDamage);
		updateHealthBars();
		System.out.printf("Enemy HP: %f, Enemy atk: %f %n", enemy.getHp(), enemy.getAtk());

		String damageMessage = "\n\nEnemy has done " + pDamage + " damage";
		UpdateHighScoreFile.setDamageTakenScore(pDamage);
		displayText(damageMessage, 50, 60, event);

		if (player.getHp() <= 0) {
			defeated();
		}
	}

	void victory(ActionEvent event) throws Exception { // player wins
		Player.incrementVictoryCounter();
		UpdateHighScoreFile.setBattleScore();
		EscapeController.setScore();
		Parent root = FXMLLoader.load(getClass().getResource("/res/battle/VictoryScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private void displayText(String message, double layoutX, double layoutY, ActionEvent event) {
		Text text = createStyledText(message);
		text.setLayoutX(layoutX);
		text.setLayoutY(layoutY);
		AnchorPane root = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();
		root.getChildren().add(text);
	}

	private void removeTextNodes(ActionEvent event) {
		AnchorPane root = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();
		root.getChildren().removeIf(node -> node instanceof Text);
	}

	private Text createStyledText(String message) { // sets font attributes
		Text text = new Text(message);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Centaur", FontWeight.BOLD, 18));
		text.setStyle("-fx-background-color: black; -fx-background-radius: 5;");
		return text;
	}

	void returnToMapView(ActionEvent event) throws Exception { // returns to map
		Parent root = FXMLLoader.load(getClass().getResource("/res/map/MapTest.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	void defeated() throws IOException { // player is defeated
		player.resetHealth();
		Stage stage = (Stage) playerAttack.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/res/screens/DefeatedScene.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private void updateHealthBars() { // updates health bars
		double playerHealthPercentage = player.getHp() / player.getOriginalHp();
		double enemyHealthPercentage = enemy.getHp() / enemy.getOriginalHp();
		progressBarPlayer.setProgress(playerHealthPercentage);
		progressBarEnemy.setProgress(enemyHealthPercentage);
	}

	private void updateFriendshipBar() { // updates friendship bar
		double friendshipPercentage = enemy.getFriend() / 100.00;
		progressBarFriendship.setProgress(friendshipPercentage);
	}
}// end of BattleController