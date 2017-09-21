import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	double gravity;
	int speedX;
	int speedY;
 int random = new Random().nextInt();
	Player(int x, int y, int width, int height, double gravity) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gravity = gravity;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 20, 20);
	}

	void update() {
		y += speedY;
		x += speedX;
		speedY += gravity;
		if (y >= 390) {
			speedY = 0;
			y = 390;
		}
		
	}
}
