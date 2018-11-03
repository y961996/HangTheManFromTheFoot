package hangTheManFromTheFoot.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class KeyboardKey{

	public static String[] englishAlphabetLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
											   "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
											   "U", "V", "W", "X", "Y", "Z", };
	
	private int x, y;
	private int width, height;
	private String keyLetter;
	private boolean keyPressed;
	private boolean width_height_updated;
	
	private Rectangle boundries;
	private BufferedImage keyImage;
	private BufferedImage keyPressedImage;
	
	public KeyboardKey(String keyLetter, int x, int y) {
		keyImage = StaticResourceLoader.keyImage;
		keyPressedImage = StaticResourceLoader.keyPressedImage;
		
		this.x = x;
		this.y = y;
		this.keyLetter = keyLetter;
		this.width = keyImage.getWidth();
		this.height = keyImage.getHeight();
		boundries = new Rectangle(this.x, this.y, this.width, this.height);
		
		width_height_updated = false;
		keyPressed = false;
	}
	
	public KeyboardKey(String keyLetter, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.keyLetter = keyLetter;
		boundries = new Rectangle(this.x, this.y, this.width, this.height);
		
		keyImage = StaticResourceLoader.keyImage;
		keyPressedImage = StaticResourceLoader.keyPressedImage;
	}
	
	public void update() {
		if(keyPressed && !width_height_updated) {
			this.width = keyPressedImage.getWidth();
			this.height = keyPressedImage.getHeight();
			width_height_updated = true;
		}
	}
	
	public void render(Graphics g) {
		if(keyPressed) {
			g.drawImage(keyPressedImage, x, y, width, height, null);
		}else {
			g.drawImage(keyImage, x, y, width, height, null);
		}
		g.drawString(keyLetter, x + 18, y + 40);
	}

	public boolean intersects(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		if(rect.intersects(boundries)) {
			return true;
		}
		return false;
	}
	
	public String getKeyLetter() {
		return keyLetter;
	}
	
	public void setKeyPressed() {
		keyPressed = true;
	}
	
	public boolean isKeyPressed() {
		return keyPressed;
	}
	
}
