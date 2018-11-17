package hangTheManFromTheFoot.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.main.Game;

public class OptionsScene extends Scene{

	private BufferedImage optionsBackgroundImage;
	
	public OptionsScene(Game game, SceneController sceneController) {
		super(game, sceneController);
	}

	@Override
	public void onEvent(Event event) {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(optionsBackgroundImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
	}

}
