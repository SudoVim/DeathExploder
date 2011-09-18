package enemies;

import java.awt.Color;
import java.awt.Graphics;

import physics.Environment;
import physics.Item;
import weapons.Weapon;

public abstract class Enemy extends Item {
	public int points;
	public Weapon weaponDrop;
	public double dropRate;
	public int minFire = 1000;
	public int maxFire = 5000;
	public int fireCounter;
	public int currentFire;
	public int damage;
	public int level;
	public int cost;
	public Enemy(double x, double y,Environment e) {
		super(2,e);
		position[0] = x;
		position[1] = y;
		points = 0;
		cost = 0;
		weaponDrop = null;
		dropRate = 0;
		bounded = true;
	}
	
	public void next(long t) {
		super.next(t);
		ai(t);
		if(checkFire(t)) {
			fire();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		double healthRatio = health / maxHealth;
		if(healthRatio < .3) {
			g.setColor(Color.RED);
		} else if(healthRatio < .7) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillRect((int)position[0], (int)(position[1] - 5), 
				(int)(width * healthRatio), 
				5);
	}
	
	public void die() {
		if(weaponDrop != null && Math.random() <= dropRate) {
			double x = position[0] + (width - weaponDrop.width) / 2.0;
			double y = position[1] + height + 10;
			weaponDrop.createNew(x,y);
		}
		super.die();
	}
	
	public abstract void ai(long t);
	public abstract boolean checkFire(long t);
	public abstract void fire();
	public abstract Enemy makeEnemy(double x, double y);
	public abstract Enemy makeRandomEnemy();
}
