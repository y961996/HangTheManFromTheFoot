package hangTheManFromTheFoot.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import hangTheManFromTheFoot.main.Game;

public class ImageUtils {

	private BufferedImage image;

	public BufferedImage loadImage(String path) {
		String[] pathString = path.split("/");
		try {
			image = ImageIO.read(getClass().getResource(path));
			if(Game.DEBUG) System.out.println(pathString[pathString.length - 1] + " has loaded successfully.");
		} catch (IOException e) {
			if(Game.DEBUG) System.err.println("Image: " + pathString[pathString.length - 1] + " from path " + path + " couldn't loaded!");
			e.printStackTrace();
		}
		return image;
	}
}
