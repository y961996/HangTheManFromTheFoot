package hangTheManFromTheFoot.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HangmanButton extends UIComponent{

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean hasText;
	
	private String buttonText;
	private BufferedImage buttonImage;
	private Rectangle collisionBox;
	
	private Font textFont;
	private Color color;
	private Color textColor;
	
	public HangmanButton(int xPos, int yPos, int width, int height, BufferedImage buttonImage, boolean hasText) {
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;
		this.buttonImage = buttonImage;
		this.collisionBox = new Rectangle(x, y, width, height);
		this.hasText = hasText;
		if(hasText) {
			buttonText = "Temporary";
			textColor = Color.WHITE;
			textFont = new Font("Verdana", Font.BOLD, 24);
		}
	}
	
	public HangmanButton(int xPos, int yPos, BufferedImage buttonImage, boolean hasText) {
		this.x = xPos;
		this.y = yPos;
		this.buttonImage = buttonImage;
		this.width = buttonImage.getWidth();
		this.height = buttonImage.getHeight();
		this.collisionBox = new Rectangle(x, y, width, height);
		this.hasText = hasText;
		if(hasText) {
			buttonText = "Temporary";
			textColor = Color.WHITE;
			textFont = new Font("Verdana", Font.BOLD, 24);
		}
	}
	
	public HangmanButton(int xPos, int yPos, int width, int height, Color color, boolean hasText) {
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;
		this.color = color;
		this.collisionBox = new Rectangle(x, y, width, height);
		this.hasText = hasText;
		if(hasText) {
			buttonText = "Temporary";
			textColor = Color.WHITE;
			textFont = new Font("Verdana", Font.BOLD, 24);
		}
	}
	
	public boolean checkCollision(Rectangle rectangle) {
		if(rectangle.intersects(this.collisionBox)) return true;
		return false;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		if(buttonImage != null) {
			g.drawImage(buttonImage, x, y, width, height, null);
		}else {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
		
		if(hasText) {
			g.setColor(textColor);
			g.setFont(textFont);
			g.drawString(buttonText, x + width / 2, y + height / 2);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setButtonText(String text) {
		this.buttonText = text;
	}
	
	public void setTextColor(Color color) {
		this.color = color;
	}
	
	public void setTextColor(int color) {
		this.color = new Color(color);
	}
	
	public void setTextFont(Font font) {
		this.textFont = font;
	}
}
