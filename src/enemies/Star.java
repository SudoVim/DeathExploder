package enemies;

import java.awt.Color;
import java.awt.Graphics;

import physics.Environment;
import physics.Item;

public class Star extends Item {

	public Star(double x, double y, Environment e) {
		super(2,e);
		position[0] = x;
		position[1] = y;
		velocity[1] = 50;
		bounded = false;
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)position[0], (int)position[1], 
				width, height);
	}
	
	public void collide(Item i) {

	}

}
