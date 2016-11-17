package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;

public class SuperJumpBlock extends AbstractBlock {

	private double xSuperJump;
	private double ySuperJump;

	public SuperJumpBlock(World w, Position position) {
		super(w, position, 12);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width)) {
			xSuperJump = 0.0;
			ySuperJump = 0.0;

			double X = x + width / 2;
			if (X >= getX() && X <= getX() + WIDTH && y < getY()) {
				ySuperJump = -1;
				animated = true;
			}

		}

		return super.collision(x, y, height, width);
	}

	@Override
	public void setPlayerState(Character c) {
		if (animated) {
			c.superJump();
			c.setSuperJumpDirection(xSuperJump, ySuperJump);
		} else
			animated = false;
	}
}
