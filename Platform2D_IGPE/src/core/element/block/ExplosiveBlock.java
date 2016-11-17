package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Player;
import core.gameManagers.PlayManager;

public class ExplosiveBlock extends AbstractBlock {

	private boolean beginExplosion = false;
	private boolean explosion = false;
	private long lastMillis;

	private Player c = PlayManager.getInstance().getPlayer();
	private double Y = this.getY() + 50;

	public ExplosiveBlock(World w, Position position) {
		super(w, position, 14);
	}

	public boolean inTheBlock() {
		if ((c.getY() + c.getHeight() >= this.getY() && c.getY() + c.getHeight() <= Y)
				|| (c.getY() > this.getY() && c.getY() < Y)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (explosion)
			return false;

		boolean flag = super.collision(x, y, height, width);
		if (flag && !beginExplosion) {
			beginExplosion = true;
			lastMillis = System.currentTimeMillis();
		}
		return flag;

	}

	@Override
	public void update() {

		if (beginExplosion) {
			long current = System.currentTimeMillis();
			if (!explosion && current - lastMillis >= 1000) {
				explosion = true;
				animated = true;
			}
			if (!inTheBlock() && explosion && current - lastMillis >= 3000) {
				explosion = false;
				beginExplosion = false;
				animated = false;

			}
		}
	}

	@Override
	public void restart() {
		beginExplosion = false;
		explosion = false;

	}

	public boolean isExploded() {
		return explosion;
	}

}
