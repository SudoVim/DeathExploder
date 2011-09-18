package beams;

import java.awt.Color;

import enemies.Enemy;
import enemies.SpreadEnemy;
import enemies.Star;

import physics.Environment;
import physics.Item;
import weapons.Weapon;

public class Beam extends Item {
	public double damage;
	public boolean enemy;
	public boolean tagged;
	
	public Beam(double x, double y, Color c, Environment e) {
		super(2,e);
		color = c;
		position[0] = x;
		position[1] = y;
		bounded = false;
		tagged = false;
	}

	public void collide(Item i) {
		if(i instanceof Star) return;
		if(i instanceof Weapon) return;
		if(enemy && i instanceof Enemy) return;
		if(i instanceof Beam) return;
		if(!enemy && !(i instanceof Enemy)) return;
		i.damage(damage);
		//System.out.println(damage + " damage to " + i.health);
		die();
	}

}
