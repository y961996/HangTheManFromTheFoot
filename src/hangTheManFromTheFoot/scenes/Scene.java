package hangTheManFromTheFoot.scenes;

import java.awt.Graphics;

import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.main.Game;

public abstract class Scene implements EventListener{

	public SceneController sceneController;
	public Game game;
	
	public Scene(Game game, SceneController sceneController) {
		this.game = game;
		this.sceneController = sceneController;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
}
