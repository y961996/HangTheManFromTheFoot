package hangTheManFromTheFoot.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */

public class KeyboardInput implements KeyListener{

	private boolean keys[] = new boolean[55555];
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_UP];
		left = keys[KeyEvent.VK_UP];
		right = keys[KeyEvent.VK_UP];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
			
	}
}
