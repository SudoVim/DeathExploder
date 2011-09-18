package beams;

import java.awt.Color;
import java.awt.Graphics;

import enemies.Enemy;

import physics.Environment;
import physics.Item;

public class HomingBeam extends Beam {
	double vel = 200;
	
	public HomingBeam(double x, double y, Color c, Environment e) {
		super(x,y,c,e);
		velocity[1] = -1 * vel;
		damage = 1;
	}
	
	public void next(long t) {
		int target = -1;
		double targetdisp = 0;
		for(int i = 0; i < env.items.size(); i++) {
			Item it = env.items.get(i);
			if(!(it instanceof Enemy)) continue;
			if(target == -1) {
				target = i;
				targetdisp = disp(env.items.get(target).position,
						position);
				continue;
			}
			double currentdisp = disp(env.items.get(i).position,
					position);
			if(currentdisp < targetdisp) {
				target = i;
				targetdisp = currentdisp;
			}
		}
		if(target == -1) {
			super.next(t);
			return;
		}
		Item it = env.items.get(target);
		double x = it.position[0] - position[0];
		double y = it.position[1] - position[1];
		x /= targetdisp;
		y /= targetdisp;
		velocity[0] = x * vel;
		velocity[1] = y * vel;
		super.next(t);
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int)position[0], 
				(int)position[1], 
				width, height);
	}
}
