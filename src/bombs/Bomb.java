package bombs;

import physics.Environment;
import physics.Item;

public abstract class Bomb extends Item {
	public Bomb(double x, double y, Environment e) {
		super(2,e);
		position[0] = x;
		position[1] = y;
	}
	@Override
	public void collide(Item i) {
		// TODO Auto-generated method stub

	}

}
