package gamepac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
//import javax.xml.bind.JAXB;

public class UpdateHighScoreFile {

	private static double highScore;
	static String filePath = "/res/map/HighScore.txt";
	private static double damageDealtScore;
	private static double damageTakenScore;
	private static double friendshipScore;
	private static double healthHealedScore;
	private static double battleScore;

	public static void loadHighScore() {// reads the HighScore.txt file
										// and turns it into a double value
		try {
			InputStream is = UpdateHighScoreFile.class.getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String hold = br.readLine();
			System.out.println("Hold Value");
			System.out.println(hold);
			double oldHighScore = Double.parseDouble(hold);
			UpdateHighScoreFile.setHighScore(oldHighScore);
			br.close();
		} catch (Exception E) {
			System.out.println("error");
		}
	} // end of loadHighScore

	public static void updateHighScore(double newScore) {// update high score
		try { // working here
			double newHighScore = EscapeController.getScore();
			FileWriter myWriter = new FileWriter("src/res/map/HighScore.txt");
			myWriter.write(String.format("%f", newHighScore));
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
			System.out.println(newHighScore);
			UpdateHighScoreFile.setHighScore(newHighScore);
		} catch (IOException e) {
			System.out.println("break");
			e.printStackTrace();
		}
	} // end of setHighScore

	public static void setHighScore(double newValue) {// sets High score
		highScore = newValue;
		System.out.println("set High Score");
		System.out.println(highScore);
	}

	public static double getHighScore() {// return High score
		System.out.println("get High Score");
		System.out.println(highScore);
		return highScore;
	}

	public static void setBattleScore() {
		battleScore++;
	}

	public static void clearBattleScore() {
		battleScore = 0;
	}

	public static double getBattleScore() {
		return battleScore;
	}

	public static void setDamageDealtScore(double newValue) {
		damageDealtScore = damageDealtScore + newValue;
	}

	public static double getDamageDealtScore() {
		return damageDealtScore;
	}
	public static void clearDamageDealtScore() {
		damageDealtScore = 0;
	}

	public static void setDamageTakenScore(double newValue) {
		damageTakenScore = damageTakenScore + newValue;
	}

	public static double getDamageTakenScore() {
		return damageTakenScore;
	}
	public static void clearDamageTakenScore() {
		damageTakenScore = 0;
	}

	public static void setFriendshipScore(double newValue) {
		friendshipScore = friendshipScore + newValue;
	}

	public static double getFriendshipScore() {
		return friendshipScore;
	}
	public static void clearFriendshipScore() {
		friendshipScore = 0;
	}

	public static void setHealthHealedScore(double newValue) {
		healthHealedScore = healthHealedScore + newValue;
	}

	public static double getHealthHealedScore() {
		return healthHealedScore;
	}
	public static void clearHealthHealedScore() {
		healthHealedScore = 0;
	}

} // end of UpdateHighScoreFile
