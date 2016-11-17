package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class SpawnBlockGraphic extends AbstractGraphicBlock {

	public SpawnBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("SpawnBlock"));
		this.setFitHeight(getFitHeight() + (getFitHeight() / 2));
		this.setFitWidth(getFitWidth() + getFitWidth() / 6);
		this.setLayoutY(getLayoutY() - getFitHeight() / 3);
	}

}
