package hangTheManFromTheFoot.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.ui.HangmanButton;
import hangTheManFromTheFoot.ui.UIManager;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class MenuScene extends Scene{

	private int mouseX;
	private int mouseY;
	private Rectangle mouseRect;
	private float alpha = 1.0f;
	
	private BufferedImage menuItemBackgroundImage;

	private UIManager uiManager;
	
	private HangmanButton playButton;
	private HangmanButton optionsButton;
	private HangmanButton exitButton;
	
	public MenuScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		menuItemBackgroundImage = StaticResourceLoader.menuItemBackground;
		mouseRect = new Rectangle(0, 0, 1, 1);
		
		uiManager = new UIManager();
		
		playButton = new HangmanButton(0, 100, menuItemBackgroundImage.getWidth() + 50, menuItemBackgroundImage.getHeight(), menuItemBackgroundImage, true);
		playButton.setButtonText("PLAY");
		
		optionsButton = new HangmanButton(0, 200, menuItemBackgroundImage.getWidth() + 25, menuItemBackgroundImage.getHeight(), menuItemBackgroundImage, true);
		optionsButton.setButtonText("OPTIONS");
		
		exitButton = new HangmanButton(0, 300, menuItemBackgroundImage.getWidth(), menuItemBackgroundImage.getHeight(), menuItemBackgroundImage, true);
		exitButton.setButtonText("EXIT");
		
		uiManager.addComponent(playButton);
		uiManager.addComponent(optionsButton);
		uiManager.addComponent(exitButton);
	}
	
	private void updateMousePosition() {
		mouseX = MouseInput.getX();
		mouseY = MouseInput.getY();
		mouseRect.x = mouseX;
		mouseRect.y = mouseY;
	}
	
	private void checkButtonCollision() {
		// Play Button
		if(playButton.checkCollision(mouseRect)) {
			if(playButton.getWidth() < menuItemBackgroundImage.getWidth() + 100) {
				playButton.setWidth(playButton.getWidth() + 5);
			}
		}
		else {
			if(playButton.getWidth() > menuItemBackgroundImage.getWidth() + 50) {
				playButton.setWidth(playButton.getWidth() - 5);
			}
		}
				
		// Options Button
		if(optionsButton.checkCollision(mouseRect)) {
			if(optionsButton.getWidth() < menuItemBackgroundImage.getWidth() + 75) {
				optionsButton.setWidth(optionsButton.getWidth() + 5);
			}
		}
		else {
			if(optionsButton.getWidth() > menuItemBackgroundImage.getWidth() + 25) {
				optionsButton.setWidth(optionsButton.getWidth() - 5);
			}
		}
				
		// Exit Button
		if(exitButton.checkCollision(mouseRect)) {
			if(exitButton.getWidth() < menuItemBackgroundImage.getWidth() + 50) {
				exitButton.setWidth(exitButton.getWidth() + 5);
			}
		}
		else {
			if(exitButton.getWidth() > menuItemBackgroundImage.getWidth()) {
				exitButton.setWidth(exitButton.getWidth() - 5);
			}
		}
	}
	
	@Override
	public void update() {
		uiManager.update();
		
		updateMousePosition();
		checkButtonCollision();
		
		enterFadeInUpdate();
	}
	
	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		enterFadeInRender(g);
	}
	
	private void enterFadeInRender(Graphics g) {
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}
	
	private void enterFadeInUpdate() {
		if(alpha > 0.000001f) {
			alpha -= 0.01f;
		}else {
			alpha = 0f;
		}
	}

	@Override
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
	}

	public boolean onMousePressed(MousePressedEvent e) {
		if(playButton.checkCollision(mouseRect)) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				sceneController.setScene(game.getGameSceneIndex());
				return true;
			}
		}

		if(exitButton.checkCollision(mouseRect)) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				System.exit(0);
				return true;
			}
		}
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			return true;
		}
		return false;
	}
}
