package core.element.block;

import core.element.Position;

public class AbstractBlock implements Block {

	protected double WIDTH = 50;
	protected double HEIGHT = 50;

	private int code;
	private String color;
	protected boolean inside = false;
	@Override
	public int getCode() {
		return code;
	}

	public double getWIDTH() {
		return WIDTH;
	}

	public double getHEIGHT() {
		return HEIGHT;
	}

	// TODO posizione da fare intera probabilmente
	protected Position position;

	public AbstractBlock(Position position, int code) {
		this.position = position;
		this.code = code;
	}

	@Override
	public double getX() {
		return position.getX();
	}

	@Override
	public double getY() {
		return position.getY();
	}

	// il punto in esame appartiene al blocco?
	protected boolean collide(double x, double y, int height, int width) {
		if ((this.position.getX() + WIDTH) < x)
			return false;
		if (this.position.getX() > (x + width))
			return false;
		if ((this.position.getY() + HEIGHT) < y)
			return false;
		if (this.position.getY() > (y + height))
			return false;

		/*
		 * System.out.println("if 1 controllo  " + (this.position.getX() +
		 * WIDTH) + " < " + x); System.out.println("if 2 controllo  " +
		 * (this.position.getX()) + " > " + (x + width)); System.out.println(
		 * "if 3 controllo  " + (this.position.getY() + HEIGHT) + " < " + (y));
		 * System.out.println("if 4 controllo  " + (this.position.getY()) +
		 * " > " + (y + width));
		 */
		return true;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		return collide(x, y, height, width);
	}

	@Override
	public void setHeight(double d) {
		this.HEIGHT = d;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean isPlayerInsideBlock() {
		return inside;
	}

}
