package test;

import gui.DeathPanel;

import java.awt.Panel;

import Stepper.Step;
import Stepper.Stepper;

import physics.Environment;

public class Updater implements Step {
	Environment env;
	DeathPanel panel;
	long delay;
	
	public Updater(Environment e, DeathPanel p, long d) {
		env = e;
		panel = p;
		delay = d;
		Stepper s = new Stepper();
		s.addStep(this,delay);
	}

	public void step() {
		env.width = panel.getWidth();
		env.height = panel.getHeight();
		if(env.player == null || env.player.pause) return;
		env.next(delay);
		panel.repaint();
	}
	
	
}
