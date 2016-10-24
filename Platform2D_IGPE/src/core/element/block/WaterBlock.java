package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;
import core.gameManagers.PlayManager;

public class WaterBlock extends AbstractBlock {

	private double Y;

	public WaterBlock(World w,Position position) {
		super(w,position, 13);
		this.setHeight(10);
		Y = getY() + 40;
	}

	@Override
	protected boolean collide(double x, double y, int height, int width) {

		/*
		 * if(this.getY() < y+height && this.getY()+getHEIGHT() >= y){
		 * System.out.println("entro in water"); return false; }
		 */
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

	@Override
	public void setPlayerState(Character c) {
		if (c.getY() + c.getHeight() > this.getY() && c.getY() + c.getHeight() < Y)
			c.kill();
	}
}
