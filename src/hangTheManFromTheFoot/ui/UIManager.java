package hangTheManFromTheFoot.ui;

import java.awt.Graphics;
import java.util.ArrayList;

public class UIManager {

	private ArrayList<UIComponent> components = new ArrayList<>();
	
	public void addComponent(UIComponent component) {
		System.out.println(component + " added.");
		components.add(component);
	}
	
	public void removeComponent(UIComponent component) {
		components.remove(component);
	}
	
	public void update() {
		for(int i = 0; i < components.size(); i++) {
			components.get(i).update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < components.size(); i++) {
			components.get(i).render(g);
		}
	}
}
