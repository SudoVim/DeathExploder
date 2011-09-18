package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Stepper.Step;
import Stepper.Stepper;

import physics.Environment;
import physics.Item;
import player.Player;

public class DeathPanel extends JPanel {
	public Environment env;
	
	public DeathPanel(Environment e) {
		env = e;
		env.width = getWidth();
		env.height = getHeight();
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(),getHeight());
		for(int i = 0; i < env.items.size(); i++) {
			env.items.get(i).paint(g);
		}
		if(env.player == null) return;
		g.setColor(Color.WHITE);
		g.drawString("" + env.player.points, 0, 10);
		g.setColor(Color.YELLOW);
		g.drawString("" + env.player.drones, 0, 20);
	}
}
