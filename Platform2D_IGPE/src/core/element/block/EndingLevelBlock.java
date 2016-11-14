package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;
import core.gameManagers.PlayManager;

public class EndingLevelBlock extends AbstractBlock {

	public EndingLevelBlock(World w, Position position) {
		super(w, position, 8);
	}

	@Override
	protected boolean collide(double x, double y, int height, int width) {
		if (super.collide(x, y, height, width)) {

			double Y = y + height / 2;
			double X = x + width / 2;
			if (X >= getX() && X <= getX() + WIDTH && y < getY()) {
				PlayManager.getInstance().finishLevel();
			}
		}
		return super.collide(x, y, height, width);

	}

	@Override
	public void setPlayerState(Character c) {

	}
}
