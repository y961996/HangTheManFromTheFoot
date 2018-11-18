package hangTheManFromTheFoot.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventListener;

public class HangmanValueBar extends UIComponent implements EventListener{

	private int x;
	private int y;
	private int width;
	private int height;
	
	private Rectangle controlBar;
	
	public HangmanValueBar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		controlBar = new Rectangle(this.x, this.y, 10, this.height);
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, width, height, 30, 30);
		g.setColor(Color.RED);
		g.fillRoundRect(controlBar.x + 50, controlBar.y, controlBar.width, controlBar.height, 30, 30);
	}
	
	@Override
	public void onEvent(Event event) {
		
	}

}
