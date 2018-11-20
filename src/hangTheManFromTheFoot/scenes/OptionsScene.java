package hangTheManFromTheFoot.scenes;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.ui.HangmanValueBar;
import hangTheManFromTheFoot.ui.UIManager;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class OptionsScene extends Scene{

	private BufferedImage optionsBackgroundImage;
	
	private UIManager uiManager;
	private HangmanValueBar changeVolumeBar;
	private float volume;
	
	public OptionsScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		optionsBackgroundImage = StaticResourceLoader.optionsBg;
		
		uiManager = new UIManager();
		
		changeVolumeBar = new HangmanValueBar(100, 100, 320, 20);
		volume = (float)changeVolumeBar.getControlBarX();
		uiManager.addComponent(changeVolumeBar);
	}

	@Override
	public void update() {
		uiManager.update();
		volume = ((float)changeVolumeBar.getControlBarX() - changeVolumeBar.getX()) / changeVolumeBar.getWidth();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(optionsBackgroundImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setFont(new Font("Verdana", Font.BOLD, 48));
		g.drawString("" + volume, 200, 200);
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
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		return false;
	}

}
