package core.element.block;

import core.element.Position;
import core.element.character.Character;

public class CheckPointBlock extends AbstractBlock {

	public CheckPointBlock(Position position) {
		super(position, 8);
	}

	@Override
	public void setPlayerState(Character c) {
		if (c.getY() + c.getHeight() < this.getY()) {
			collided = true;
			c.setLastSpawnPoint(this.getX() + c.getWidth() / 2, this.getY() - this.getHEIGHT());
			// System.out.println("posizioni dal checkpoint block: " +
			// this.getX() + " " + this.getY());
		}

	}

	@Override
	public void update() {
		if (collided)
			animated = true;
		super.update();
	}
}
