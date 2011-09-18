package test;

import java.util.Date;

import enemies.SimpleEnemy;
import enemies.UberEnemy;
import gui.DeathPanel;

import javax.swing.JFrame;

import Stepper.Stepper;

import physics.Environment;
import player.Player;
import weapons.DomeWeapon;
import weapons.HomingWeapon;
import weapons.SimpleWeapon;
import weapons.SinWeapon;
import weapons.UberWeapon;
import weapons.Weapon;

public class TestDriver {
	static final long DELAY = 1000 / 50;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);
		Environment env = new Environment(800,600);
		DeathPanel panel = new DeathPanel(env);
		panel.setSize(800,600);
		frame.add(panel);
		Player p = new Player(400,500,env);
		Weapon w = new SimpleWeapon(0,0,env);
		p.weapon = w;
		p.armory.add(w);
		w.user = p;
		p.setMaxVelocity(100);
		frame.addKeyListener(p);
		Updater u = new Updater(env,panel,DELAY);
		while(env.width == 0);
		env.add(p);
	}

}
