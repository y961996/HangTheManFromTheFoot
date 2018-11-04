package hangTheManFromTheFoot.utils;

import java.awt.image.BufferedImage;

public class StaticResourceLoader {

	public StaticResourceLoader() {}
	
	private static ImageLoader loader = new ImageLoader();
	
	// Temp Images
	public static BufferedImage menuItemBackground;
	public static BufferedImage letterPlaceHolder;
	public static BufferedImage keyImage;
	public static BufferedImage keyPressedImage;
	public static BufferedImage starParticle;
	
	static {
		menuItemBackground = loader.loadImage("/images/temp/MenuItemBackground.png");
		letterPlaceHolder = loader.loadImage("/images/temp/letterPlaceholder.png");
		keyImage = loader.loadImage("/images/temp/Tuþ.png");
		keyPressedImage = loader.loadImage("/images/temp/TuþPressed.png");
		starParticle = loader.loadImage("/images/temp/particles/particleStar.png");
	}
}
