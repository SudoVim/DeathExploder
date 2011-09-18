package enemies;

import java.awt.Color;

import physics.Environment;
import physics.Item;
import weapons.SimpleWeapon;

import beams.Beam;

public class SimpleEnemy extends Enemy {
	double[] target;
	public double accel;

	public SimpleEnemy(double x, double y, Environment e) {
		super(x,y,e);
		target = new double[2];
		target[0] = x;
		target[1] = y;
		width = 30;
		height = 30;
		color = Color.yellow;
		bounded = true;
		health = 5;
		maxHealth = 5;
		currentFire = 0;
		cost = 1;
		points = 1;
		weaponDrop = new SimpleWeapon(0,0,env);
		dropRate = .2;
		damage = 5;
		maxVelocity = 100;
		accel = 100;
	}
	
	public Enemy makeEnemy(double x, double y) {
		Enemy e = new SimpleEnemy(x,y,env);
		if(env.add(e)) return e;
		return null;
	}
	
	public Enemy makeRandomEnemy() {
		double x = Math.random() * env.width - width;
		double y = Math.random() * env.height / 2;
		return makeEnemy(x,y);
	}
	
	public void ai(long t) {
		if(disp(target,position) < 50) {
			target[0] = Math.random() * (env.width - width);
			target[1] = Math.random() * (env.height / 2);
		}
		double dx = target[0] - position[0];
		double dy = target[1] - position[1];
		double mag = Math.sqrt(dx * dx + dy * dy);
		dx /= mag;
		dy /= mag;
		acceleration[0] = dx * accel;
		acceleration[1] = dy * accel;
	}
	
	public boolean checkFire(long t) {
		if(currentFire == 0) {
			currentFire = (int)(Math.random() * (maxFire - minFire)
					+ minFire);
		}
		fireCounter += t;
		if(fireCounter >= currentFire) {
			currentFire = 0;
			fireCounter = 0;
			return true;
		}
		return false;
	}
	
	public void fire() {
		double x = position[0] + (width / 2) - 2.5;
		double y = position[1] + height + 10;
		Beam b = new Beam(x,y,Color.MAGENTA,env);
		b.width = 5;
		b.height = 10;
		b.velocity[1] = 100;
		b.damage = damage;
		b.enemy = true;
		env.add(b);
	}

	public void collide(Item i) {
		return;
	}

}
