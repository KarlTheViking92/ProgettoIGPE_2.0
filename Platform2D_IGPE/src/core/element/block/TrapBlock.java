package core.element.block;

import core.element.Position;
import core.element.character.Character;

public class TrapBlock extends AbstractBlock {

	public TrapBlock(Position position) {
		super(position, 13);
	}

	@Override
	public void setPlayerState(Character c) {
		if (c.getY() + c.getHeight() < this.getY()) {
			collided = true;
			System.out.println("collide______: " + collided);
			 c.kill();
		}
	}

	@Override
	public void update() {
		if (collided) {
			animated = true;
			collided = false;
		} else {
			animated = false;
			collided = false;
		}
		super.update();

	}
}