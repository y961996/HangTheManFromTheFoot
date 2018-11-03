package hangTheManFromTheFoot.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String path) {
		String[] pathString = path.split("/");
		try {
			image = ImageIO.read(getClass().getResource(path));
			System.out.println(pathString[pathString.length - 1] + " has loaded successfully.");
		} catch (IOException e) {
			System.err.println("Image: " + pathString[pathString.length - 1] + " from path " + path + " couldn't loaded!");
			e.printStackTrace();
		}
		return image;
	}
	
	
}
