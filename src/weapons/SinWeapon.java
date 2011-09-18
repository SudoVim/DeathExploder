package weapons;

import java.awt.Color;

import physics.Environment;

import beams.SinBeam;

public class SinWeapon extends Weapon {

	public SinWeapon(double x, double y, Environment e) {
		super(x,y,e);
		health = 40;
		rank = 30;
		color = Color.CYAN;
		droneChance = .075;
	}
	
	@Override
	public void createNew(double x, double y) {
		env.add(new SinWeapon(x,y,env));
	}

	@Override
	public void fire() {
		SinBeam b1 = new SinBeam(
				user.position[0] + (width + 10) / 2,
			    user.position[1] - height - 10,
			    Color.GRAY,
			    env);
		SinBeam b2 = new SinBeam(
				user.position[0] + (width + 10) / 2,
			    user.position[1] - height - 10,
			    Color.GRAY,
			    env);
		b1.amplitude = user.width + 10;
		b2.amplitude = user.width + 10;
		b2.positive = -1;
		b1.damage = 2;
		b2.damage = 2;
		b1.enemy = false;
		b2.enemy = false;
		b1.height = 10;
		b1.width = 10;
		b2.height = 10;
		b2.width = 10;
		b1.partner = b2;
		env.add(b1);
		env.add(b2);
		
	}

}
