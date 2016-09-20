package core.element;

public class AbstractItem implements Item {

	private double x;
	private double y;

	private double height;
	private double width;
	
	protected boolean collected = false;
	
	public AbstractItem(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public boolean isCollected() {
		return collected;
	}

	@Override
	public boolean collide(double x, double y, double width, double height) {
		return false;
	}

	@Override
	public void collect() {
		System.out.println("ho preso la gemma");
		collected = true;
	}
}
