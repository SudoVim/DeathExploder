package beams;

import java.awt.Color;
import java.awt.Graphics;

import physics.Environment;

public class CircleBeam extends Beam {
	
	public CircleBeam(double x, double y, Color c, Environment e) {
		super(x,y,c,e);
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int)position[0], (int)position[1], 
				width, height);
	}
}
