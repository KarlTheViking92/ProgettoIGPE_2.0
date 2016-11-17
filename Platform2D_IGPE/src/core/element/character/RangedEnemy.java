package core.element.character;

import core.World.World;
import core.element.Bullet;
import core.element.Position;

public class RangedEnemy extends AbstractCharacter {

	private boolean flag = false;

	public RangedEnemy(String name, int life, int damage, World w, Position p) {
		super(name, life, damage, w);
		this.setPosition(p);
		canShoot = true;
		this.bulletDifferenceSx = 41;
		this.setType("RangedEnemy");
		this.setWidth(80);
		this.setHeight(90);
	}

	@Override
	public void setPosition(Position p) {
		double x = p.getX();
		double y = p.getY();

		this.position = new Position(x, y);
	}

	@Override
	public boolean collide(Character c) {
		if (this.getX() + this.getWidth() < c.getX())
			return false;

		if (this.getX() > c.getX() + c.getWidth())
			return false;

		if (this.getY() + this.getHeight() < c.getY())
			return false;

		if (this.getY() + c.getHeight() > c.getY() + c.getHeight())
			return false;
		return true;
	}

	@Override
	public void searchPlayer(Player p) {
		if (p.getY() > (this.getY() - this.getWidth()) && p.getY() < this.getY() + this.getHeight()) {

			double distance = 5 * this.getWidth();

			if (p.getX() > this.getX()) {
				if (p.getX() < this.getX() + (distance)) {
					flag = true;
					directionNum = 1;
				}
				setDirection(Direction.RIGHT);
			} else {
				if (p.getX() > this.getX() - (distance)) {
					flag = true;
					directionNum = -1;
					setDirection(Direction.LEFT);
				}
			}
		} else {
			flag = false;
			setDirection(Direction.STOP);
		}
	}

	@Override
	public void shoot() {

		if (canShoot) {

			if (oldDirection == Direction.LEFT) {
				bullets.add(new Bullet(this.getX() - bulletDifferenceSx, this.getY() + this.getHeight() / 2,
						oldDirection, world, true));
			} else if (oldDirection == Direction.RIGHT) {
				bullets.add(new Bullet(this.getX() + this.getWidth() + bulletDifferenceDx,
						this.getY() + this.getHeight() / 2, oldDirection, world, true));
			}

			shoot = true;

			canShoot = false;
			lastMillis = System.currentTimeMillis();
		}
	}

	@Override
	public void update() {

		long current = System.currentTimeMillis();
		double y = this.getY();
		double x = this.getX();

		switch (direction) {
		case LEFT:
			oldDirection = Direction.LEFT;
			break;
		case RIGHT:
			oldDirection = Direction.RIGHT;
			break;

		default:
			break;
		}

		if (flag && canShoot && current - lastMillis >= 1994) {
			this.shoot();
			canShoot = true;
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

		if (!world.checkPlayerCollision(this, x, y)) {
			position.setY(y);
		}
	}

}
