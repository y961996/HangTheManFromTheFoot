package hangTheManFromTheFoot.particle;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ParticleController {

	private ArrayList<Particle> particles;
	
	public ParticleController() {
		particles = new ArrayList<Particle>();
	}
	
	public void addParticle(Particle particle) {
		particles.add(particle);
	}
	
	public void removeParticles() {
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).getLifeTime() < 0) {
				particles.remove(i);
			}
		}
	}
	
	public void update() {
		for(Particle p : particles) {
			p.update();
		}
		System.out.println(particles.size());
	}
	
	public void render(Graphics g) {
		for(Particle p : particles) {
			if(p.isUsesImage()) {
				Graphics2D g2d = (Graphics2D) g;
				float alpha = p.lifeTime / p.initialLifeTime;
				if(alpha < 0.1f) {
					alpha = 0.1f;
				}else if(alpha > 1.0f) {
					alpha = 1.0f;
				}
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				p.render(g2d);
			}else {
				p.render(g);
			}
		}
	}
}
