package weapons;

import java.awt.Color;

import physics.Environment;

import beams.HomingBeam;

public class HomingWeapon extends Weapon {

	public HomingWeapon(double x, double y, Environment e) {
		super(x,y,e);
		color = Color.white;
		health = 70;
		rank = 20;
	}
	
	public void createNew(double x, double y) {
		env.add(new HomingWeapon(x,y,env));
	}
	@Override
	public void fire() {
		HomingBeam b = new HomingBeam(
				user.position[0] + 7.5,
				user.position[1] - 10,
				Color.WHITE,
				env
		);
		b.width = 5;
		b.height = 5;
		b.velocity[1] = -200;
		b.damage = 1;
		b.enemy = false;
		env.add(b);
	}

}
