package weapons;

import java.awt.Color;

import physics.Environment;

import beams.Beam;

public class UberWeapon extends Weapon {

	public UberWeapon(double x, double y, Environment e) {
		super(x,y,e);
		color = Color.orange;
		health = 20;
		rank = 10;
		droneChance = .05;
	}
	
	public void createNew(double x, double y) {
		env.add(new UberWeapon(x,y,env));
	}
	@Override
	public void fire() {
		Beam b1 = new Beam(
				user.position[0],
				user.position[1] - 10,
				Color.ORANGE,
				env
		);
		b1.width = 5;
		b1.height = 10;
		b1.velocity[1] = -200;
		b1.damage = 1;
		b1.enemy = false;
		Beam b2 = new Beam(
				user.position[0] + 7.5,
				user.position[1] - 10,
				Color.ORANGE,
				env
		);
		b2.width = 5;
		b2.height = 10;
		b2.velocity[1] = -200;
		b2.damage = 1;
		b2.enemy = false;
		Beam b3 = new Beam(
				user.position[0] + 15,
				user.position[1] - 10,
				Color.ORANGE,
				env
		);
		b3.width = 5;
		b3.height = 10;
		b3.velocity[1] = -200;
		b3.damage = 1;
		b3.enemy = false;
		env.add(b1);
		env.add(b2);
		env.add(b3);
	}

}
