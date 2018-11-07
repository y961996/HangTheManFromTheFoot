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
		for(int i = particles.size() - 1; i >= 0; i--) {
			if(particles.get(i).getLifeTime() < 0) {
				particles.remove(i);
			}
		}
	}
	
	public void update() {
		removeParticles();
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).isUsesImage()) {
				Graphics2D g2d = (Graphics2D) g;
				float alpha = particles.get(i).lifeTime / particles.get(i).initialLifeTime;
				if(alpha < 0.1f) {
					alpha = 0.1f;
				}else if(alpha > 1.0f) {
					alpha = 1.0f;
				}
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				particles.get(i).render(g2d);
			}else {
				particles.get(i).render(g);
			}
		}
	}
	
	public int getParticleArraySize() {
		return this.particles.size();
	}
}
