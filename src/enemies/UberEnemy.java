package enemies;

import java.awt.Color;

import physics.Environment;
import player.Player;
import weapons.UberWeapon;

import beams.CircleBeam;

public class UberEnemy extends SimpleEnemy {
	
	public UberEnemy(double x, double y, Environment e) {
		super(x,y,e);
		width = 50;
		height = 50;
		color = Color.CYAN;
		health = 15;
		maxHealth = 15; 
		cost = 30;
		points = 10;
		weaponDrop = new UberWeapon(0,0,env);
		dropRate = .2;
		damage = 5;
		accel = 70;
	}
	
	public Enemy makeEnemy(double x, double y) {
		Enemy e = new UberEnemy(x,y,env);
		if(env.add(e)) return e;
		return null;
	}
	
	public Enemy makeRandomEnemy() {
		double x = Math.random() * env.width - width;
		double y = Math.random() * env.height / 2;
		return makeEnemy(x,y);
	}
	
	public void fire() {
		double x = position[0];
		double y = position[1] + height + 10;
		double x2 = position[0] + width - 10;
		CircleBeam b = new CircleBeam(x,y,Color.MAGENTA,env);
		CircleBeam b2 = new CircleBeam(x2,y,Color.MAGENTA,env);
		Player p = new Player(0,0,env);
		for(int i = 0; i < env.items.size(); i++) {
			if(env.items.get(i) instanceof Player) {
				p = (Player)env.items.get(i);
				break;
			}
		}
		double mag1 = disp(p.position,b.position);
		double mag2 = disp(p.position,b2.position);
		double[] ud1 = unitDisp(p.position,b.position);
		double[] ud2 = unitDisp(p.position,b2.position);
		b.width = 10;
		b2.width = 10;
		b.height = 10;
		b2.height = 10;
		b.velocity[0] = ud1[0] * 100;
		b.velocity[1] = ud1[1] * 100;
		b2.velocity[0] = ud2[0] * 100;
		b2.velocity[1] = ud2[1] * 100;
		b.damage = damage;
		b2.damage = damage;
		b.enemy = true;
		b2.enemy = true;
		env.add(b);
		env.add(b2);
	}
}
