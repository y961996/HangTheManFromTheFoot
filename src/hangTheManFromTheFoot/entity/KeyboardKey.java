package hangTheManFromTheFoot.entity;

import java.awt.Color;
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
	private int backgroundColorHex;
	private Color backgroundColor;
	
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
		
		backgroundColorHex = 0x2b7f11;
		backgroundColor = new Color(backgroundColorHex);
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
		
		backgroundColorHex = 0x2b7f11;
		backgroundColor = new Color(backgroundColorHex);
	}
	
	public void update() {
		if(keyPressed && !width_height_updated) {
			this.width = keyPressedImage.getWidth();
			this.height = keyPressedImage.getHeight();
			width_height_updated = true;
		}
	}
	
	public void render(Graphics g) {
		if(!keyPressed) {
			g.setColor(backgroundColor);
			g.fillRect(x + 10, y + 10, width - 20, height - 20);
		}
		if(keyPressed) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x + 10, y + 10, width - 20, height - 20);
			g.drawImage(keyPressedImage, x, y, width, height, null);
		}else {
			g.drawImage(keyImage, x, y, width, height, null);
		}
		g.setColor(Color.BLACK);
		if(keyLetter.equals("W")) g.drawString(keyLetter, x + 12, y + 40);
		else g.drawString(keyLetter, x + 18, y + 40);
	}

	public boolean intersects(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		if(rect.intersects(boundries)) {
			return true;
		}
		return false;
	}
	
	public void updateBackgroundColor() {
		backgroundColorHex = 0x58ef37;
		backgroundColor = new Color(backgroundColorHex);
	}
	
	public void backToOriginalColor() {
		backgroundColorHex = 0x2b7f11;
		backgroundColor = new Color(backgroundColorHex);
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
