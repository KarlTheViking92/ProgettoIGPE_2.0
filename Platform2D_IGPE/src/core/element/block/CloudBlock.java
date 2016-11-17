package core.element.block;

import core.World.World;
import core.element.Position;
import core.gameManagers.PlayManager;

public class CloudBlock extends AbstractBlock {

	public CloudBlock(World w, Position position) {
		super(w, position, 6);
		this.setHeight(5);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collide(x, y, height, width))
			inside = true;
		else
			inside = false;
		if (PlayManager.getInstance().getPlayer().getY() + height > getY()) {
			return false;
		} else
			return super.collision(x, y, height, width);

	}
}