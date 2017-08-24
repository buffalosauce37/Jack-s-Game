import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 20, 20);
	}

	void update() {

	}
}
