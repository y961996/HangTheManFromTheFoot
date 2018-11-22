package hangTheManFromTheFoot.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.particle.MouseParticle;
import hangTheManFromTheFoot.particle.ParticleController;
import hangTheManFromTheFoot.ui.HangmanButton;
import hangTheManFromTheFoot.ui.HangmanYesNoQuestionBox;
import hangTheManFromTheFoot.ui.UIManager;
import hangTheManFromTheFoot.utils.Animation;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class MenuScene extends Scene{

	private float alpha = 1.0f;
	
	private BufferedImage menuItemBackgroundImage;
	private BufferedImage menuSceneBackground;
	private BufferedImage hangmanText;
	private BufferedImage[] sparkleImages;
	
	private Animation sparkleAnimation;
		
	private ParticleController particleController;
	private UIManager uiManager;
	
	private HangmanButton playButton;
	private HangmanButton optionsButton;
	private HangmanButton exitButton;
	private HangmanYesNoQuestionBox yesNoBox;
	
	public MenuScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		menuItemBackgroundImage = StaticResourceLoader.menuItemBackground;
		menuSceneBackground = StaticResourceLoader.menuSceneBackground;
		hangmanText = StaticResourceLoader.hangmanText;
		sparkleImages = StaticResourceLoader.sparkleImages;
		
		sparkleAnimation = new Animation();
		sparkleAnimation.setFrames(sparkleImages);
		sparkleAnimation.setDelayBetweenFrames(75);
			
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
		
		particleController = new ParticleController();
	}
	
	private void checkButtonCollision() {
		// Play Button
		if(playButton.checkCollision(Game.mouseRectangle)) {
			if(playButton.getWidth() < menuItemBackgroundImage.getWidth() + 100) {
				playButton.setWidth(playButton.getWidth() + 5);
				playButton.setTextX(playButton.getWidth() / 2 + 5);
			}
		}
		else {
			if(playButton.getWidth() > menuItemBackgroundImage.getWidth() + 50) {
				playButton.setWidth(playButton.getWidth() - 5);
				playButton.setTextX(playButton.getWidth() / 2 - 5);
			}
		}
				
		// Options Button
		if(optionsButton.checkCollision(Game.mouseRectangle)) {
			if(optionsButton.getWidth() < menuItemBackgroundImage.getWidth() + 75) {
				optionsButton.setWidth(optionsButton.getWidth() + 5);
				optionsButton.setTextX(optionsButton.getWidth() / 2 + 5);
			}
		}
		else {
			if(optionsButton.getWidth() > menuItemBackgroundImage.getWidth() + 25) {
				optionsButton.setWidth(optionsButton.getWidth() - 5);
				optionsButton.setTextX(optionsButton.getWidth() / 2 - 5);
			}
		}
				
		// Exit Button
		if(exitButton.checkCollision(Game.mouseRectangle)) {
			if(exitButton.getWidth() < menuItemBackgroundImage.getWidth() + 50) {
				exitButton.setWidth(exitButton.getWidth() + 5);
				exitButton.setTextX(exitButton.getWidth() / 2 + 5);
			}
		}
		else {
			if(exitButton.getWidth() > menuItemBackgroundImage.getWidth()) {
				exitButton.setWidth(exitButton.getWidth() - 5);
				exitButton.setTextX(exitButton.getWidth() / 2 - 5);
			}
		}
	}
	
	@Override
	public void update() {
		uiManager.update();
		
		sparkleAnimation.update();
		
		checkButtonCollision();
		
		enterFadeInUpdate();
		
		particleController.update();
	}
	
	@Override
	public void render(Graphics g) {
		enterFadeInRender(g);
		
		// Background
		g.drawImage(menuSceneBackground, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(sparkleAnimation.getCurrentImage(), Game.WIDTH - 250, 0, null);
		g.drawImage(hangmanText, 250, 435, null);
		g.drawImage(sparkleAnimation.getCurrentImage(), 300, 350, 128, 128, null);
		g.drawImage(sparkleAnimation.getCurrentImage(), 700, 460, 64, 64, null);
		g.drawImage(sparkleAnimation.getCurrentImage(), 1150, 480, 64, 64, null);
		
		particleController.render(g);

		uiManager.render(g);
	}
	
	private void makeParticle() {
		particleController.addParticle(new MouseParticle(32, 32, 5));
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
		if(yesNoBox != null) {
			yesNoBox.onEvent(event);
		}
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) e));
		
	}

	public boolean onMousePressed(MousePressedEvent e) {
		if(playButton.checkCollision(Game.mouseRectangle)) {
			if(e.getButton() == MouseEvent.BUTTON1 && yesNoBox == null) {
				sceneController.setScene(game.getGameSceneIndex());
				playButton.setWidth(menuItemBackgroundImage.getWidth() + 50);
				playButton.setTextX(playButton.getX() + playButton.getWidth() / 2);
				playButton.setTextY(playButton.getY() + playButton.getHeight() / 2);
				return true;
			}
		}

		if(optionsButton.checkCollision(Game.mouseRectangle)) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				sceneController.setScene(game.getOptionsSceneIndex());
				optionsButton.setWidth(menuItemBackgroundImage.getWidth() + 25);
				optionsButton.setTextX(optionsButton.getX() + optionsButton.getWidth() / 2);
				optionsButton.setTextY(optionsButton.getY() + optionsButton.getHeight() / 2);
				return true;
			}
		}
			
		if(exitButton.checkCollision(Game.mouseRectangle)) {
			if(e.getButton() == MouseEvent.BUTTON1 && yesNoBox == null) {
				yesNoBox = new HangmanYesNoQuestionBox(500, 300);
				uiManager.addComponent(yesNoBox);
				return true;
			}
		}
		
		if(yesNoBox != null) {
			if(yesNoBox.yesPressed()) {
				System.exit(0);
			}
			
			if(yesNoBox.noPressed()) {
				uiManager.removeComponent(yesNoBox);
				yesNoBox = null;
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
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		makeParticle();
		return false;
	}
}
