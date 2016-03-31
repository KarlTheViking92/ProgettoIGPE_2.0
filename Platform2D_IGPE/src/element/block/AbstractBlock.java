package element.block;


import element.Position;

public class AbstractBlock implements Block {

	private final int WIDTH = 10;
	private final int HEIGHT = 10;
	private Position position;
	
	
	public AbstractBlock(Position position) {
		this.position = position;
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
	protected boolean contains(double x, double y, int height, int width) {
		
		System.out.println("1 parte "+ position.getX() + " < " +(x + (width / 2)));
		System.out.println("2 parte "+ position.getY() + "  < " + (y + (height / 2)));
		System.out.println("3 parte "+ (position.getX() + WIDTH) + " > " + x );
		System.out.println("4 parte "+ (position.getY() + HEIGHT) + " > " + y);
		
		
		if (position.getX() + WIDTH > x
				&& position.getY() + HEIGHT > y) {
			return true;
		}
		return false;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		return contains(x, y, height, width);
	}

	public static void main(String[] args) {
		StandardBlock s = new StandardBlock(new Position(0, 0));
	}
}
