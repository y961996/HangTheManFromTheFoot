package hangTheManFromTheFoot.events.eventTypes;

import hangTheManFromTheFoot.events.Event;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */

public class MouseMovedEvent extends Event{
	
	private int x;
	private int y;
	private boolean dragged;
	
	public MouseMovedEvent(int x, int y, boolean dragged) {
		super(Event.Type.MOUSE_MOVED);
		this.x = x;
		this.y = y;
		this.dragged = dragged;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getDragged() {
		return dragged;
	}
}
