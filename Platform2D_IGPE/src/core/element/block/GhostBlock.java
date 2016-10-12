package core.element.block;

import core.element.Position;

public class GhostBlock extends AbstractBlock {

	public GhostBlock(Position position) {
		super(position, 10);
	}

	@Override
	protected boolean collide(double x, double y, int height, int width) {
		
		if(super.collide(x, y, height, width)){
			inside = true;
		}else
			inside = false;
		
		return false;
	}
	
	
}
