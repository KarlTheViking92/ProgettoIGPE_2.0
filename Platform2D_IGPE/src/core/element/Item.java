package core.element;

public interface Item {
	public double getX();
	public double getY();
	
	public double getWidth();
	public double getHeight();
	
	public boolean collide(double x, double y, double width, double height);
	public void collect();
	public boolean isCollected();
}
