import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cache {

	public static Image player1;
	public static Image player2;
	private static final String IMAGE_DIR = "images/";
	public static Image bullet;

	public Cache() {
		load();
	}

	public void load() {
		Cache.player1 = loadImage("player1.png");
		Cache.player2 = loadImage("player2.png");
		bullet = loadImage("bullet.png");
	}

	private Image loadImage(String img) {
		try {
			return ImageIO.read(new File(IMAGE_DIR + img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
