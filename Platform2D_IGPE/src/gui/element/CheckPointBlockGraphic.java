package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class CheckPointBlockGraphic extends AbstractGraphicBlock {

	public CheckPointBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock(""));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		if(logicBlock.isPlayerInsideBlock()){
			//se il player si trova sullo spawn block setto una booleana a true in maniera tare che se muoio riparto da li
		}
	}

}
