package hangTheManFromTheFoot.particle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class MenuParticle extends Particle{

	private static Random random = new Random();

	private BufferedImage image;
	
	public MenuParticle(float width, float height, float lifeTime) {
		super(random.nextFloat() * Game.WIDTH, random.nextFloat() * Game.HEIGHT, width, height, lifeTime, true);
		image = StaticResourceLoader.starParticle;
		setVelocities(random.nextFloat() * 2 - 1, random.nextFloat() * 2 -1);
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
	public void setVelocities(float velX, float velY) {
		this.velX = velX;
		this.velY = velY;
	}

	
}
