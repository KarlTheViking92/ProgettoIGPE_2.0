package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class MoveableBlockGraphic extends AbstractGraphicBlock {

	public MoveableBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSimpleBlock1("Cube2_green"));
	}
	
	@Override
	public void update() {
		this.relocate(logicBlock.getX()-7.5, logicBlock.getY()-7.5);
	}

}
