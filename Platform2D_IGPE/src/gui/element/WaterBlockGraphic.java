package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class WaterBlockGraphic extends AbstractGraphicBlock {

	public WaterBlockGraphic(Block logic) {
		super(logic);
		System.out.println(ImageProvider.getInstance().getSpecialBlock("WaterBlock"));
		this.setImage(ImageProvider.getInstance().getSpecialBlock("WaterBlock"));
	}

}
