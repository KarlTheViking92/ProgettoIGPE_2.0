package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class TrapBlockGraphic extends AbstractGraphicBlock {

	public TrapBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock(""));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {
		if(logicBlock.isPlayerInsideBlock()){}
			//se il player si trova sul trap block perde una vita minchiu		}
	}

}
