package core.element.block;

public interface Block  {
	public abstract double getX();
	public abstract double getY();
	public abstract boolean collision(double x, double y,int height, int width);
	
	public abstract double getWIDTH();
	public abstract double getHEIGHT();
	public abstract void update();
	public abstract void restart();
	public abstract void setHeight(double d);
	public abstract int getCode();
	public abstract String getColor();
	public abstract boolean isPlayerInsideBlock();
}
