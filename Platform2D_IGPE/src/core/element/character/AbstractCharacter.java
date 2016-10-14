package core.element.character;

import core.World.World;
import core.element.Item;
import core.element.Position;
import javafx.geometry.Point2D;
import sun.util.resources.cldr.sah.CalendarData_sah_RU;

public abstract class AbstractCharacter implements Character {

	private String name, type;
	private int life;
	private Position position;
	private Position lastCheck;
	private int damage;
	private Direction direction = Direction.STOP;
	private double[] d = new double[] { 0.0, 0.0 };
	protected int gems = 0;
	// aggiustare width e height
	private int HEIGHT = 45;
	private int WIDTH = 30;

	private boolean shoot;

	private final float VELOCITY_X = 3.0f;
	private final float VELOCITY_Y = 4.0f;

	private float vx, vy;

	private boolean canJump = true, jumping = false, superJumping = false, falling = true, grounded = false,
			provaSuperJumping = false;
	private boolean right = false, moving = false;
	private boolean doubleJump = false, canDoubleJump = true, canSuperJump = true;
	// jumping
	private final double JUMPSPEED = 4;
	private double currentJumpSpeed = JUMPSPEED;

	// falling
	private final double MAXFALLSPEED = 5;
	private double currentFallSpeed = 0.1;

	// World

	private World world;

	public AbstractCharacter(String name, int life, int damage, World w) {
		this.name = name;
		// this.position = position;
		this.damage = damage;
		this.life = life;
		world = w;

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
		if (canSuperJump) {
			currentJumpSpeed = JUMPSPEED * 1.5;
			canSuperJump = false;
			jumping = false;
			falling = false;
			provaSuperJumping = true;
		}
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

		// System.out.println("last check attuale : " + lastCheck.getX() + " " +
		// lastCheck.getY());
		switch (direction) {
		case RIGHT:
			if (!world.checkPlayerCollision(this, getX() + VELOCITY_X, getY())) {
				X = position.getX() + VELOCITY_X;
				// return;
			}

			vx = VELOCITY_X;
			// moving = true;
			break;

		case LEFT:
			if (!world.checkPlayerCollision(this, getX() - VELOCITY_X, getY())) {
				X = position.getX() - VELOCITY_X;
			}
			// moving = true;
			vx = -VELOCITY_X;
			break;

		case STOP:
			moving = false;
			break;
		default:
			break;
		}

		if (moving) {
			X += vx;
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
				superJumping = true;
				if (currentJumpSpeed < 1) {
					currentJumpSpeed = JUMPSPEED;
					currentFallSpeed = 0.1;
					doubleJump = false;
					superJumping = false;
					falling = true;
				}
			} else {
				currentJumpSpeed = JUMPSPEED;
				currentFallSpeed = 0.1;
				vx = 0;
				doubleJump = false;
				superJumping = false;
				falling = true;
				grounded = false;
			}

		}

		if (provaSuperJumping) {
			if (!world.checkPlayerCollision(this, getX() + currentJumpSpeed * d[0], getY() + currentJumpSpeed * d[1])) {
				// Y = position.getY() - currentJumpSpeed;
				Y = position.getY() + (currentJumpSpeed * d[1]);
				X = position.getX() + (currentJumpSpeed * d[0]);
				currentJumpSpeed -= 0.1;
				superJumping = true;
				if (currentJumpSpeed < 1) {
					currentJumpSpeed = JUMPSPEED;
					currentFallSpeed = 0.1;
					doubleJump = false;
					superJumping = false;
					provaSuperJumping = false;
					falling = true;
				}
			} else {
				currentJumpSpeed = JUMPSPEED;
				currentFallSpeed = 0.1;
				vx = 0;
				doubleJump = false;
				superJumping = false;
				provaSuperJumping = false;
				falling = true;
				grounded = false;
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
				vx = 0;
				doubleJump = false;
				superJumping = false;
				canJump = true;
				provaSuperJumping = false;
				canDoubleJump = true;
				canSuperJump = true;
				falling = false;
				grounded = true;
			}
		}
		if (!world.checkPlayerCollision(this, getX(), getY() + currentFallSpeed)) {
			if (!jumping && !doubleJump && !provaSuperJumping) {
				falling = true;
			}
		}

		// System.out.println(" JUMPING " + jumping);
		// System.out.println(" FALLING " + falling);

		if (!world.checkPlayerCollision(this, X, Y)) {
			position.setX(X);
			position.setY(Y);
		} else
			moving = false;
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
		System.out.println("chiamo setposition");
		this.position = p;
		if (lastCheck == null) {
			lastCheck = p;
//			lastCheck = new Position(world.getMap().getSpawnPoint().getX(), world.getMap().getSpawnPoint().getY());
			System.out.println("setto lo spawnpoint nel personaggio");
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isSuperJumping() {
		return superJumping;
	}

	@Override
	public void setLastSpawnPoint(double x, double y) {
//		lastCheck = new Position(x, y);
		lastCheck.setX(x);
		lastCheck.setX(y);
		// System.out.println("le posizioni dell'ultimo spawn point sono: " +
		// lastCheck.getX() + " " + lastCheck.getY());

	}

	@Override
	public void respawn() {
		setPosition(lastCheck);
		// System.out.println("sto respawnando in: " + lastCheck);
		life = 1;
	}
}
