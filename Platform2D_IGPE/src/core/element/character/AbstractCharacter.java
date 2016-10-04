package core.element.character;

import core.World.World;
import core.element.Item;
import core.element.Position;

public abstract class AbstractCharacter implements Character {

	private String name;
	private int life;
	private Position position;
	private int damage;
	private Direction direction = Direction.STOP;
	protected int gems = 0;

	// aggiustare width e height
	private int HEIGHT = 45;
	private int WIDTH = 30;

	private boolean shoot;

	private final float VELOCITY_X = 3.0f;
	private final float VELOCITY_Y = 4.0f;

	private float vx, vy;

	private boolean canJump = true, jumping = false, falling = true, grounded = false;
	private boolean right = false, moving = false;
	private boolean doubleJump = false, canDoubleJump = true;
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
		//		this.position = position;
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
	public void shoot() {
		shoot = true;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {

//		List<Block> blocks = world.getNearBlocks();

		double X = getX();
		double Y = getY();

		for (Item gem : world.getGems()) {
			if(gem.collide(X, Y, WIDTH, HEIGHT) && !gem.isCollected()){
				gem.collect();
			}
		}

		if (!world.checkPlayerCollision(this, getX(), getY() + currentFallSpeed)) {
			if (!jumping && !doubleJump) {
				falling = true;
			}
		}

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
				canJump = true;
				canDoubleJump = true;
				falling = false;
				grounded = true;
			}
		}
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
	public void collectGem() {}
	
	@Override
	public int getCollectedGems() {return gems;}
	
	@Override
	public void kill() {
		life = 0;
	}
	
	@Override
	public void setPosition(Position p) {
		position = p;
	}
	
	@Override
	public String getName() {
		return name;
	}
	// @Override
	// public void update() {
	//
	// List<Block> blocks = world.getNearBlocks();
	//
	// double X = getX();
	// double Y = getY();
	// // System.out.println(" JUMPING " + jumping);
	// // System.out.println(" FALLING " + falling);
	// // System.out.println("GROUNDED " + grounded);
	// // System.out.println(" ******************** ");
	// // System.out.println();
	// // System.out.println("controllo " + world.getNearBlocks().size() +"
	// // blocchi");
	//
	// if (!world.checkPlayerCollision(getX(), getY() + currentFallSpeed)) {
	// if (!jumping) {
	// falling = true;
	// }
	// }
	//
	// switch (direction) {
	// case RIGHT:
	// // if (!world.checkPlayerCollision(getX() + VELOCITY_X, getY())) {
	// X = position.getX() + VELOCITY_X;
	// // return;
	//// currentSpeed_X = VELOCITY_X;
	// // }
	//
	// break;
	//
	// case LEFT:
	// // if (!world.checkPlayerCollision(getX() - VELOCITY_X, getY())) {
	// // X = position.getX() - VELOCITY_X;
	// currentSpeed_X = -VELOCITY_X;
	// // }
	// break;
	//
	//
	// case STOP: currentSpeed_X = 0; currentSpeed_Y = 0; break;
	//
	// default:
	// break;
	// }
	//
	// if (jumping) {
	//
	// if (!world.checkPlayerCollision(getX(), getY() - currentJumpSpeed)) {
	//
	// Y = position.getY() - currentJumpSpeed;
	// currentJumpSpeed -= 0.1;
	//
	// currentSpeed_Y = -VELOCITY_Y;
	// // currentSpeed_Y += currentJumpSpeed;
	// if (currentJumpSpeed < 1) {
	// currentJumpSpeed = JUMPSPEED;
	// jumping = false;
	// falling = true;
	// }
	// } else {
	// currentJumpSpeed = JUMPSPEED;
	// jumping = false;
	// falling = true;
	// grounded = false;
	// }
	//
	// }
	// if (falling) {
	// if (!world.checkPlayerCollision(getX(), getY() + currentFallSpeed)) {
	// // System.out.println("NON COLLIDO");
	// // System.out.println("current fall speed " + currentFallSpeed);
	// Y = position.getY() + currentFallSpeed;
	// // currentSpeed_Y += 0.1;
	//
	// currentSpeed_Y = VELOCITY_Y;
	// if (currentFallSpeed <= MAXFALLSPEED) {
	// currentFallSpeed += 0.1;
	// }
	//
	// } else {
	// // System.out.println("COLLIDO");
	// currentFallSpeed = 0.1;
	// currentSpeed_Y = 0;
	// falling = false;
	// grounded = true;
	// // currentSpeed_X = 0;
	// // currentSpeed_Y = 0;
	// }
	// }
	//
	// // System.out.println("NEW X POSITION: " + X);
	// // System.out.println("NEW Y POSITION: " + Y);
	// if (!world.checkPlayerCollision(getX() + currentSpeed_X, getY() +
	// currentSpeed_Y)) {
	// position.setX(X);
	//// position.setX(getX() + currentSpeed_X);
	// position.setY(getY() + currentSpeed_Y);
}
