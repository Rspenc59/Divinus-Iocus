import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends GameObject {

	public boolean start;
	public int health;
	public int startX, startY;
	private boolean LR, UD;

	public static final int STARThealth = 10;
	public static final int speedY = 10, speedX = 1;

	public Enemy(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
		this.health = STARThealth;
		this.startX = x;
		this.startY = y;
	}

	@Override
	void update() {
		if (start) {
			if (y >= 0 && LR == false && UD == false) {
				y -= speedY;
				rect.y -= speedY;
				x -= speedX;
				rect.x -= speedX;
				if (y == 0)
					UD = true;
				if (x == 300)
					LR = true;
			} else if (y <= 490 && UD == true && LR == false) {
				y += speedY;
				rect.y += speedY;
				x -= speedX;
				rect.x -= speedX;
				if (y == 490)
					UD = false;
				if (x == 300)
					LR = true;
			} else if (y >= 0 && LR == true && UD == false) {
				y -= speedY;
				rect.y -= speedY;
				x += speedX;
				rect.x += speedX;
				if (y == 0)
					UD = true;
				if (x == 570)
					LR = false;
			} else if (y <= 490 && LR == true && UD == true) {
				y += speedY;
				rect.y += speedY;
				x += speedX;
				rect.x += speedX;
				if (y == 490)
					UD = false;
				if (x == 570)
					LR = false;
			}
		}
	}

	@Override
	void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	public void reset() {
		this.x = startX;
		this.y = startY;
		this.rect.x = startX;
		this.rect.y = startY;
		this.health = STARThealth;
		this.start = false;
		this.LR = false;
		this.UD = false;
	}

}
