package core.element.block;

import core.element.Position;

public class CloudBlock extends AbstractBlock {

	public CloudBlock(Position position) {
		super(position);
	}

	@Override
	protected boolean collide(double x, double y, int height, int width) {
		double range = Math.abs(getY() - (y + height));
		// System.out.println("getX(): "+getX()+" <= "+(x+width)+" &&
		// "+(getX()+getWIDTH())+" >= "+x+" && "+(int)getY()+" ==
		// "+(int)(y+height));
//		System.out.println(
//				"differenza ordinate:" + range + "         cubo nuvola: " + getY() + " giocatore" + (y + height));
		if (getX() <= x + width && getX() + getWIDTH() >= x && range >= 0 && range <= 2) {
			System.out.println("Ci sono");

			return true;
		}
		return false;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {

		return collide(x, y, height, width);
	}

}
