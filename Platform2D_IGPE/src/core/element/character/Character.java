package core.element.character;

import java.util.List;

import core.element.Bullet;
import core.element.Position;

public interface Character {

	public abstract void hurt();

	public abstract boolean collide(Character c);

	public abstract boolean isDead();

	public abstract void kill();

	public abstract void setPosition(Position p);

	public abstract void moveCharacter(double spostamento);

	public abstract Direction getDirection();

	public abstract void setDirection(Direction direction);

	public abstract void setType(String type);

	public abstract String getType();

	public abstract double getX();

	public abstract boolean isAttacking();

	public abstract double getY();

	public abstract int getLife();

	public abstract void setLife(int life);

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract int getDamage();

	public abstract String getName();

	public abstract void setDamage(int damage);

	public abstract void jump();

	public abstract void doubleJump();

	public abstract void shoot();

	public abstract void hit();

	public abstract void update();

	public abstract boolean isJumping();

	public abstract boolean isDoubleJumping();

	public abstract boolean isSuperJumping();

	public abstract boolean canJump();

	public abstract boolean canDoubleJump();

	public abstract boolean isFalling();

	public abstract void collectGem();

	public abstract int getCollectedGems();

	public abstract void respawn();

	public abstract void superJump();

	public abstract void setLastSpawnPoint(double x, double y);

	public abstract void setSuperJumpDirection(double x, double y);

	public abstract boolean canSuperJump();

	public abstract float getVelocityX();

	public abstract void setX(double x);

	public abstract void searchPlayer(Player p);

	public abstract void setHeight(int h);

	public abstract void setWidth(int w);

	public abstract List<Bullet> getBullet();

	public abstract boolean isShooting();

	public abstract boolean isRespawned();

	public abstract void notRespawned();
	
	public abstract void noShooting();
	
	public abstract boolean getCanShoot();
	
	public abstract long getLast();
	
	public abstract void restartCanShoot ();

}