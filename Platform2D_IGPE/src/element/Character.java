package element;

import logic.Position;

public abstract class Character {
	private int lives;
	private Position position;
	private int damage;
	private double height;
	private double width;
	//direction
	
	public Character(int lives,Position position,int damage,double height, double width) {
		this.setLives(lives);
		this.setPosition(position);
		this.setDamage(damage);
		this.setHeight(height);
		this.setWidth(width);
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void hurt(){
		lives--;
		setLives(lives);
	}
	
	public boolean isDead(){
		if(lives == 0)
			return true;
		return false;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

}
