package weapons;

import physics.Environment;
import physics.Item;
import player.Player;

public abstract class Weapon extends Item {
	public Player user;
	public int rank;
	public double droneChance;

	public Weapon(double x, double y, Environment e) {
		super(2,e);
		position[0] = x;
		position[1] = y;
		user = null;
		bounded = false;
		velocity[1] = 50;
		width = 10;
		height = 10;
		rank = 0;
		droneChance = 0;
	}
	
	public abstract void fire();

	public void collide(Item i) {
		if(i instanceof Player) {
			Player player = (Player)i;
			if(player.weapon.rank < rank) {
				player.weapon = this;
				user = player;
			}
			player.health += health;
			if(Math.random() < droneChance) {
				player.drones++;
			}
			if(player.maxHealth < player.health) {
				player.health = player.maxHealth;
			}
			die();
			for(int j = 0; j < player.armory.size(); j++) {
				if(player.armory.get(j).rank == this.rank) {
					return;
				}
			}
			player.armory.add(this);
		}

	}
	
	public abstract void createNew(double x, double y);

}
