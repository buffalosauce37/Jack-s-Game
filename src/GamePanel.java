import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer time;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font subtitleFont;
	Player player;

	GamePanel() {
		time = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.BOLD, 48);
		subtitleFont = new Font("Arial", Font.PLAIN, 30);
		player = new Player(50, 50, 20, 20);
	}

	void startGame() {
		time.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}

	}

	void updateMenuState() {

	}

	void updateGameState() {
		player.update();
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, RunnerClass.width, RunnerClass.height);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Press enter to start", 10, 50);
		g.setFont(subtitleFont);
		g.drawString("Up arrow to jump", 10, 200);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, RunnerClass.width, RunnerClass.height);
		player.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RunnerClass.width, RunnerClass.height);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 300, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println();
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState++;
		}
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			player.speedY += -3;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			player.speedY += 3;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			player.speedX += -3;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.speedX += 3;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println();
		player.speedX -= 4;
		player.speedY -= 4;
	}
}
