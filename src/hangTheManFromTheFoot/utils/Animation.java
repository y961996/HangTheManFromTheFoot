package hangTheManFromTheFoot.utils;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delayBetweenFrames;
	
	private boolean playedOnce;
	
	public Animation() {
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	public void setDelayBetweenFrames(long delayBetweenFrames) {
		this.delayBetweenFrames = delayBetweenFrames;
	}
	
	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	public void update() {
		if(delayBetweenFrames == -1) {
			return;
		}
		
		long elapsedTime = (System.nanoTime() - startTime) / 1000000;
		
		if(elapsedTime > delayBetweenFrames) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public BufferedImage getCurrentImage() {
		return frames[currentFrame];
	}
	
	public boolean hasPlayedOnce() {
		return playedOnce;
	}
}
