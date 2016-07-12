package gui.element.block;

import core.element.block.Block;
import gui.GraphicBlock;
import javafx.scene.image.ImageView;

public abstract class AbstractGraphicBlock extends ImageView implements GraphicBlock {

	protected Block logicBlock;
	

	public AbstractGraphicBlock(Block b) {
		logicBlock = b;
		this.setLayoutX(b.getX() - 7.5);
		this.setLayoutY(b.getY() - 7.5);
		this.setFitHeight(65);
		this.setFitWidth(65);
	}
	
	
	
	
}
