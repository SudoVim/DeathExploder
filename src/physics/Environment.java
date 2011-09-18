package physics;

import java.util.ArrayList;

import player.Player;

import enemies.Enemy;
import enemies.EnemySelector;
import enemies.Star;

public class Environment {
	public ArrayList<Item> items;
	public int height;
	public int width;
	double probability;
	static final boolean makeStars = false;
	EnemySelector es;
	public Player player;
	public static final int MAX_ENEMIES = 50;
	
	public Environment(int x, int y) {
		items = new ArrayList<Item>();
		height = y;
		width = x;
		probability = 200;
		es = new EnemySelector(this);
		player = null;
	}
	
	public int numEnemies() {
		int ret = 0;
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i) instanceof Enemy) {
				ret++;
			}
		}
		
		return ret;
	}
	
	public boolean add(Item i) {
		if(i instanceof Enemy && numEnemies() >= MAX_ENEMIES) return false;
		if(i instanceof Player) {
			player = (Player)i;
			if(player.points == 0) {
				es.makeEnemies(3);
			} else if(player.points == -1) {
			} else {
				es.makeEnemies(player.points);
			}
		}
		items.add(i);
		return true;
	}
	
	public boolean complete() {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i) instanceof Enemy) {
				return false;
			}
		}
		return true;
	}
	
	public void remove(Item i) {
		if(i instanceof Enemy) {
			Enemy e = (Enemy)i;
			double base = e.points * .7;
			double random = e.points * .6;
			int add = (int)(base + random * Math.random());;
			player.points += Math.max(add, 1);
			player.currency += Math.max(add, 1);
		}
		items.remove(i);
	}
	
	public void next(long t) {
		for(int i = 0; i < items.size(); i++) {
			items.get(i).next(t);
		}
		for(int i = 0; i < items.size(); i++) {
			for(int j = i + 1; j < items.size(); j++) {
				Item i1 = items.get(i);
				Item i2 = items.get(j);
				double x1 = i1.position[0];
				double x2 = i2.position[0];
				double y1 = i1.position[1];
				double y2 = i2.position[1];
				if(x1 - x2 > 0 && x1 - x2 < i2.width) {
					if(y1 - y2 > 0 && y1 - y2 < i2.height) {
						i1.collide(i2);
						i2.collide(i1);
					}
					if(y2 - y1 > 0 && y2 - y1 < i1.height) {
						i1.collide(i2);
						i2.collide(i1);
					}
				}
				if(x2 - x1 > 0 && x2 - x1 < i1.width) {
					if(y1 - y2 > 0 && y1 - y2 < i2.height) {
						i1.collide(i2);
						i2.collide(i1);
					}
					if(y2 - y1 > 0 && y2 - y1 < i1.height) {
						i1.collide(i2);
						i2.collide(i1);
					}
				}
			}
		}
		if(makeStars) {
			if(Math.random() * 100 > t * probability / 1000) {
				Star s = new Star(Math.random() * width,0,this);
				s.width = (int)(Math.random() * 5 + 5);
				s.height = s.width;
				add(s);
			}
		}
		if(complete()) {
			if(player == null) return;
			if(es.makeEnemies(player.currency)) {
				player.currency = 0;
				es.makeEnemies(3);
			}
		}
	}
}
