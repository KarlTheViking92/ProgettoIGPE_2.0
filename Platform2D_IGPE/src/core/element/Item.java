package core.element;

public interface Item {
	public abstract double getX();

	public abstract double getY();

	public abstract void setX(double x);

	public abstract void setY(double y);

	public abstract double getWidth();

	public abstract double getHeight();

	public abstract boolean collide(double x, double y, double width, double height);

	public abstract void collect();

	public abstract boolean isCollected();

	public abstract boolean isCreated();

	public abstract void create();

	public abstract void update();

	public abstract void setWidth(int w);

	public abstract void setHeight(int h);

	public abstract boolean hasCollided();

	public abstract void setItemPosition(double x, double y);
	
	public abstract boolean getType();
}