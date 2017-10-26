import java.awt.Color;
import java.awt.Graphics;

public class Obsticle extends GameObject {

	Obsticle(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.width = width;
		this.height = height;
	}

	void update() {
		x -= 3;
		if (x+width <= -20) {
			isAlive = false;
		}
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.obsticleImg, x, y, width, height, null);
	}
}
