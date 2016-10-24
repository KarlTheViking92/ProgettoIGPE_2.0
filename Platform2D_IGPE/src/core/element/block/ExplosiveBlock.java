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

	public ExplosiveBlock(World w,Position position) {
		super(w,position, 14);
	}

	public boolean inTheBlock() {
		if ((c.getY() + c.getHeight() >= this.getY() && c.getY() + c.getHeight() <= Y)
				|| (c.getY() > this.getY() && c.getY() < Y)) {
			// System.out.println("SONO DENTRO L'IF FIGLIO DI PUTTANA");
			return true;
		}
		// System.out.println("FALSO AHHHH");
		return false;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (explosion)
			return false;

		boolean flag = super.collision(x, y, height, width);
		if (flag && !beginExplosion) {
//			System.out.println("BOOOOOM");
			beginExplosion = true;
			lastMillis = System.currentTimeMillis();
		}
		return flag;

	}

	@Override
	public void update() {

		// System.out.println(c.getX()+" "+c.getY());

		if (beginExplosion) {
			long current = System.currentTimeMillis();
			if (!explosion && current - lastMillis >= 1000) {
//				System.out.println("if 1        X: " + this.getX() + "   Y: " + this.getY());
				explosion = true;
				animated = true;
			}
			if (!inTheBlock() && explosion && current - lastMillis >= 3000) {
//				System.out.println("if 2        X: " + this.getX() + "   Y: " + this.getY());
//				System.out.println("rigenerato");
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
