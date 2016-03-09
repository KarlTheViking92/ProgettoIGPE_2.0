package element.character;
import element.Position;

public abstract class AbstractCharacter implements Character {

	private int life;
	private Position position;
	private int damage;
	private Direction direction = Direction.STOP;
	private int height;
	private int width;
	
	public AbstractCharacter(Position position, int life, int damage, int height, int width){
		this.position = position;
		this.damage = damage;
		this.life = life;
		this.height = height;
		this.width = width;
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
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void jump() {
		
	}

	@Override
	public void doubleJump() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
	}

}
