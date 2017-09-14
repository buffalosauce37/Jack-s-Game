import java.awt.Color;
import java.awt.Graphics;

public class Obsticle extends GameObject {

	Obsticle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	void update(){
		x--;
	}
	void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(700, 200, 20, 20);
	}
}
