package core.element.character;

import core.World.World;
import core.element.Position;

public class MeleeEnemy extends AbstractCharacter {

	// private Direction dir = Direction.STOP;
	private int direction = 1;
	private double delta = 1;
	private boolean flag = false;
	private double X;

	public MeleeEnemy(String name, int life, int damage, World w, Position p) {
		super(name, life, damage, w);
		this.setPosition(p);
		this.setType("MeleeEnemy");
	}

	@Override
	public void searchPlayer(Player p) {
		// se il player e il mostro sono sulla stessa x e non vi sono pareti in
		// mezzo il mostro farà di tutto per raggiungere il player
		// sotto deve avere un blocco questo grandissimo maiale

		if (p.getY() > this.getY() && p.getY() < this.getY() + this.getHeight()) {
			if (p.getX() > this.getX()) {
				direction = 1;
				X = p.getX();
				setDirection(Direction.RIGHT);
				// System.out.println("x del player mannaia a dio è: " +
				// p.getX());
			} else {
				direction = -1;
				X = p.getX();
				setDirection(Direction.LEFT);
				// System.out.println("x del player mannaia la madonna è: " +
				// p.getX());
			}
			flag = true;
		} else {
			setDirection(Direction.STOP);
			flag = false;
		}
	}

	@Override
	public void update() {

		double Y = this.getY();
		double x = this.getX();

		if (flag) {

			if (X == this.getX()) {
				this.setX(X);
				// System.out.println("muori e la x del nemico è: " + X);
				return;
			}
			// System.out.println("x del nemico: " + x + " x del player: " + X);

			if (world.checkPlayerCollision(this, x, this.getY())) {
				direction *= -1;

			}
			x = this.getX() + (delta * direction);
		}

		if (falling) {
			if (!world.checkPlayerCollision(this, x, getY() + currentFallSpeed)) {
				Y = position.getY() + currentFallSpeed;
				if (currentFallSpeed <= MAXFALLSPEED) {
					currentFallSpeed += 0.1;
				}

			} else {
				currentFallSpeed = 0.1;
				falling = false;
			}
		}

		if (!world.checkPlayerCollision(this, x, getY() + currentFallSpeed)) {
			falling = true;
		}

		this.setX(x);
	}

}
