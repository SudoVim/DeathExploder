package enemies;

import java.awt.Color;

import physics.Environment;

import weapons.DomeWeapon;

import beams.CircleBeam;

public class SpreadEnemy extends SimpleEnemy {

	public SpreadEnemy(double x, double y, Environment e) {
		super(x,y,e);
		damage = 10;
		height = 30;
		width = 30;
		color = Color.blue;
		weaponDrop = new DomeWeapon(0,0,env);
		dropRate = .2;
		maxVelocity = 150;
		cost = 3000;
		points = 2000;
		health = 30;
		maxHealth = 30;
		accel = 80;
	}
	
	public void fire() {
		for(int i = 0; i < 3; i++) {
			double theta = i * Math.PI / 4 + Math.PI / 4;
			double x = position[0] + width / 2;
			double y = position[1] + height / 2;
			CircleBeam b = new CircleBeam(x + Math.cos(theta) * 30,
					y + Math.sin(theta) * 30,
					Color.GRAY, env);
			b.damage = damage;
			b.enemy = true;
			b.width = 8;
			b.height = 8;
			b.velocity[0] = Math.cos(theta) * maxVelocity;
			b.velocity[1] = Math.sin(theta) * maxVelocity;
			env.add(b);
		}
	}
	
	public Enemy makeEnemy(double x, double y) {
		Enemy e = new SpreadEnemy(x,y,env);
		if(env.add(e)) return e;
		return null;
	}
	
	public Enemy makeRandomEnemy() {
		double x = Math.random() * env.width - width;
		double y = Math.random() * env.height / 2;
		return makeEnemy(x,y);
	}
}
