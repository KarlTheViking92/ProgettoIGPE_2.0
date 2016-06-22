package core.element.block;

import core.element.Position;

public class WaterBlock extends AbstractBlock {

	private double Y;
	
	public WaterBlock(Position position) {
		super(position);
		this.setHeight(10);
		Y =getY()+40;
	}

	@Override
	protected boolean collide(double x, double y, int height, int width) {
		
		/*if(this.getY() < y+height && this.getY()+getHEIGHT() >= y){
			System.out.println("entro in water");
			return false;
		}*/
		if ((this.getX() + getWIDTH()) < x)
			return false;
		if (this.getX() > (x + width))
			return false;
		if ((Y + getHEIGHT()) < y)
			return false;
		if (Y > (y + height))
			return false;
		return true;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		return super.collision(x, y, height, width);
	}
}
