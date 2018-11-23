package hangTheManFromTheFoot.utils;

import java.awt.image.BufferedImage;

public class StaticResourceLoader {

	public StaticResourceLoader() {}
	
	private static ImageUtils imageUtils = new ImageUtils();
	
	public static BufferedImage menuItemBackground;
	public static BufferedImage menuSceneBackground;
	public static BufferedImage hangmanText;
	public static BufferedImage sparkleSpriteSheet;
	public static BufferedImage hangingManSpriteSheet;
	public static BufferedImage windSpriteSheet;
	
	public static BufferedImage[] sparkleImages;
	public static BufferedImage[] hangingManImages;
	public static BufferedImage[] windImages;
	
	public static BufferedImage gameSceneBackground;
	public static BufferedImage letterPlaceHolder;
	public static BufferedImage keyImage;
	public static BufferedImage keyPressedImage;
	public static BufferedImage starParticle;
	public static BufferedImage football;
	
	public static BufferedImage optionsBg;
	
	static {
		menuItemBackground = imageUtils.loadImage("/images/MenuItemBackground.png");
		menuSceneBackground = imageUtils.loadImage("/images/bg/beach.png");
		hangmanText = imageUtils.loadImage("/images/Hangman.png");
		sparkleSpriteSheet = imageUtils.loadImage("/images/2d/sparkle effect/sparkle256.png");
		hangingManSpriteSheet = imageUtils.loadImage("/images/hangingManSpriteSheet.png");
		windSpriteSheet = imageUtils.loadImage("/images/wind.png");
		
		sparkleImages = new BufferedImage[9];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sparkleImages[i * 3 + j] = sparkleSpriteSheet.getSubimage(j * 256, i * 256, 256, 256);
			}
		}
		
		hangingManImages = new BufferedImage[7];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if(i > 0 && j > 2) break;
				hangingManImages[i * 4 + j] = hangingManSpriteSheet.getSubimage(j * 512, i * 512, 512, 512);
			}
		}
		
		windImages = new BufferedImage[4];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				windImages[i * 2 + j] = windSpriteSheet.getSubimage(j * 64, i * 64, 64, 64);
			}
		}
		
		gameSceneBackground = imageUtils.loadImage("/images/bg/menubg.png");
		letterPlaceHolder = imageUtils.loadImage("/images/letterPlaceholder.png");
		keyImage = imageUtils.loadImage("/images/Tuþ.png");
		keyPressedImage = imageUtils.loadImage("/images/TuþPressed.png");
		starParticle = imageUtils.loadImage("/images/particles/particleArrow.png");
		football = imageUtils.loadImage("/images/football.png");
		
		optionsBg = imageUtils.loadImage("/images/bg/optionsBg.jpg");
	}
}
