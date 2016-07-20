package gui.element.block;

import core.element.block.Block;
import gui.ImageProvider;

public class ExplosiveBlockGraphic extends AbstractGraphicBlock {

	public ExplosiveBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplodingBlock"));
	}

}
