package core.element.character;

import java.util.ArrayList;
import java.util.List;

import core.World.World;
import core.element.Bullet;
import core.element.Item;
import core.element.Position;

public abstract class AbstractCharacter implements Character {

	protected boolean canShoot = true;
	private String name, type;
	private int life;
	protected Position position;
	private Position lastSpawnPoint;
	private int damage;
	protected int bulletDifferenceSx = 45; // delta decrement
	protected int bulletDifferenceDx = 5;// delta increment
	protected boolean attacking = false;
	protected int directionNum = 1;
	protected Direction direction = Direction.STOP;
	private double delta = 0;
	private double[] d = new double[] { 0.0, 0.0 };
	protected int gems = 0;
	protected Direction oldDirection = Direction.RIGHT;
	protected ArrayList<Bullet> bullets = new ArrayList<>();
	protected int HEIGHT = 45;
	protected int WIDTH = 30;
	protected long lastMillis;
	protected boolean shoot = false;

	private final float VELOCITY_X = 3.0f;

	private float vx;

	protected boolean canJump = true, jumping = false, superJumping = false, falling = true, grounded = false;
	private boolean blockMoving = false;
	private boolean doubleJump = false, canDoubleJump = true, canSuperJump = true;
	// jumping
	private final double JUMPSPEED = 4;
	private double currentJumpSpeed = JUMPSPEED;

	// super jump
	private final double SUPERJUMP = 8;
	private double currentSuperJumpSpeed = SUPERJUMP;

	// falling
	protected final double MAXFALLSPEED = 5;
	protected double currentFallSpeed = 0.1;

	// World

	protected World world;
	private boolean respawned = false;

	public AbstractCharacter(String name, int life, int damage, World w) {
		this.name = name;
		this.damage = damage;
		this.life = life;
		this.world = w;

	}

	@Override
	public float getVelocityX() {
		return VELOCITY_X;
	}

