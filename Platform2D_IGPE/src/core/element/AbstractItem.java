package core.element;

public class AbstractItem implements Item {

	private double x;
	private double y;

	protected int height;
	protected int width;

	protected boolean collided = false;
	protected boolean collected = false;
	protected boolean created = false;

	public AbstractItem(double x, double y, int width, int height) {
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
		if ((this.getX() + getWidth()) < x)
			return false;
		if (this.getX() > (x + width))
			return false;
		if ((this.getY() + getHeight()) < y)
			return false;
		if (this.getY() > (y + height))
			return false;

		return true;
	}

	@Override
	public void collect() {
		collected = true;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void update() {

	}

	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void create() {
		created = true;
	}

	@Override
	public void setWidth(int w) {
		width = w;
	}

	@Override
	public void setHeight(int h) {
		height = h;
	}

	@Override
	public boolean hasCollided() {
		return collided;
	}

	@Override
	public void setItemPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean getType() {
		return false;
	}
}
