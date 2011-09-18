package weapons;

import java.awt.Color;

import physics.Environment;

import beams.Beam;

public class SimpleWeapon extends Weapon {

	public SimpleWeapon(double x, double y, Environment e) {
		super(x,y,e);
		color = Color.blue;
		health = 5;
		rank = 1;
		droneChance = 0.025;
	}
	
	public void createNew(double x, double y) {
		env.add(new SimpleWeapon(x,y,env));
	}
	@Override
	public void fire() {
		Beam b = new Beam(
				user.position[0] + 7.5,
				user.position[1] - 20,
				Color.RED,
				env
		);
		b.width = 5;
		b.height = 10;
		b.velocity[1] = -200;
		b.damage = 1;
		b.enemy = false;
		env.add(b);

	}

}
