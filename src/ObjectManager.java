
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	int random = new Random().nextInt(2000);
	ArrayList<GameObject> objects;
	long enemyTimer = 0;
	Player player;
	Projectile projectile;
	private int score = 0;

	int enemySpawnTime = random + 500;

	public ObjectManager(Player player) {
		objects = new ArrayList<GameObject>();
		this.player = player;
		objects.add(player);
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	void addProjectile(Projectile projectile) {
		objects.add(projectile);
	}

	public void update() {
		checkCollision();
		score++;
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();

		}

		purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	int OS = 20;

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			int SetY = new Random().nextInt(390-OS);
			addObject(new Obsticle(900, SetY, OS, OS));
			OS += 5;
			enemySpawnTime = new Random().nextInt(2000) + 500;
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				if (o1.collisionbox.intersects(o2.collisionbox)) {
					if ((o1 instanceof Obsticle && o2 instanceof Player) || (o2 instanceof Obsticle && o1 instanceof Player)) {
						o1.isAlive = false;
						o2.isAlive = false;

					}

					if (o1.collisionbox.intersects(o2.collisionbox)) {
						if ((o1 instanceof Obsticle && o2 instanceof Projectile) || (o2 instanceof Obsticle && o1 instanceof Projectile)) {
							o1.isAlive = false;
							o2.isAlive = false;
						}
					}
				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public void reset() {
		objects.clear();
	}
}
