package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class GhostBlockGraphic extends AbstractGraphicBlock {
	
	private double opacity = 1;
	
	public GhostBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSimpleBlock("C4", "rock"));
	}

	@Override
	public void update() {
		if (logicBlock.isPlayerInsideBlock()) {
			if(opacity > 0)opacity -= 0.03;
		}else{
			if(opacity < 1)opacity += 0.03;
		}
		this.setOpacity(opacity);
	}
}
