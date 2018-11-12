package hangTheManFromTheFoot.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class HangmanYesNoQuestionBox extends UIComponent implements EventListener{

	private int x;
	private int y;
	private int width = 300;
	private int height = 50;
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
		noButton = new HangmanButton(this.x + 160, this.y + 20, 100, 40, buttonImage, true);
		noButton.setButtonText("NO");
		
		uiManager = new UIManager();
		uiManager.addComponent(yesButton);
		uiManager.addComponent(noButton);
	}
	
	@Override
	public void update() {
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.fillRect(this.x, this.y, width, height);
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
		if(yesButton.checkCollision(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
			System.out.println("YES");
		}
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		return false;
	}
}
