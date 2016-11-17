package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class StandardBlockGraphic extends AbstractGraphicBlock {

	public StandardBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSimpleBlock1(logic.getColor()));
	}

}
