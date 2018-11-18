package hangTheManFromTheFoot.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.ui.HangmanValueBar;
import hangTheManFromTheFoot.ui.UIManager;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class OptionsScene extends Scene{

	private BufferedImage optionsBackgroundImage;
	
	private UIManager uiManager;
	private HangmanValueBar changeVolumeBar;
	
	public OptionsScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		optionsBackgroundImage = StaticResourceLoader.optionsBg;
		
		uiManager = new UIManager();
		
		changeVolumeBar = new HangmanValueBar(100, 100, 500, 20);
		uiManager.addComponent(changeVolumeBar);
	}

	@Override
	public void onEvent(Event event) {
		
	}

	@Override
	public void update() {
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(optionsBackgroundImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		uiManager.render(g);
	}

}
