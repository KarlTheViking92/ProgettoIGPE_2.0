package gui.element;

import core.element.block.Block;
import core.gameManagers.PlayManager;
import gui.ImageProvider;

public class CloudBlockGraphic extends AbstractGraphicBlock {

	public CloudBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("CloudBlock"));
	}

	@Override
	public void update() {
		if(logicBlock.isPlayerInsideBlock()){
			// set opacity di player da fare dopo
		}
	}
	
}
