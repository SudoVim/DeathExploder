package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import beams.CancelBeam;

import physics.Environment;
import physics.Item;
import weapons.Weapon;

public class Player extends Item implements KeyListener {
	boolean left;
	boolean right;
	boolean up;
	boolean down;
	boolean space;
	boolean bomb;
	boolean bombFired;
	static final double ACCEL = 100;
	double fireDelay;
	double fireCounter;
	public long points;
	public boolean pause;
	public int drones;
	boolean drone;
	public boolean droneReleased;
	public boolean returnDrones;
	public ArrayList<CancelBeam> fieldDrones;
	public long currency;
	public ArrayList<Weapon> armory;
	public boolean weaponSwitch;
	public boolean switched;
	
	public Player(int x, int y, Environment e) {
		super(2,e);
		position[0] = x;
		position[1] = y;
		width = 20;
		height = 20;
		bounded = true;
		fireDelay = 200;
		fireCounter = fireDelay;
		health = 100;
		maxHealth = 100;
		points = 0;
		pause = false;
		bomb = false;
		bombFired = false;
		drones = 0;
		drone = false;
		droneReleased = false;
		fieldDrones = new ArrayList<CancelBeam>();
		returnDrones = false;
		currency = points;
		armory = new ArrayList<Weapon>();
	}
	
	public void next(long t) {
		if(!left && !right && !up && !down) {
			acceleration[0] = 0;
			acceleration[1] = 0;
		} else if(left && !up && !down) {
			acceleration[0] = -1 * ACCEL;
			acceleration[1] = 0;
		} else if(right && !up && !down) {
			acceleration[0] = ACCEL;
			acceleration[1] = 0;
		} else if(up && !left && !right) {
			acceleration[0] = 0;
			acceleration[1] = -1 * ACCEL;
		} else if(down && !left && !right) {
			acceleration[0] = 0;
			acceleration[1] = ACCEL;
		} else if(left) {
			acceleration[0] = ACCEL / -1.4;
			if(up) {
				acceleration[1] = ACCEL / -1.4;
			} else {
				acceleration[1] = ACCEL / 1.4;
			}
		} else {
			acceleration[0] = ACCEL / 1.4;
			if(up) {
				acceleration[1] = ACCEL / -1.4;
			} else {
				acceleration[1] = ACCEL / 1.4;
			}
		}
		super.next(t);
		if(space) {
			fireCounter += t;
			if(fireCounter >= fireDelay) {
				fireCounter -= fireDelay;
				weapon.fire();
			}
		}
		if(drone && drones > 0 && !droneReleased) {
			CancelBeam b = new CancelBeam(position[0] + 5,
					position[1] - 5,env);
			b.height = 10;
			b.width = 10;
			b.velocity[1] = -400;
			env.add(b);
			drones--;
			droneReleased = true;
			fieldDrones.add(b);
		}
		if(returnDrones) {
			for(CancelBeam d : fieldDrones) {
				d.returnToPlayer();
			}
		}
		if(weaponSwitch && !switched) {
			int index = armory.indexOf(weapon);
			index++;
			if(index >= armory.size()) {
				index = 0;
			}
			weapon = armory.get(index);
			weapon.user = this;
			switched = true;
		}
	}
	
	public void dockDrone(CancelBeam b) {
		if(fieldDrones.remove(b)) {
			drones++;
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		int[] xVals = {
				(int)(position[0] + width / 2),
				(int)(position[0]),
				(int)(position[0] + width)
		};
		int[] yVals = {
				(int)(position[1]),
				(int)(position[1] + height),
				(int)(position[1] + height)
		};
		g.fillPolygon(xVals,yVals,3);
		double healthRatio = health / maxHealth;
		if(healthRatio < .3) {
			g.setColor(Color.RED);
		} else if(healthRatio < .7) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillRect(env.width - 10, 0, 10, 
				(int)(env.height * healthRatio));
	}

	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT: left = true; return;
		case KeyEvent.VK_RIGHT: right = true; return;
		case KeyEvent.VK_UP: up = true; return;
		case KeyEvent.VK_DOWN: down = true; return;
		case KeyEvent.VK_SPACE: space = true; return;
		case KeyEvent.VK_B: bomb = true; return;
		case KeyEvent.VK_C: returnDrones = true; return;
		case KeyEvent.VK_V: drone = true; return;
		case KeyEvent.VK_X: weaponSwitch = true; return;
		}

	}

	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_P) {
			pause = !pause;
		}
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT: left = false; return;
		case KeyEvent.VK_RIGHT: right = false; return;
		case KeyEvent.VK_UP: up = false; return;
		case KeyEvent.VK_DOWN: down = false; return;
		case KeyEvent.VK_SPACE: space = false; return;
		case KeyEvent.VK_B: bomb = false; bombFired = false; return;
		case KeyEvent.VK_C: returnDrones = false; return;
		case KeyEvent.VK_V: drone = false; droneReleased = false; return;
		case KeyEvent.VK_X: weaponSwitch = false; switched = false; return;
		}

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void collide(Item i) {
		return;
	}
}
