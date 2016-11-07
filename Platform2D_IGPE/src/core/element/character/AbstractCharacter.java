package core.element.character;

import core.World.World;
import core.element.Item;
import core.element.Position;

public abstract class AbstractCharacter implements Character {

	private String name, type;
	private int life;
	protected Position position;
	private Position lastSpawnPoint;
	private int damage;
	private Direction direction = Direction.STOP;
	private double delta = 0;
	private double[] d = new double[] { 0.0, 0.0 };
	protected int gems = 0;
	// aggiustare width e height
	private int HEIGHT = 45;
	private int WIDTH = 30;

	private boolean shoot;

	private final float VELOCITY_X = 3.0f;
	private final float VELOCITY_Y = 4.0f;

	private float vx, vy;

	private boolean canJump = true, jumping = false, superJumping = false, falling = true, grounded = false;
	private boolean moving = false, blockMoving = false;
	private boolean doubleJump = false, canDoubleJump = true, canSuperJump = true;
	// jumping
	private final double JUMPSPEED = 4;
	private double currentJumpSpeed = JUMPSPEED;

	// super jump
	private final double SUPERJUMP = 8;
	private double currentSuperJumpSpeed = SUPERJUMP;

	// falling
	private final double MAXFALLSPEED = 5;
	private double currentFallSpeed = 0.1;

	// World

	protected World world;

	public AbstractCharacter(String name, int life, int damage, World w) {
		this.name = name;
		// this.position = position;
		this.damage = damage;
		this.life = life;
		world = w;

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

	// private boolean isPressed(KeyCode k) {
	// return keys.getOrDefault(k, false);
	// }

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
		System.out.println("metto a true");
		superJumping = true;
		falling = false;
		// currentSuperJumpSpeed = SUPERJUMP;
		d[0] = 0;
		d[1] = 0;
	}

	@Override
	public void shoot() {
		shoot = true;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setSuperJumpDirection(double x, double y) {
		d[0] = x;
		d[1] = y;
	}

	@Override
	public void update() {

		// List<Block> blocks = world.getNearBlocks();
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

		// System.out.println("last check attuale : " + lastCheck.getX() + " " +
		// lastCheck.getY());
		switch (direction) {
		case RIGHT:
			if (!world.checkPlayerCollision(this, getX() + VELOCITY_X, getY())) {
				X = position.getX() + VELOCITY_X;
			}

			vx = VELOCITY_X;
			break;

		case LEFT:
			if (!world.checkPlayerCollision(this, getX() - VELOCITY_X, getY())) {
				X = position.getX() - VELOCITY_X;
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
				// superJumping = true;
				if (currentJumpSpeed < 1) {
					currentJumpSpeed = JUMPSPEED;
					currentFallSpeed = 0.1;
					doubleJump = false;
					// superJumping = false;
					falling = true;
				}
			} else {
				currentJumpSpeed = JUMPSPEED;
				currentFallSpeed = 0.1;
				vx = 0;
				doubleJump = false;
				// superJumping = false;
				falling = true;
				grounded = false;
			}

		}

		if (superJumping) {
			System.out.println((getY() + currentSuperJumpSpeed * d[1]));

			if (!world.checkPlayerCollision(this, getX() + currentJumpSpeed * d[0],
					getY() + currentSuperJumpSpeed * d[1])) {
				Y = position.getY() + (currentSuperJumpSpeed * d[1]);
				// X = position.getX() + (currentSuperJumpSpeed * d[0]);
				falling = false;
				currentSuperJumpSpeed -= 0.1;
				if (currentSuperJumpSpeed < 1) {
					currentSuperJumpSpeed = SUPERJUMP;
					superJumping = false;
				}
			} else {
				System.out.println("metto a false");
				currentSuperJumpSpeed = SUPERJUMP;
				superJumping = false;
				// falling = true;
			}

		}

		if (falling) {
			if (!world.checkPlayerCollision(this, getX(), getY() + currentFallSpeed)) {
				Y = position.getY() + currentFallSpeed;

				if (currentFallSpeed <= MAXFALLSPEED) {
					currentFallSpeed += 0.1;
				}

			} else {
				System.out.println("ehila");
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

		// System.out.println(" JUMPING " + jumping);
		// System.out.println(" FALLING " + falling);

		if (!world.checkPlayerCollision(this, X, Y)) {
			position.setX(X);
			position.setY(Y);
		}
		// else
		// moving = false;
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
		life = 0;
	}

	@Override
	public void setPosition(Position p) {
		// this.position = p;
		double x = p.getX();
		double y = p.getY();

		this.position = new Position(x, y);
		if (lastSpawnPoint == null) {
			lastSpawnPoint = new Position(world.getMap().getSpawnPoint().getX(), world.getMap().getSpawnPoint().getY());
		}
		// System.out.println("position : "+position.getX()+"
		// "+position.getY());
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
		setPosition(new Position(lastSpawnPoint.getX(), lastSpawnPoint.getY()));
		life = 1;
	}

	@Override
	public void moveCharacter(double delta) {
		blockMoving = true;
		this.delta = delta;
	}
}
