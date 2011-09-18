package enemies;

import java.util.ArrayList;

import physics.Environment;


public class EnemySelector {
	ArrayList<Enemy> choices;
	
	public EnemySelector(Environment e) {
		choices = new ArrayList<Enemy>();
		choices.add(new SimpleEnemy(0,0,e));
		choices.add(new UberEnemy(0,0,e));
		choices.add(new FollowingEnemy(0,0,e));
		choices.add(new SpreadEnemy(0,0,e));
	}
	
	public boolean makeEnemies(long n) {
		int i = choices.size() - 1;
		if(n > choices.get(i).cost * 14) {
			while(i >= 0) {
				choices.get(i).maxHealth *= 2;
				choices.get(i).health *= 2;
				choices.get(i).damage *= 2;
				i--;
			}
			return true;
		}
		while(n > 0) {
			while(choices.get(i).cost > n) {
				i--;
				if(i < 0) break;
			}
			if(i < 0) break;
			Enemy test = choices.get(i).makeRandomEnemy();
			if(test == null) {
				break;
			}
			test.maxHealth = choices.get(i).maxHealth;
			test.health = choices.get(i).health;
			test.damage = choices.get(i).damage;
			test.points = choices.get(i).points;
			test.cost = choices.get(i).cost;
			n -= choices.get(i).cost;
		}
		return false;
	}
}
