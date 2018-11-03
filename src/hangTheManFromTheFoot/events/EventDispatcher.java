package hangTheManFromTheFoot.events;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */

public class EventDispatcher {

	private Event event;
	
	public EventDispatcher(Event event) {
		this.event = event;
	}
	
	public void dispatch(Event.Type type, EventHandler handler) {
		if(event.handled) {
			return;
		}
		
		if(event.getType() == type) {
			event.handled = handler.onEvent(event);
		}
	}
}
