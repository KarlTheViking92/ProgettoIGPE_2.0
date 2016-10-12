package core.element.block;

import core.element.Position;
import core.element.character.Character;

public class SuperJumpBlock extends AbstractBlock {

	public SuperJumpBlock(Position position) {
		super(position, 12);
	}

	@Override
	public void setPlayerState(Character c) {
//		c.superJump();
		collided = true;
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

	}
}
