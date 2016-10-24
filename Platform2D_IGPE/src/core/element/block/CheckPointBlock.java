package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;

public class CheckPointBlock extends AbstractBlock {

	public CheckPointBlock(World w, Position position) {
		super(w,position, 8);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		
		if(super.collision(x, y, height, width))
			collided = true;
		return super.collision(x, y, height, width);
	}
	
	@Override
	public void setPlayerState(Character c) {
//		if (c.getY() + c.getHeight() < this.getY()) {
//			collided = true;
//		}
		System.out.println("sono checkpoint block e chiamo la funzione");
		System.out.println("vorrei settare questo " + (this.getX() + c.getWidth() / 2)+" "+ (this.getY() - this.getHEIGHT()/2));
		c.setLastSpawnPoint(this.getX() + c.getWidth() / 2, this.getY() - this.getHEIGHT());

	}

	
	@Override
	public void update() {
		if (collided)
			animated = true;
		super.update();
	}
}
