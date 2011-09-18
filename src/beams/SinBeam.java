package beams;

import java.awt.Color;
import java.awt.Graphics;

import physics.Environment;

public class SinBeam extends Beam {
	double startY;
	double startX;
	public double waveLength;
	public double amplitude;
	public int positive;
	public SinBeam partner;
	
	public SinBeam(double x, double y, Color c, Environment e) {
		super(x,y,c,e);
		startY = y;
		velocity[1] = -200;
		startX = x;
		waveLength = 200;
		amplitude = 15;
		positive = 1;
		partner = null;
	}
	
	public void next(long t) {
		super.next(t);
		double theta = ((startY - position[1]) % waveLength)
		               * 2 * Math.PI / waveLength;
		position[0] = startX + positive * amplitude 
		              * Math.sin(theta);
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int)position[0],(int)position[1],
				width,height);
		if(partner == null) return;
		if(partner.dead) {
			partner = null;
			return;
		}
		double x1 = position[0] + width / 2;
		double x2 = partner.position[0] + partner.width / 2;
		double y1 = position[1] + height / 2;
		double y2 = partner.position[1] + partner.height / 2;
		g.setColor(Color.RED);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}
}
