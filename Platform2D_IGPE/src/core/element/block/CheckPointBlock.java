package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;

public class CheckPointBlock extends AbstractBlock {

	public CheckPointBlock(World w, Position position) {
		super(w, position, 8);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width)) {
			if (x > getX() && x + width < getX() + WIDTH)
				collided = true;
		}

		return super.collision(x, y, height, width);
	}

	@Override
	public void setPlayerState(Character c) {
		c.setLastSpawnPoint(this.getX() + c.getWidth() / 2, this.getY() - this.getHEIGHT());

	}

	@Override
	public void update() {
		if (collided) {
			animated = true;
		} else {
			animated = false;
		}
	}
}
