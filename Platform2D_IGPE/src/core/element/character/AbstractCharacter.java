package core.element.character;
import core.element.Position;

public abstract class AbstractCharacter implements Character {

	private int life;
	private Position position;
	private int damage;
	private Direction direction = Direction.STOP;
	private int height;
	private int width;
	private boolean shoot;
	private final float VELOCITY_X = 0.3f;
	private final float VELOCITY_Y = 0.5f;
	
	private boolean jumping = false, falling = false;
	
	// jumping
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;
	
	// falling
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;
	
	public AbstractCharacter(Position position, int life, int damage, int height, int width){
		this.position = position;
		this.damage = damage;
		this.life = life;
		this.height = height;
		this.width = width;
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
		return width;
	}

	@Override
	public int getHeight() {
		return height;
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
		if(life == 0)
			return true;
		return false;
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void jump() {		
		jumping = true;
	}		

	@Override
	public void doubleJump() {
		// TODO Auto-generated method stub

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
		
		float X;
		switch(direction){
		case LEFT:
			X = position.getX() - VELOCITY_X;
			position.setX(X);
			break;
		
		case RIGHT:	
			X = position.getX() + VELOCITY_X;
			position.setX(X);
			
			break;
		default:
			break;
		}
		
		// poi ti finisco
		
		/*if(jumping){       
			double Y = position.getY() + currentJumpSpeed;
			position.setY((float) Y);
			currentJumpSpeed += 0.1;
			
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		
		if(falling){
			double Y = position.getY() - currentFallSpeed;
			position.setY((float)Y);
			
		}*/
		
		
	}
}
