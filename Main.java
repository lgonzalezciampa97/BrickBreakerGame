package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame display = new JFrame();
		GamePlay gamePlay = new GamePlay();
		display.setBounds(10, 10, 700, 600);
		display.setTitle("Brick Breaker");
		display.setResizable(false);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.add(gamePlay);

	}

}
