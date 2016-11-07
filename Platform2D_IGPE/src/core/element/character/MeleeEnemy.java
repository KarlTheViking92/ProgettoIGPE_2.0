package core.element.character;

import core.World.World;
import core.element.Position;

public class MeleeEnemy extends AbstractCharacter {

	private int direction = 1;
	private double delta = 1;
	private boolean flag = false;

	public MeleeEnemy(String name, int life, int damage, World w, Position p) {
		super(name, life, damage, w);
		this.setPosition(p);
	}

	public void searchPlayer(Player p) {
		// se il player e il mostro sono sulla stessa x e non vi sono pareti in
		// mezzo il mostro farà di tutto per raggiungere il player

		// sotto deve avere un blocco questo grandissimo maiale
		
		
		// 
		if (p.getY() == this.getY()) {
			if (p.getX() > this.getX())
				direction = 1;
			else
				direction = -1;

			flag = true;
		}

		flag = false;
	}

	@Override
	public void update() {

		if (flag) {
			double x = this.getX() + this.getHeight() + (delta * direction);

			if (direction < 0)
				x = this.getX() + (delta * direction);

			if (world.checkPlayerCollision(this, x, this.getY())) {
				direction *= -1;
			}
			this.setX(this.getX() + (delta * direction));

		}
	}
}
