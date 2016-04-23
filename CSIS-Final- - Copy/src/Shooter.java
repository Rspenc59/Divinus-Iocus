import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class Shooter extends Canvas implements Runnable, KeyListener {

	private static Shooter instance;
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private BufferStrategy bs = null;
	private Graphics graphics = null;
	private boolean running = false;
	private Thread thread;
	private Player player1;
	private Enemy enemy;
	private List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
	private boolean GO = false;
	private int winner;

	public Shooter() {
		JFrame frame = new JFrame("Shooter");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// setting up
		new Cache();
		player1 = new Player(10, 150, 20, 90, Cache.player1);
		enemy = new Enemy(580, 150, 20, 90, Cache.player2);

		frame.add(this);

		thread = new Thread(this);

		frame.setVisible(true);

		addKeyListener(this);
	}

	public void paint(Graphics g) {
		if (bs == null) {
			createBufferStrategy(2);
			bs = getBufferStrategy();
			graphics = bs.getDrawGraphics();

			thread.start();
			running = true;
		}
	}

	public void update() {
		if (!GO) {
			player1.update();
			enemy.update();
			if (enemy.start == true)
				enemysBullets();
			for (Bullet bullet : bullets) {
				bullet.update();
				if (player1.health <= 0) {
					// enemy win
					GO = true;
					winner = 2;
				}
				if (enemy.health <= 0) {
					// player1 win
					GO = true;
					winner = 1;
				}
			}
		}
	}

	public void render() {
		graphics.clearRect(0, 0, WIDTH, HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		graphics.setColor(Color.RED);
		graphics.drawString("Health: " + player1.health, 50, 50);
		graphics.drawString("Health: " + enemy.health, 520, 50);
		if (GO) {
			if (winner == 1) {
				graphics.drawString("Player " + winner + " has won", 250, 60);
			} else if (winner == 2) {
				graphics.drawString("Computer has won", 250, 60);
			}
			graphics.drawString("press ESC to restart", 250, 70);
		}
		player1.draw(graphics);
		enemy.draw(graphics);
		for (Bullet bullet : bullets) {
			bullet.draw(graphics);
		}
	}

	@Override
	public void run() {
		// game loop
		while (running) {
			update();
			render();
			bs.show();
			Thread.currentThread();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
	}

	public static void main(String[] args) {
		instance = new Shooter();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!GO) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player1.up = true;
				enemy.start = true;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				player1.down = true;
				enemy.start = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Bullet bullet = new Bullet(player1.x + player1.width, player1.y + player1.height / 2, 4, 4,
						Cache.bullet);
				bullet.deltaX = 10;
				bullets.add(bullet);
				enemy.start = true;
			}
		} else {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				resetGame();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player1.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player1.down = false;
		}
	}

	private void enemysBullets() {
		int randomShot = (int) (Math.random() * (Math.random() * 100));
		if (randomShot == 0) {
			Bullet bullet = new Bullet(enemy.x, enemy.y + enemy.height / 2, 4, 4, Cache.bullet);
			bullet.deltaX = -5;
			bullets.add(bullet);
		}
	}

	private void resetGame() {
		player1.reset();
		enemy.reset();
		bullets.clear();
		GO = false;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public static Shooter getInstance() {
		return instance;
	}

}
