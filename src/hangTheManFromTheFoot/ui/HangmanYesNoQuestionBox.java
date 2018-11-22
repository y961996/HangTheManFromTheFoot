package hangTheManFromTheFoot.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class HangmanYesNoQuestionBox extends UIComponent implements EventListener{

	private int x;
	private int y;
	private int width = 300;
	private int height = 75;
	private int backgroundColorHex;
	private Color backgroundColor;
	private boolean yesPressed = false;
	private boolean noPressed = false;
	private HangmanButton yesButton;
	private HangmanButton noButton;
	private BufferedImage buttonImage;
	private UIManager uiManager;
	
	// 300 - 50
	public HangmanYesNoQuestionBox(int x, int y) {
		this.x = x;
		this.y = y;
		buttonImage = StaticResourceLoader.menuItemBackground;
		yesButton = new HangmanButton(this.x + 20, this.y + 20, 100, 40, buttonImage, true);
		yesButton.setButtonText("YES");
		yesButton.setTextX(yesButton.getX() + yesButton.getWidth() / 4);
		yesButton.setTextY(yesButton.getY() + yesButton.getHeight() / 2 + 10);
		noButton = new HangmanButton(this.x + 170, this.y + 20, 100, 40, buttonImage, true);
		noButton.setTextX(noButton.getX() + noButton.getWidth() / 4);
		noButton.setTextY(noButton.getY() + noButton.getHeight() / 2 + 10);
		noButton.setButtonText("NO");
		
		uiManager = new UIManager();
		uiManager.addComponent(yesButton);
		uiManager.addComponent(noButton);
		
		backgroundColorHex = 0x3F546A;
		backgroundColor = new Color(backgroundColorHex);
	}
	
	@Override
	public void update() {
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		float alpha = 0.5f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g.setColor(backgroundColor);
		g.fillRoundRect(this.x, this.y, width, height, 20, 20);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		uiManager.render(g);
	}

	@Override
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) e));
	}

	public boolean onMousePressed(MousePressedEvent e) {
		if(yesButton.checkCollision(Game.mouseRectangle)) {
			yesPressed = true;
		}else if(noButton.checkCollision(Game.mouseRectangle)) {
			noPressed = true;
		}
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		return false;
	}
	
	public boolean yesPressed() {
		return yesPressed;
	}
	
	public boolean noPressed() {
		return noPressed;
	}
}
