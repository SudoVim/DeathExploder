package beams;

import java.awt.Color;
import java.util.ArrayList;

import physics.Environment;
import physics.Item;
import player.Player;

public class CancelBeam extends CircleBeam {
	public double maxVelocity;
	public double veloc;
	public boolean playerReturn;
	ArrayList<Item> target;
	ArrayList<CancelBeam> others;
	public CancelBeam(double x, double y, Environment e) {
		super(x,y,Color.YELLOW,e);
		maxVelocity = 400;
		bounded = true;
		width = 5;
		height = 5;
		target = new ArrayList<Item>();
		veloc = 400;
		playerReturn = false;
		others = new ArrayList<CancelBeam>();
		for(int i = 0; i < env.items.size(); i++) {
			if(env.items.get(i) instanceof CancelBeam && env.items.get(i) != this) {
				others.add((CancelBeam)env.items.get(i));
			}
		}
	}
	
	public void next(long t) {
		double[] displacement = {1000,1000,1000,1000,1000};
		target.remove(env.player);
		for(int i = 0; i < target.size(); i++) { 
			((Beam)target.get(i)).tagged = false;
		}
		target = new ArrayList<Item>();
		for(int i = 0; i < env.items.size(); i++) {
			if(env.items.get(i) instanceof Beam) {
				Beam temp = (Beam)env.items.get(i);
				if(!temp.enemy) {
					continue;
				}
				if(temp.tagged) continue; 
				double disp = Math.abs(disp(temp.position,position));
				boolean addIt = true;
				for(int k = 0; k < others.size(); k++) {
					if(env.items.get(k) != this) {
						if(disp(env.items.get(k).position,temp.position) < disp) {
							addIt = false;
						}
					}
				}
				if(!addIt) {
					continue;
				}
				for(int j = 0; j < displacement.length; j++) {
					if(disp < displacement[j]) {
						target.add(j, temp);
						((Beam)temp).tagged = true;
						for(int k = displacement.length - 1; k >= j + 1; k--) {
							displacement[k] = displacement[k - 1];
						}
						displacement[j] = disp;
						break;
					}
				}
			}
		}
		if(playerReturn) {
			target.add(0, env.player);
		}
		if(target.size() == 0) {
			target.add(env.player);
		}
		if(target.size() != 0) {
			double[] ud = unitDisp(target.get(0).position,position);
			velocity[0] = veloc * ud[0];
			velocity[1] = veloc * ud[1];
		}
		super.next(t);
	}
	
	public void returnToPlayer() {
		playerReturn = true;
	}
	
	public void die() {
		for(int i = 0; i < others.size(); i++) {
			others.get(i).others.remove(this);
		}
		super.die();
	}
	
	public void collide(Item i) {
		if(i instanceof Beam) {
			Beam temp = (Beam)i;
			if(temp.enemy) {
				target.remove(temp);
				temp.die();
			}
		} else if(i instanceof Player && playerReturn) {
			((Player) i).dockDrone(this);
			for(int j = 0; j < target.size(); j++) {
				if(target.get(j) instanceof Beam) {
					((Beam)target.get(j)).tagged = false;
				}
			}
			die();
		}
	}
}
