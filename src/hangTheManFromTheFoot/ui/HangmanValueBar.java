package hangTheManFromTheFoot.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.main.Game;

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
		
		controlBar = new Rectangle(this.x + this.width / 2, this.y, 15, this.height);
	}
	
	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, width + controlBar.width, height, 30, 30);
		g.setColor(Color.RED);
		g.fillRoundRect(controlBar.x, controlBar.y, controlBar.width, controlBar.height, 30, 30);
	}
	
	@Override
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) e));
	}

	public boolean onMousePressed(MousePressedEvent e) {
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		if(e.getDragged()) {
			if(controlBar.intersects(Game.mouseRectangle)) {
				controlBar.x = MouseInput.getX() - controlBar.width / 2;
				if(controlBar.x < this.x) controlBar.x = this.x;
				if(controlBar.x > this.x + this.width) controlBar.x = this.x + this.width;
			}
		}
		return false;
	}

	public int getControlBarX() {
		return this.controlBar.x;
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
}
