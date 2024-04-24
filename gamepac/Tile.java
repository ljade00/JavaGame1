package gamepac;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

public class Tile {
	//creates the outline for
	//tile objects
	public BufferedImage image;
	public boolean collision = false;
	public boolean monster = false;
	public boolean exit = false;
	public boolean escape = false;
	public boolean healthPotion = false;
}
