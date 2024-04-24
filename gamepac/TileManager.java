package gamepac;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class TileManager {

	Tile[] tile;
	Start st;
	int[][] mapTileNum;
	int row1 = 9;
	int col1 = 15;

	public TileManager(Start st) {

		this.st = st;
		tile = new Tile[10];// array to hold unique tiles
		mapTileNum = new int[col1][row1];// array to store map tile information
		getTileImage();
	}

	private void getTileImage() {// sets the collision values for each tile

		try {
			tile[0] = new Tile();// floor
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/map/floor.png"));

			tile[1] = new Tile();// wall
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/map/DarkBrickWall.png"));
			tile[1].collision = true;

			tile[2] = new Tile();// enemy
			tile[2].monster = true;

			tile[3] = new Tile();// exit
			tile[3].exit = true;

			tile[4] = new Tile();// final exit
			tile[4].escape = true;

			tile[5] = new Tile();// health pickup
			tile[5].healthPotion = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {// reads the Map.txt file and
		// turns it into a readable int grid
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < col1 && row < row1) {
				String line = br.readLine();
				while (col < col1) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					System.out.print(mapTileNum[col][row]);
					col++;
				}
				if (col == col1) {
					col = 0;
					row++;
					System.out.println();
				}
			}
			br.close();
		} catch (Exception E) {
			System.out.println("break");
		}
	} // end of load map

	public int getMapTileNum(int col, int row) {
		return (mapTileNum[col][row]);
	}

}// end of TileManager
