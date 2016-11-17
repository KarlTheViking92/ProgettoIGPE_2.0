package core.element.character;

import core.World.World;
import core.element.Position;
import core.gameManagers.PlayManager;

public class MeleeEnemy extends AbstractCharacter {

	private double delta = 1;

	public MeleeEnemy(String name, int life, int damage, World w, Position p) {
		super(name, life, damage, w);
		this.setPosition(p);
		this.setType("MeleeEnemy");
		this.setWidth(50);
		this.setHeight(80);
	}

	@Override
	public void setPosition(Position p) {
		double x = p.getX();
		double y = p.getY();

		this.position = new Position(x, y);
	}

	@Override
	public boolean collide(Character c) {
		if (this.getX() + c.getWidth() * 2.5 < c.getX())
			return false;

		if (this.getX() + c.getWidth() > c.getX() + c.getWidth())
			return false;

		if (this.getY() + this.getHeight() < c.getY())
			return false;

		if (this.getY() + c.getHeight() > c.getY() + c.getHeight())
			return false;
		return true;
	}

	@Override
	public void update() {
		double y = this.getY();
		double x = this.getX();

		switch (direction) {
		case LEFT:
			x += delta * directionNum;
			break;
		case RIGHT:
			x += delta * directionNum;
			break;

		default:
			break;
		}

		if (falling) {
			if (!world.checkPlayerCollision(this, x, y + currentFallSpeed)) {

				y = position.getY() + currentFallSpeed;
				if (currentFallSpeed <= MAXFALLSPEED) {
					currentFallSpeed += 0.1;
				}

			} else {
				currentFallSpeed = 0.1;
				falling = false;
			}
		}
		if (!world.checkPlayerCollision(this, x, y + currentFallSpeed)) {
			falling = true;
		}
		if (!world.checkPlayerCollision(this, x, y) && !this.isDead()) {

			position.setY(y);
			position.setX(x);

			Player p = PlayManager.getInstance().getPlayer();
			if (this.collide(p)) {
				attacking = true;
				p.kill();
			} else {
				attacking = false;
			}
		}
	}
}