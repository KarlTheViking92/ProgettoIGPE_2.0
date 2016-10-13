package core.element.block;

import core.element.Position;
import core.element.character.Character;

public class SuperJumpBlock extends AbstractBlock {

	public SuperJumpBlock(Position position) {
		super(position, 12);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width))
			animated = true;

		return super.collision(x, y, height, width);
	}

	@Override
	public void setPlayerState(Character c) {
		 c.superJump();
	}

	@Override
	public void update() {
	/*	if (collided) {
			animated = true;
			collided = false;
		}*/

	}
}
