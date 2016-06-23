package core.element.block;

import core.element.Position;

public class GhostBlock extends AbstractBlock {

	public GhostBlock(Position position) {
		super(position);
	}
	
	@Override
	protected boolean collide(double x, double y, int height, int width) {
	
		return false; 
	}
}
