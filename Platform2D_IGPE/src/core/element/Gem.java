package core.element;

public class Gem extends AbstractItem {

	public Gem(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	@Override
	public boolean collide(double x, double y, double width, double height) {
//		System.out.println("controllo player con " + x + " " + y + " " + width + " " + height);
//		System.out.println("controllo gem con " + getX() + " " + getY() + " " + getWidth() + " " + getHeight());
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
}
