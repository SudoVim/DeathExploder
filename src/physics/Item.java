package physics;

import java.awt.Color;
import java.awt.Graphics;

import weapons.Weapon;

public abstract class Item {
	public double[] position;
	public double[] velocity;
	public double[] acceleration;
	public double maxVelocity;
	public int width;
	public int height;
	public Environment env;
	public boolean bounded;
	public double health;
	public double maxHealth;
	public Color color;
	public Weapon weapon;
	public boolean dead;
	
	public Item(int n, Environment e) {
		position = new double[n];
		velocity = new double[n];
		acceleration = new double[n];
		maxVelocity = Double.MAX_VALUE;
		width = 0;
		height = 0;
		env = e;
		health = 0;
		weapon = null;
		dead = false;
	}
	
	public Item(double[] p, double[] v, double[] a) {
		position = p;
		velocity = v;
		acceleration = a;
		maxVelocity = Double.MAX_VALUE;
	}
	
	public void fire() {
		weapon.fire();
	}
	
	public void setMaxVelocity(double d) {
		maxVelocity = d;
	}
	
	public void next(long t) {
		double time = t / 1000.0;
		for(int i = 0; i < position.length; i++) {
			velocity[i] += acceleration[i] * time;
		}
		if(mag(velocity) >= maxVelocity) {
			double mag = mag(velocity);
			for(int i = 0; i < velocity.length; i++) {
				velocity[i] *= maxVelocity / mag;
			}
		}
		for(int i = 0; i < position.length; i++) {
			position[i] += velocity[i] * time;
		}
		if(!bounded) {
			if(position[0] < -1 * width) die();
			if(position[1] < -1 * height) die();
			if(position[0] > env.width + width) die();
			if(position[1] > env.height + height) die();
			return;
		}
		if(position[0] < 0) {
			position[0] = 0;
			velocity[0] = 0;
		} 
		if(position[0] > env.width - width && env.width > 0) {
			position[0] = env.width - width;
			velocity[0] = 0;
		}
		if(position[1] < 0) {
			position[1] = 0;
			velocity[1] = 0;
		}
		if(position[1] > env.height - height && env.height > 0) {
			position[1] = env.height - height;
			velocity[1] = 0;
		}
	}
	
	public static double mag(double[] d) {
		double total = 0;
		for(int i = 0; i < d.length; i++) {
			total += d[i] * d[i];
		}
		return Math.sqrt(total);
	}
	
	public static double disp(double[] p1, double[] p2) {
		double[] d = new double[p1.length];
		for(int i = 0; i < p1.length && i < p2.length; i++) {
			d[i] = p1[i] - p2[i];
		}
		return mag(d);
	}
	
	public static double[] unitDisp(double[] p1, double[] p2) {
		double mag = disp(p1,p2);
		double[] ret = new double[p1.length];
		for(int i = 0; i < ret.length; i++) {
			double d = p1[i] - p2[i];
			ret[i] = d / mag;
		}
		return ret;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect((int)position[0], 
				(int)position[1], 
				width, height);
	}
	
	public void die() {
		dead = true;
		env.remove(this);
	}
	
	public void damage(double n) {
		health -= n;
		if(health <= 0) {
			die();
		}
	}
	
	public abstract void collide(Item i);
}
