import java.awt.Graphics;
import java.awt.Image;

public class Bullet extends GameObject {

	public int deltaX;

	public Bullet(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
	}

	@Override
	public void update() {
		x += deltaX;
		rect.x += deltaX;
		if (Shooter.getInstance().getPlayer1().rect.contains(this.rect)) {
			// player1
			Shooter.getInstance().getPlayer1().health--;
			removeBullet();
		} else if (Shooter.getInstance().getEnemy().rect.contains(this.rect)) {
			// player2
			Shooter.getInstance().getEnemy().health--;
			removeBullet();
		}
		if (deltaX > 0) {
			// player1 shooting
			if (x > Shooter.getInstance().getWidth() + 50) {
				removeBullet();
			}
		} else if (deltaX < 0) {
			// player2 shooting
			if (x < -50) {
				removeBullet();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	private void removeBullet() {
		Shooter.getInstance().getBullets().remove(this);
	}

}
