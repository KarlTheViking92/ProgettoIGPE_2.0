package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class SpawnBlockGraphic extends AbstractGraphicBlock{

	public SpawnBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("SpawnBlock"));
	}

}
