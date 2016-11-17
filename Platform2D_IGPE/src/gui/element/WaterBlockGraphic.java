package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class WaterBlockGraphic extends AbstractGraphicBlock {

	public WaterBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("WaterBlock"));
	}
}
