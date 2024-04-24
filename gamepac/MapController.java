package gamepac;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapController { // a controller for the map view

	private Stage stage;
	private Scene scene;
	private Parent root;

	Player player = Player.getInstance(); // get that singleton instance from Player class

	@FXML
	private GridPane gridPane;
	@FXML
	private Button startButton;
	@FXML
	private Button down;
	@FXML
	private Button left;
	@FXML
	private ImageView monster;
	@FXML
	private ImageView playerOnMap;
	@FXML
	private Button right;
	@FXML
	private Button up;
	@FXML
	private TextField playerInfo;
	@FXML
	private TextField potionInfo;

	///////////////////////////////////////////////////////////////////////
	Tile[] tile;
	int mapTileNum[][];

	char direnction = 'n';
	Start st;
	// creating a new instance of TileManager
	TileManager tileM = new TileManager(st);
	String infoHp = String.format("%.0f", player.getHp());
	String infoPot = String.format("%d", StartController.getHealthPotions());
	int potionUses = StartController.getPotionUses();

	public MapController(/* Start st */) {
		int prompt = 1;
		this.st = st;
		// loads the Map1.txt file
		if (StartController.getCurrentMap() == 1) {
			tileM.loadMap("/res/map/Map1.txt");
		}
		if (StartController.getCurrentMap() == 2) {
			tileM.loadMap("/res/map/Map2.txt");
		}
	}

	public void initialize() {// loads player position and display text
		GridPane.setConstraints(playerOnMap, StartController.getX(), StartController.getY());
		// StartController.getCurrentHealthPotions());
		playerInfo.setText("Health(" + (infoHp) + "/150) Health Potions(" + (infoPot) + "/3)");
		potionInfo.setText("   ");
		if (StartController.getCurrentMap() == 2 && StartController.getPrompt() == 2) {
			potionInfo.setText("The enemies grow stronger!");
			StartController.setPrompt();
		}
	}

	// MAP MOVEMENT/////////////////////////////////////////////////////////////////

	@FXML // moves the character up
	void up(ActionEvent event) throws IOException {
		setPlayerDirection('w');

		if (checkTileCollision(playerOnMap) == false) {
			StartController.setY(StartController.getY() - 1);
			GridPane.setConstraints(playerOnMap, StartController.getX(), StartController.getY());
			healthPickUp(event);
			escape(event);
			encounter(event);
			exit(event);
		}
	}

	@FXML // moves the character down
	void down(ActionEvent event) throws IOException {
		setPlayerDirection('s');

		if (checkTileCollision(playerOnMap) == false) {
			StartController.setY(StartController.getY() + 1);
			GridPane.setConstraints(playerOnMap, StartController.getX(), StartController.getY());
			healthPickUp(event);
			escape(event);
			encounter(event);
			exit(event);
		}
	}

	@FXML // moves the character left
	void left(ActionEvent event) throws IOException {
		setPlayerDirection('a');

		if (checkTileCollision(playerOnMap) == false) {
			StartController.setX(StartController.getX() - 1);
			GridPane.setConstraints(playerOnMap, StartController.getX(), StartController.getY());
			healthPickUp(event);
			escape(event);
			encounter(event);
			exit(event);
		}
	}

	@FXML // moves the character right
	void right(ActionEvent event) throws IOException {
		setPlayerDirection('d');

		if (checkTileCollision(playerOnMap) == false) {
			StartController.setX(StartController.getX() + 1);
			GridPane.setConstraints(playerOnMap, StartController.getX(), StartController.getY());
			healthPickUp(event);
			escape(event);
			encounter(event);
			exit(event);
		}
	}

	// checks if the player is in the same position as a monster
	void encounter(ActionEvent event) throws IOException {
		int playerMapX = getPlayerX();
		int playerMapY = getPlayerY();
		int mapTileNum;
		mapTileNum = tileM.getMapTileNum(playerMapX, playerMapY);
		if (tileM.tile[mapTileNum].monster == true) {
			Parent root = FXMLLoader.load(getClass().getResource("/res/battle/BattleScene1.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	// checks if the player is in the same position as an exit(next stage)
	void exit(ActionEvent event) throws IOException {
		int playerMapX = getPlayerX();
		int playerMapY = getPlayerY();
		int mapTileNum;
		mapTileNum = tileM.getMapTileNum(playerMapX, playerMapY);
		if (tileM.tile[mapTileNum].exit == true) {
			StartController.setPrompt();
			StartController.setPotionUses(1);
			System.out.println("EXIT");
			StartController.setCurrentMap(StartController.getCurrentMap() + 1);
			Parent root = FXMLLoader.load(getClass().getResource("/res/map/MapTest2.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	// checks if the player is in the same position as an escape (victory)
	void escape(ActionEvent event) throws IOException {
		int playerMapX = getPlayerX();
		int playerMapY = getPlayerY();
		int mapTileNum;
		mapTileNum = tileM.getMapTileNum(playerMapX, playerMapY);
		if (tileM.tile[mapTileNum].escape == true) {
			System.out.println("Escape");
			Parent root = FXMLLoader.load(getClass().getResource("/res/screens/EscapeScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	// restores the players health potions if the player is in the same position
	void healthPickUp(ActionEvent event) throws IOException {
		int playerMapX = getPlayerX();
		int playerMapY = getPlayerY();
		int mapTileNum;
		mapTileNum = tileM.getMapTileNum(playerMapX, playerMapY);
		if (tileM.tile[mapTileNum].healthPotion == true) {
			if (potionUses == 1) {
				System.out.println(playerOnMap);
				StartController.setHealthPotions(3);
				double tempHeal = 0;
				tempHeal = player.getOriginalHp() - player.getHp();// amount healed
				UpdateHighScoreFile.setHealthHealedScore(tempHeal);
				player.resetHealth();
				infoHp = String.format("%.0f", player.getHp());
				infoPot = String.format("%d", StartController.getHealthPotions());
				System.out.println("Health potions refilled");
				playerInfo.setText("Health(" + (infoHp) + "/150) Health Potions(" + (infoPot) + "/3)");
				potionInfo.setText("Health and Health Potions restored");
				StartController.setPotionUses(0);// only one use per stage
			} else {
				potionInfo.setText("Health Pickup already used");
			}
		}
	}

	Integer getPlayerY() {// returns players row position
		return GridPane.getRowIndex(playerOnMap);
	}

	Integer getPlayerX() {// returns players column position
		return GridPane.getColumnIndex(playerOnMap);
	}

	char getPlayerDirection() {
		// returns players input direction
		return direnction;
	}

	void setPlayerDirection(char dr) {
		// sets players input direction
		direnction = dr;
	}

	// checks if the players movement is valid
	public boolean checkTileCollision(ImageView playerOnMap) {
		int playerMapX = getPlayerX();
		int playerMapY = getPlayerY();
		int mapTileNum;

		switch (getPlayerDirection()) {

		case 'w':// Up movement
			mapTileNum = tileM.getMapTileNum(playerMapX, playerMapY - 1);

			if (tileM.tile[mapTileNum].collision == true) {
				System.out.println("T");
				return true;
			} else {
				System.out.println("F");
				return false;
			}

		case 'a':// Left movement
			mapTileNum = tileM.getMapTileNum(playerMapX - 1, playerMapY);

			if (tileM.tile[mapTileNum].collision == true) {
				System.out.println("T");
				return true;
			} else {
				System.out.println("F");
				return false;
			}

		case 's':// Down movement
			mapTileNum = tileM.getMapTileNum(playerMapX, playerMapY + 1);

			if (tileM.tile[mapTileNum].collision == true) {
				System.out.println("T");
				return true;
			} else {
				System.out.println("F");
				return false;
			}

		case 'd':// Right movement
			mapTileNum = tileM.getMapTileNum(playerMapX + 1, playerMapY);

			if (tileM.tile[mapTileNum].collision == true) {
				System.out.println("T");
				return true;
			} else {
				System.out.println("F");
				return false;
			}
		default:
			return false;
		}
	}// end of checkTileCollision
}// end of MapController
