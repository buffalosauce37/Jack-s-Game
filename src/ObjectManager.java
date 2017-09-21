
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	 int random = new Random().nextInt(4000); 
	ArrayList<GameObject> objects;
	ArrayList<Projectile> projectiles;
	Player player;
	Projectile projectile;
	private int score = 0;
	
	long enemyTimer = 0;
	int enemySpawnTime = random;
	
	public ObjectManager(Player player) {
		objects = new ArrayList<GameObject>();
		projectiles = new ArrayList<Projectile>();
		this.player = player;
		objects.add(player);
		}

	public void addObject(GameObject o) {
		objects.add(o);
	}
	
	void addProjectile(Projectile projectile){
		projectiles.add(projectile);
	}

	public void update() {
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
	
	public void manageEnemies(){
		if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
			addObject(new Obsticle(new Random().nextInt(RunnerClass.width), 0, 50, 50));
			enemySpawnTime = new Random().nextInt(4000);
			enemyTimer = System.currentTimeMillis();
		}
	}

//	public void checkCollision() {
//		for (int i = 0; i < objects.size(); i++) {
//			for (int j = i + 1; j < objects.size(); j++) {
//				GameObject o1 = objects.get(i);
//				GameObject o2 = objects.get(j);
//				
//				if(o1.collisionBox.intersects(o2.collisionBox)){
//					if((o1 instanceof Alien && o2 instanceof Projectile) ||
//					   (o2 instanceof Alien && o1 instanceof Projectile)){
//						score++;
//						System.out.println(score);
//						o1.isAlive = false;
//						o2.isAlive = false;
//					}
//					else if((o1 instanceof Alien && o2 instanceof Rocketship) ||
//							(o2 instanceof Alien && o1 instanceof Rocketship)){
//						o1.isAlive = false;
//						o2.isAlive = false;
//					}
//	
//				}
//			}
//		}
//	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public void reset(){
		objects.clear();
	}
}
