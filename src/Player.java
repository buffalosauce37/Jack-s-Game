import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	double gravity;
	int speedX;
	int speedY;
 int random = new Random().nextInt();
	Player(int x, int y, int width, int height, double gravity) {
		super(x,y,width,height);
		this.gravity = gravity;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}

	void update() {
		y += speedY;
		x += speedX;
		speedY += gravity;
		if (y >= 390) {
			speedY = 0;
			y = 390;
		}
		if (y < 0){
			speedY = 0;
			y = 0;
		}
		super.update();
	}
}
