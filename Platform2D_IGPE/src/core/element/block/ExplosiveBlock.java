package core.element.block;

import core.element.Position;

public class ExplosiveBlock extends AbstractBlock {

	private boolean beginExplosion = false;
	private boolean explosion = false;
	private long lastTime;

	public ExplosiveBlock(Position position) {
		super(position,14);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (explosion && beginExplosion)
			return false;
		
		boolean flag = super.collision(x, y, height, width);
		if (flag) {
			beginExplosion = true;
			lastTime = System.currentTimeMillis();
		}
		return flag;

	}

	@Override
	public void update() {
		if (beginExplosion) {
			long current = System.currentTimeMillis();
			if (lastTime - current > 1000) {
				explosion = true;
			}
		}
	}
	
	@Override
	public void restart() {
		beginExplosion = false;
		explosion = false;
		
	}
	
	public boolean isExploded (){
		return explosion;
	}
}
