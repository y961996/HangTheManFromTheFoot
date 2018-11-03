package hangTheManFromTheFoot.scenes;

import java.awt.Graphics;
import java.util.ArrayList;

public class SceneController {
	
	private ArrayList<Scene> scenes  = new ArrayList<Scene>();
	private int currentScene = 0;
	
	public void addScene(Scene scene) {
		scenes.add(scene);
	}
	public void setScene(int index) {
		currentScene = index;
	}
	
	public void update() {
		scenes.get(currentScene).update();
	}
	
	public void render(Graphics g) {
		scenes.get(currentScene).render(g);
	}
	
	public ArrayList<Scene> getScenesList(){
		return scenes;
	}
	
	public int getNumberOfScenes() {
		return scenes.size();
	}
	
	public Scene getCurrentScene() {
		return scenes.get(currentScene);
	}
}
