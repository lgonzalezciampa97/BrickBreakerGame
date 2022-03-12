package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2267492289603880928L;
	private boolean play = false;
	private int score = 0;

	public static int totalBricks = 32;

	private Timer timer;
	private int delay = 8;

	private int playerX = 310;

	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -2;
	private int ballYdir = -4;

	private static MapGenerator map;

	public static Color color;

	public GamePlay() {
		GamePlay.color = GamePlay.randomColor();
		map = new MapGenerator(4, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		Sounds soundTheme = new Sounds();
		soundTheme.startLoopSound("C:\\Users\\Leonardo\\Documents\\JavaWokingSets\\"
				+ "Java Standard Edition Games\\Brick Breaker\\src\\brickBreaker\\"
				+ "GUNSHIP - Revel In Your Time (Instrumental).wav");

	}

	public static Color randomColor() {
		int colorRandom = 0;
		Color color;
		Random random = new Random();
		Map<Integer, Color> colorsMap = new HashMap<Integer, Color>();
		colorsMap.put(1, new Color(218, 75, 234));
		colorsMap.put(2, new Color(66, 227, 240));
		colorsMap.put(3, new Color(75, 239, 67));
		colorsMap.put(4, new Color(235, 220, 33));
		colorsMap.put(5, new Color(255, 0, 128));
		colorsMap.put(6, new Color(108, 205, 50));
		colorsMap.put(7, new Color(15, 166, 110));
		colorsMap.put(8, new Color(205, 135, 19));
		colorsMap.put(9, new Color(233, 58, 106));
		colorsMap.put(10, new Color(113, 240, 51));
		colorRandom = random.nextInt(10 - 1 + 1) + 1;
		color = colorsMap.get(colorRandom);
		return color;
	}

	public void paint(Graphics g) {

		// background
		if (totalBricks <= 32 && totalBricks > 24) {
			Image background = new ImageIcon(getClass().getResource("space1.png")).getImage();
			g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
		} else if (totalBricks <= 24 && totalBricks > 16) {
			Image background = new ImageIcon(getClass().getResource("astronaut1.png")).getImage();
			g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
//			g.setColor(new Color(210,210,210));
//			g.fillRect(1, 1, 692, 592);
		} else if (totalBricks <= 16 && totalBricks > 8) {
			Image background = new ImageIcon(getClass().getResource("astronaut2.png")).getImage();
			g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
		} else if (totalBricks <= 8 && totalBricks > 0) {
			Image background = new ImageIcon(getClass().getResource("space2.png")).getImage();
			g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
		} else if (totalBricks == 0) {
			Image background = new ImageIcon(getClass().getResource("astronaut-in-space.png")).getImage();
			g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
		}

		map.draw((Graphics2D) g);

		// player
		Image player = new ImageIcon(getClass().getResource("player.png")).getImage();
		g.drawImage(player, playerX, 522, getFocusCycleRootAncestor());
//		g.setColor(new Color(3,71,154));
//		g.fillRect(playerX, 550, 100, 8);

		// ball
		Image pelota = new ImageIcon(getClass().getResource("basketball-ball.png")).getImage();
		g.drawImage(pelota, ballPosX, ballPosY, getFocusCycleRootAncestor());
//		g.setColor(new Color(60, 205, 23));
//		g.fillOval(ballPosX, ballPosY, 25, 25);

		if (play) {
			g.setColor(Color.white);
			g.setFont(new Font("consolas", 1, 28));
			g.drawString("SCORE: " + score, 536, 30);
		}

		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("Orbitron", 1, 48));
			g.drawString("YOU WIN", 228, 100);
			g.setFont(new Font("Orbitron", 1, 32));
			g.drawString("SCORE: " + score, 255, 260);
			g.setFont(new Font("serif", 1, 24));
			g.drawString("Press ENTER to Restart", 230, 180);
		}

		if (ballPosY > 560) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			Image background = new ImageIcon(getClass().getResource("gameover.png")).getImage();
			g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
			map.draw((Graphics2D) g);
			// player
			Image playerGameOver = new ImageIcon(getClass().getResource("player.png")).getImage();
			g.drawImage(playerGameOver, playerX, 522, getFocusCycleRootAncestor());
			// ball
			Image pelotaGameOver = new ImageIcon(getClass().getResource("basketball-ball.png")).getImage();
			g.drawImage(pelotaGameOver, ballPosX, ballPosY, getFocusCycleRootAncestor());
			g.setColor(Color.WHITE);
			g.setFont(new Font("consolas", 1, 32));
			g.drawString("SCORE: " + score, 270, 440);
			g.setFont(new Font("consolas", 1, 24));
			g.drawString("Press ENTER to Restart", 205, 465);
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		timer.start();

		if (play) {

			// ball - pedal interaction
			if (new Rectangle(ballPosX, ballPosY, 20, 30).intersects(new Rectangle(playerX, 522, 140, 8))) {
				ballYdir = -ballYdir;
			}

			for (int i = 0; i < MapGenerator.map.length; i++) {
				for (int j = 0; j < MapGenerator.map[0].length; j++) {
					if (MapGenerator.map[i][j] > 0) {
						int brickX = j * MapGenerator.brickWidth + 80;
						int brickY = i * MapGenerator.brickHeight + 50;
						int brickWidth = MapGenerator.brickWidth;
						int brickHeight = MapGenerator.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							MapGenerator.generatedColor = GamePlay.randomColor();
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 2;
							Sounds soundBrick = new Sounds();
							soundBrick.startShortSound(
									"C:\\Users\\Leonardo\\Documents\\JavaWokingSets\\Java Standard Edition Games\\Brick Breaker\\src\\brickBreaker\\brickBreakSound.wav");

							if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width)
								ballXdir = -ballXdir;
							else {
								ballYdir = -ballYdir;
							}
						}

					}

				}
			}

			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if (ballPosX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballPosY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballPosX > 652) {
				ballXdir = -ballXdir;
			}
		}

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 542) {
				playerX = 542;
			} else {
				moveRight();

			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 3) {
				playerX = 3;
			} else {
				moveLeft();

			}
		}

		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXdir = -2;
				ballYdir = -4;
				score = 0;
				totalBricks = 32;
				map = new MapGenerator(4, 8);
				repaint();
			}
		}

	}

	public void moveRight() {
		play = true;
		playerX += 20;
	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
