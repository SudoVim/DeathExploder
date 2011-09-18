package enemies;

import java.awt.Color;

import physics.Environment;
import physics.Item;
import player.Player;
import weapons.SinWeapon;

import beams.Beam;
import beams.CircleBeam;

public class FollowingEnemy extends SimpleEnemy {
	public boolean atPlayer;
	public FollowingEnemy(double x, double y, Environment e) {
		super(x,y,e);
		cost = 300;
		points = 200;
		health = 20;
		maxHealth = 20;
		dropRate = .2;
		weaponDrop = new SinWeapon(0,0,env);
		height = 20;
		width = 20;
		color = Color.WHITE;
		damage = 15 ;
		maxVelocity = 80;
		atPlayer = false;
		accel = 200;
	}
	
	@Override
	public void ai(long t) {
		Player p = env.player;
		if(Math.abs(position[0] - p.position[0]) < 10) {
			atPlayer = true;
			target[1] = Math.random() * (env.height / 2);
		} else {
			atPlayer = false;
		}
			target[0] = p.position[0];
		if(!atPlayer) {
			double dx = target[0] - position[0];
			double dy = target[1] - position[1];
			double mag = Math.sqrt(dx * dx + dy * dy);
			dx /= mag;
			dy /= mag;
			acceleration[0] = dx * accel;
			acceleration[1] = dy * accel;
		} else {
			acceleration[0] = 0;
			acceleration[1] = 0;
		}
	}

	@Override
	public void fire() {
		Beam b = new CircleBeam(position[0] + (width + 15) / 2,
				position[1] + height + 10,
				Color.GREEN,
				env);
		b.height = 15;
		b.width = 15;
		b.damage = damage;
		b.velocity[1] = 200;
		b.enemy = true;
		env.add(b);
	}

	@Override
	public Enemy makeEnemy(double x, double y) {
		Enemy e = new FollowingEnemy(x,y,env);
		if(env.add(e)) return e;
		return null;
	}

	@Override
	public Enemy makeRandomEnemy() {
		double x = Math.random() * env.width - width;
		double y = Math.random() * env.height / 2;
		return makeEnemy(x,y);
	}

	@Override
	public void collide(Item i) {
		return;

	}

}
