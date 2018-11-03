package hangTheManFromTheFoot.events.eventTypes;

import hangTheManFromTheFoot.events.Event;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */

public class MouseButtonEvent extends Event{

	protected int x;
	protected int y;
	protected int button;
	
	protected MouseButtonEvent(int button, int x, int y, Type type) {
		super(type);
		this.button = button;
		this.x = x;
		this.y = y;
	}
	
	public int getButton() {
		return button;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
