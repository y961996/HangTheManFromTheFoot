package hangTheManFromTheFoot.events.eventTypes;

import hangTheManFromTheFoot.events.Event;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */

public class MouseReleasedEvent extends MouseButtonEvent{

	public MouseReleasedEvent(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_RELEASED);
	}
}
