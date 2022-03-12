package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MapGenerator extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5204934176413740290L;
	public static int map[][];
	public static int brickWidth;
	public static int brickHeight;

	public static Color generatedColor = GamePlay.color;
	public int bricks = GamePlay.totalBricks;
	
	public MapGenerator() {

	}

	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}

		brickWidth = 540 / col;
		brickHeight = 150 / row;
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					g.setColor(generatedColor);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(2));
					g.setColor(new Color(0, 15, 21));
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}

		}
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}
