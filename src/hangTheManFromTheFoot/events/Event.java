package hangTheManFromTheFoot.events;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */


public class Event {

	public enum Type{
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		MOUSE_MOVED
	}
	
	private Type type;
	boolean handled = false;
	
	protected Event(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean handled() {
		return handled;
	}
	
	public void setHandled(boolean bool) {
		this.handled = bool;
	}
}
