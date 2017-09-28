import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	Rectangle collisionbox;
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
GameObject(int x, int y, int width, int height){
	collisionbox = new Rectangle(x, y, width, height);
}
	void update() {
		collisionbox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {
	}
}
