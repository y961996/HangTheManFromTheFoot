package hangTheManFromTheFoot.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.ui.HangmanButton;
import hangTheManFromTheFoot.ui.HangmanValueBar;
import hangTheManFromTheFoot.ui.UIManager;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class OptionsScene extends Scene{

	private float volume;

	private BufferedImage optionsBackgroundImage;
	
	private UIManager uiManager;
	private HangmanValueBar changeVolumeBar;
	private HangmanButton goToMenuButton;
	
	public OptionsScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		optionsBackgroundImage = StaticResourceLoader.optionsBg;
		
		uiManager = new UIManager();
		
		changeVolumeBar = new HangmanValueBar(100, 400, 320, 20);
		volume = (float)changeVolumeBar.getControlBarX();
		uiManager.addComponent(changeVolumeBar);
		goToMenuButton = new HangmanButton(50, 50, StaticResourceLoader.menuItemBackground.getWidth(), StaticResourceLoader.menuItemBackground.getHeight(), StaticResourceLoader.menuItemBackground, true);
		goToMenuButton.setButtonText("Go Back To Menu");
		goToMenuButton.setTextX(goToMenuButton.getX() + 60);
		goToMenuButton.setTextY(goToMenuButton.getTextY() + 10);
		uiManager.addComponent(goToMenuButton);
	}

	@Override
	public void update() {
		uiManager.update();
		volume = ((float)changeVolumeBar.getControlBarX() - changeVolumeBar.getX()) / changeVolumeBar.getWidth();
		game.getMenuSoundUtils().setVolume(volume);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(optionsBackgroundImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setFont(new Font("Verdana", Font.BOLD, 24));
		g.setColor(Color.BLACK.brighter());
		g.drawString("Background Music Volume", 100, 350);
		g.setFont(new Font("Verdana", Font.BOLD, 48));
		g.setColor(Color.WHITE);
		g.drawString("" + (int)(volume * 100), 450, 430);
		uiManager.render(g);
	}
	
	public void onEvent(Event event) {
		if(changeVolumeBar != null) changeVolumeBar.onEvent(event);
		
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) e));
	}

	public boolean onMousePressed(MousePressedEvent e) {
		if(goToMenuButton.checkCollision(Game.mouseRectangle)) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				sceneController.setScene(game.getMenuSceneIndex());
			}
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
