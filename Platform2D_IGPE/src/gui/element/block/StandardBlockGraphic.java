package gui.element.block;

import core.element.block.Block;
import gui.ImageProvider;

public class StandardBlockGraphic extends AbstractGraphicBlock {

	
	public StandardBlockGraphic(Block logic) {
		super(logic);
				
//		System.out.println("code " + logicBlock.getClass());
//		System.out.println("code " + logicBlock.getCode());
		this.setImage(ImageProvider.getInstance().getSimpleBlock("C4", "rock"));
	}
	
}
