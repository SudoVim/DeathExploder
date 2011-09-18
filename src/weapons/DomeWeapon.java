package weapons;

import java.awt.Color;

import beams.Beam;
import beams.CircleBeam;
import beams.HomingBeam;

import physics.Environment;

public class DomeWeapon extends Weapon {

	public DomeWeapon(double x, double y, Environment e) {
		super(x,y,e);
		rank = 50;
		color = Color.GRAY;
		health = 80;
		droneChance = .1;
	}
	
	@Override
	public void createNew(double x, double y) {
		env.add(new DomeWeapon(x,y,env));
	}

	@Override
	public void fire() {
		for(int i = 0; i < 5; i++) {
			double theta = 3 * Math.PI / 4 - i * Math.PI / 8;
			double x = user.position[0] + user.width / 2;
			double y = user.position[1] + user.height / 2;
			x += 10 * Math.cos(theta);
			y -= 10 * Math.sin(theta);
			int f = i + 2;
			if(i == 3) f = 3;
			else if(i == 4) f = 2;
			CircleBeam b = new CircleBeam(x,y,Color.RED,env);
			b.width = f * 2 + 4;
			b.height = f * 2 + 4;
			b.damage = f / 2;
			b.enemy = false;
			b.velocity[0] = 300 * Math.cos(theta);
			b.velocity[1] = -300 * Math.sin(theta);
			env.add(b);
		}

	}

}
