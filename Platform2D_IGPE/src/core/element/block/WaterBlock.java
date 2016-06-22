package core.element.block;

import core.element.Position;

public class WaterBlock extends AbstractBlock {

	public WaterBlock(Position position) {
		super(position);
	}

	@Override
	protected boolean collide(double x, double y, int height, int width) {
		
		/*if(this.getY() < y+height && this.getY()+getHEIGHT() >= y){
			System.out.println("entro in water");
			return false;
		}*/
		return false;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		return super.collision(x, y, height, width);
	}
}
