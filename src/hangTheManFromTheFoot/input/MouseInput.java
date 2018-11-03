package hangTheManFromTheFoot.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;

/*
 * This class is taken from the GameProgrammingTutorials in youtube channel TheCernoProject
 * @author of this class is TheChernoProject
 * You can find the link to the tutorials here: 
 * https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
 */

public class MouseInput implements MouseListener, MouseMotionListener{

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	private EventListener eventListener;
	
	public MouseInput(EventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), true);
		eventListener.onEvent(event);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
			
		MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), false);
		eventListener.onEvent(event);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
		
		MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
		eventListener.onEvent(event);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseB = e.getButton();
		
		MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
		eventListener.onEvent(event);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getButton() {
		return mouseB;
	}
	
	
}
