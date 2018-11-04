package hangTheManFromTheFoot.particle;

import java.awt.Graphics;

public abstract class Particle {

	protected float x;
	protected float y;
	protected float velX;
	protected float velY;
	protected float width;
	protected float height;
	protected float lifeTime;
	protected float initialLifeTime;
	private boolean usesImage;
	
	public Particle(float x, float y, float width, float height, float lifeTime, boolean usesImage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lifeTime = lifeTime;
		this.initialLifeTime = this.lifeTime;
		this.usesImage = usesImage;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void setVelocities();

	protected float getX() {
		return x;
	}

	protected void setX(float x) {
		this.x = x;
	}

	protected float getY() {
		return y;
	}

	protected void setY(float y) {
		this.y = y;
	}

	protected float getVelX() {
		return velX;
	}

	protected void setVelX(float velX) {
		this.velX = velX;
	}

	protected float getVelY() {
		return velY;
	}

	protected void setVelY(float velY) {
		this.velY = velY;
	}

	protected float getWidth() {
		return width;
	}

	protected void setWidth(float width) {
		this.width = width;
	}

	protected float getHeight() {
		return height;
	}

	protected void setHeight(float height) {
		this.height = height;
	}

	protected float getLifeTime() {
		return lifeTime;
	}

	protected void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}

	protected float getInitialLifeTime() {
		return initialLifeTime;
	}

	protected void setInitialLifeTime(float initialLifeTime) {
		this.initialLifeTime = initialLifeTime;
	}

	protected boolean isUsesImage() {
		return usesImage;
	}

	protected void setUsesImage(boolean usesImage) {
		this.usesImage = usesImage;
	}
}
