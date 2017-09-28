
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	int random = new Random().nextInt(2000);
	ArrayList<GameObject> objects;
	ArrayList<Projectile> projectiles;
	Player player;
	Projectile projectile;
	private int score = 0;

	long enemyTimer = 0;
	int enemySpawnTime = random+500;

	public ObjectManager(Player player) {
		objects = new ArrayList<GameObject>();
		projectiles = new ArrayList<Projectile>();
		this.player = player;
		objects.add(player);
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public void update() {
		checkCollision();
		score++;
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();

		}
		for (int i = 0; i < projectiles.size(); i++) {
			GameObject p = projectiles.get(i);
			p.update();

		}

		purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.draw(g);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			GameObject p = projectiles.get(i);
			p.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addObject(new Obsticle(new Random().nextInt(RunnerClass.width), 0, 50, 50));
			enemySpawnTime = new Random().nextInt(2000)+500;
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				if (o1.collisionbox.intersects(o2.collisionbox)) {
				if ((o1 instanceof Obsticle && o2 instanceof Player)
						|| (o2 instanceof Obsticle && o1 instanceof Player)) {
					o1.isAlive = false;
					o2.isAlive = false;
				}
				}
			}
		}

		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < projectiles.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject p2 = projectiles.get(j);
				if (o1.collisionbox.intersects(p2.collisionbox)) {
					if ((o1 instanceof Obsticle && p2 instanceof Projectile)
							|| (p2 instanceof Obsticle && o1 instanceof Projectile)) {
						o1.isAlive = false;
						p2.isAlive = false;
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
