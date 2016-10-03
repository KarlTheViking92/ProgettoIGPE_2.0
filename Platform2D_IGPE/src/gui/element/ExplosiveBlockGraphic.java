package gui.element;

import core.element.block.Block;
import gui.ImageProvider;

public class ExplosiveBlockGraphic extends AbstractGraphicBlock {

	private double opacity = 1;
	
	public ExplosiveBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock"));
	}
	
	//settare il timer nella logica per innescare la sequenza di distruzione
	
	public void update() {
		if (logicBlock.isPlayerInsideBlock()) {
			if(opacity > 0)opacity -= 0.03;
		}else{
			if(opacity < 1)opacity += 0.03;
		}
		this.setOpacity(opacity);
	}
}
