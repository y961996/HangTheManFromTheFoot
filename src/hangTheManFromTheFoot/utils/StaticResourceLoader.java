package hangTheManFromTheFoot.utils;

import java.awt.image.BufferedImage;

public class StaticResourceLoader {

	public StaticResourceLoader() {}
	
	private static ImageUtils imageUtils = new ImageUtils();
	
	// Images
	public static BufferedImage menuItemBackground;
	public static BufferedImage menuSceneBackground;
	public static BufferedImage hangmanText;
	public static BufferedImage sparkleSpriteSheet;
	
	public static BufferedImage[] sparkleImages;
	
	public static BufferedImage gameSceneBackground;
	public static BufferedImage letterPlaceHolder;
	public static BufferedImage keyImage;
	public static BufferedImage keyPressedImage;
	public static BufferedImage starParticle;
	
	// Sounds
	
	static {
		// Images
		menuItemBackground = imageUtils.loadImage("/images/temp/MenuItemBackground.png");
		menuSceneBackground = imageUtils.loadImage("/images/temp/bg/beach.png");
		hangmanText = imageUtils.loadImage("/images/temp/Hangman.png");
		sparkleSpriteSheet = imageUtils.loadImage("/images/temp/2d/sparkle effect/sparkle256.png");
		
		sparkleImages = new BufferedImage[9];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sparkleImages[i * 3 + j] = sparkleSpriteSheet.getSubimage(j * 256, i * 256, 256, 256);
			}
		}
		
		gameSceneBackground = imageUtils.loadImage("/images/temp/bg/menubg.png");
		letterPlaceHolder = imageUtils.loadImage("/images/temp/letterPlaceholder.png");
		keyImage = imageUtils.loadImage("/images/temp/Tuþ.png");
		keyPressedImage = imageUtils.loadImage("/images/temp/TuþPressed.png");
		starParticle = imageUtils.loadImage("/images/temp/particles/particleArrow.png");
		
		// Sounds
	}
}
