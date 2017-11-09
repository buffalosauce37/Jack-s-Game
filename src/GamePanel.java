import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	ObjectManager manager;
	int ammo;
	int ammoincrerase = 1000;
	public static BufferedImage backgroundImg;
	public static BufferedImage obsticleImg;
	public static BufferedImage playerImg;
	public static BufferedImage projectileImg;
	boolean pause = false;
	int backgroundSpeed = 2;
	int backgroundX = 0;

	GamePanel() {
		time = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.BOLD, 48);
		subtitleFont = new Font("Arial", Font.PLAIN, 30);
		try {
			playerImg = ImageIO.read(this.getClass().getResourceAsStream("player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			backgroundImg = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			projectileImg = ImageIO.read(this.getClass().getResourceAsStream("Fire.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obsticleImg = ImageIO.read(this.getClass().getResourceAsStream("airplane-12.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void startGame() {
		time.start();
	}
	
	void newGame(){
		player = new Player(100, 150, 20, 20, 1);
		manager = new ObjectManager(player);
		ammo = 3;
		ammoincrerase = 1000;
		pause = true;
		
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
		
		if (pause == true){

		}
		else {
			manager.update();
			manager.manageEnemies();
			manager.checkCollision();
			backgroundX -= backgroundSpeed;
			if(manager.getScore()==ammoincrerase){
				ammo+=3;
				ammoincrerase+=1000;
			}
			if (player.isAlive == false) {
				currentState = END_STATE;
				player = new Player(100, 150, 20, 20, 1);
			}
		}
		if (backgroundX <= -800){
			backgroundX = 0;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, RunnerClass.width, RunnerClass.height);
		g.setFont(subtitleFont);
		g.setColor(Color.WHITE);
		g.drawString("Press enter to start", 10, 300);
		g.setFont(titleFont);
		g.drawString("Dinosaur Dodge", 10, 50);
		g.setFont(subtitleFont);
		g.drawString("Up arrow to jump and to unfreeze game", 10, 200);
		g.setFont(subtitleFont);
		g.drawString("Space to shoot", 10, 250);
		g.setFont(subtitleFont);
		g.drawString("Touching the top or bottom of the screen kills you", 10, 150);
		g.setFont(subtitleFont);
		g.drawString("Airplanes kill you", 10, 100);
	}

	void drawGameState(Graphics g) {
		g.drawImage(GamePanel.backgroundImg, backgroundX, 0, 800, 500, null);
		g.drawImage(GamePanel.backgroundImg, backgroundX+800, 0, 800, 500, null);
		manager.draw(g);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(manager.getScore()), 10, 50);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(ammo), 750, 50);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RunnerClass.width, RunnerClass.height);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 300, 50);
		g.drawString(Integer.toString(manager.getScore()), 300, 100);
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
			if (currentState == GAME_STATE){
				newGame();
			}
		}
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.speedY -= 15;
			pause = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && ammo>0) {
			manager.addProjectile(new Projectile(player.x, player.y, 5, 10));
			ammo--;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println();
		player.speedX = 0;
		player.speedY = 0;
	}
}
