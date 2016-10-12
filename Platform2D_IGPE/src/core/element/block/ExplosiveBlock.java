package core.element.block;

import core.element.Position;

public class ExplosiveBlock extends AbstractBlock {

	private boolean beginExplosion = false;
	private boolean explosion = false;
	private long lastMillis;
	private long deltaMillis;
	
	public ExplosiveBlock(Position position) {
		super(position,14);
	}


	
	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (explosion)
			return false;
		
		boolean flag = super.collision(x, y, height, width);
		if (flag && !beginExplosion) {
			System.out.println("BOOOOOM");
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
			if (!this.isPlayerInsideBlock() && explosion && current - lastMillis >= 3000 ) {
				System.out.println("rigenerato");
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
	
	public boolean isExploded (){
		return explosion;
	}
	
	
}