	@Override
	public void setX(double x) {
		position.setX(x);

	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public double getX() {
		return position.getX();
	}

	@Override
	public double getY() {
		return position.getY();
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void hurt() {
		life--;
	}

	@Override
	public boolean isDead() {
		if (life == 0)
			return true;
		return false;
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

	@Override
	public void jump() {
		if (canJump) {
			jumping = true;
			falling = false;
			currentFallSpeed = 0.1;
			grounded = false;
		}
		canJump = false;
	}

	@Override
	public void doubleJump() {
		if (canDoubleJump) {
			if (jumping || falling) {
				currentJumpSpeed = JUMPSPEED;
				canDoubleJump = false;
				jumping = false;
				falling = false;
				doubleJump = true;
			}
		}

	}

	@Override
	public void superJump() {
		superJumping = true;
		falling = false;
		d[0] = 0;
		d[1] = 0;
	}

	@Override
	public void shoot() {

		if (canShoot) {

			if (oldDirection == Direction.LEFT) {
				bullets.add(new Bullet(this.getX() - bulletDifferenceSx, this.getY() + this.getHeight() / 2,
						oldDirection, world, false));
			} else if (oldDirection == Direction.RIGHT) {
				bullets.add(new Bullet(this.getX() + this.getWidth() + bulletDifferenceDx,
						this.getY() + this.getHeight() / 2, oldDirection, world, false));
			}
			shoot = true;

			canShoot = false;
			lastMillis = System.currentTimeMillis();

		}
	}

	@Override
	public void hit() {
	}

	@Override
	public void searchPlayer(Player p) {
		if (p.getY() > this.getY() && p.getY() < this.getY() + this.getHeight()
				&& world.checkVisibility(this, this.getX(), this.getY(), p.getX(), p.getY()) && !this.collide(p)) {
			if (p.getX() > this.getX()) {
				directionNum = 1;
				setDirection(Direction.RIGHT);
			} else {
				directionNum = -1;
				setDirection(Direction.LEFT);
			}
		} else {
			setDirection(Direction.STOP);
		}
	}

	@Override
	public void setSuperJumpDirection(double x, double y) {
		d[0] = x;
		d[1] = y;
	}

	@Override
	public void update() {

		double X = getX();
		double Y = getY();

		for (Item gem : world.getGems()) {
			if (gem.collide(X, Y, WIDTH, HEIGHT) && !gem.isCollected()) {
				gem.collect();
				collectGem();
			}
		}
		if (blockMoving) {
			X = getX() + delta;
			blockMoving = false;
		}

		switch (direction) {
		case RIGHT:
			if (!world.checkPlayerCollision(this, getX() + VELOCITY_X, getY())) {
				X = position.getX() + VELOCITY_X;
				oldDirection = Direction.RIGHT;
			}

			vx = VELOCITY_X;
			break;

		case LEFT:
			if (!world.checkPlayerCollision(this, getX() - VELOCITY_X, getY())) {
				X = position.getX() - VELOCITY_X;
				oldDirection = Direction.LEFT;
			}
			vx = -VELOCITY_X;
			break;

		case STOP:
			break;
		default:
			break;
		}

		if (jumping) {
			if (!world.checkPlayerCollision(this, getX(), getY() - currentJumpSpeed)) {

				Y = position.getY() - currentJumpSpeed;
				currentJumpSpeed -= 0.1;
				if (currentJumpSpeed < 1) {
					currentJumpSpeed = JUMPSPEED;
					jumping = false;
					falling = true;
				}
			} else {
				currentJumpSpeed = JUMPSPEED;
				vx = 0;
				jumping = false;
				falling = true;
				grounded = false;
			}

		}

		if (doubleJump) {
			if (!world.checkPlayerCollision(this, getX(), getY() - currentJumpSpeed)) {
				Y = position.getY() - currentJumpSpeed;
				currentJumpSpeed -= 0.1;
				if (currentJumpSpeed < 1) {
					currentJumpSpeed = JUMPSPEED;
					currentFallSpeed = 0.1;
					doubleJump = false;
					falling = true;
				}
			} else {
				currentJumpSpeed = JUMPSPEED;
				currentFallSpeed = 0.1;
				vx = 0;
				doubleJump = false;
				falling = true;
				grounded = false;
			}

		}

		if (superJumping) {

			if (!world.checkPlayerCollision(this, getX() + currentJumpSpeed * d[0],
					getY() + currentSuperJumpSpeed * d[1])) {
				Y = position.getY() + (currentSuperJumpSpeed * d[1]);
				falling = false;
				currentSuperJumpSpeed -= 0.1;
				if (currentSuperJumpSpeed < 1) {
					currentSuperJumpSpeed = SUPERJUMP;
					superJumping = false;
				}
			} else {
				currentSuperJumpSpeed = SUPERJUMP;
				superJumping = false;
			}

		}

		if (falling) {
			if (!world.checkPlayerCollision(this, getX(), getY() + currentFallSpeed)) {
				Y = position.getY() + currentFallSpeed;

				if (currentFallSpeed <= MAXFALLSPEED) {
					currentFallSpeed += 0.1;
				}

			} else {
				currentFallSpeed = 0.1;
				currentJumpSpeed = JUMPSPEED;
				currentSuperJumpSpeed = SUPERJUMP;
				vx = 0;
				doubleJump = false;
				canJump = true;
				superJumping = false;
				canDoubleJump = true;
				canSuperJump = true;
				falling = false;
				grounded = true;
			}
		}
		if (!world.checkPlayerCollision(this, getX(), getY() + currentFallSpeed)) {
			if (!jumping && !doubleJump && !superJumping) {
				falling = true;
			}
		}

		if (!world.checkPlayerCollision(this, X, Y)) {
			position.setX(X);
			position.setY(Y);
		}
	}

	@Override
	public boolean isJumping() {
		return jumping;
	}

	@Override
	public boolean isFalling() {
		return falling;
	}

	@Override
	public boolean canJump() {
		return canJump;
	}

	@Override
	public boolean canDoubleJump() {
		return canDoubleJump;
	}

	@Override
	public boolean canSuperJump() {
		return canSuperJump;
	}

	@Override
	public void collectGem() {
	}

	@Override
	public int getCollectedGems() {
		return gems;
	}

	@Override
	public void kill() {
		attacking = false;
		jumping = false;
		doubleJump = false;
		life = 0;
	}

	@Override
	public void setPosition(Position p) {
		double x = p.getX();
		double y = p.getY();

		this.position = new Position(x, y);
		if (lastSpawnPoint == null) {
			lastSpawnPoint = new Position(world.getMap().getSpawnPoint().getX(), world.getMap().getSpawnPoint().getY());
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isDoubleJumping() {
		return doubleJump;
	}

	@Override
	public boolean isSuperJumping() {
		return superJumping;
	}

	@Override
	public void setLastSpawnPoint(double x, double y) {
		lastSpawnPoint.setX(x);
		lastSpawnPoint.setY(y);
	}

	@Override
	public void respawn() {
		respawned = true;
		setPosition(new Position(lastSpawnPoint.getX(), lastSpawnPoint.getY()));
		life = 1;
	}

	@Override
	public void moveCharacter(double delta) {
		blockMoving = true;
		this.delta = delta;
	}

	@Override
	public void setWidth(int w) {
		WIDTH = w;
	}

	@Override
	public void setHeight(int h) {
		HEIGHT = h;
	}

	@Override
	public boolean collide(Character c) {
		return true;
	}

	@Override
	public boolean isAttacking() {
		return attacking;
	}

	@Override
	public List<Bullet> getBullet() {
		return bullets;
	}

	@Override
	public boolean isShooting() {
		return shoot;
	}

	@Override
	public void noShooting() {
		shoot = false;
	}

	@Override
	public boolean isRespawned() {
		return respawned;
	}

	@Override
	public void notRespawned() {
		respawned = false;
	}

	@Override
	public long getLast() {
		return lastMillis;
	}

	@Override
	public boolean getCanShoot() {
		return canShoot;
	}

	@Override
	public void restartCanShoot() {
		canShoot = true;
	}

}
