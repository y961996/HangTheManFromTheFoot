package hangTheManFromTheFoot.particle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class MouseParticle extends Particle{

	private BufferedImage image;
	
	public MouseParticle(float width, float height, float lifeTime) {
		super(MouseInput.getX(), MouseInput.getY(), width, height, lifeTime, true);
		image = StaticResourceLoader.starParticle;
	}

	@Override
	public void update() {
		lifeTime--;
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}

	@Override
	public void setVelocities() {
		velX = 0.1f;
		velY = -0.1f;
	}

}
