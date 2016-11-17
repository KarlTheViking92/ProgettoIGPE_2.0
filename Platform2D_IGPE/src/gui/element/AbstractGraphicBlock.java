package gui.element;

import core.element.block.Block;
import javafx.scene.image.ImageView;

public class AbstractGraphicBlock extends ImageView implements GraphicElement {

	protected Block logicBlock;
	protected boolean destroyed = false;

	public AbstractGraphicBlock(Block logic) {
		logicBlock = logic;
		this.setLayoutX(logicBlock.getX() - 7.5);
		this.setLayoutY(logicBlock.getY() - 7.5);
		this.setFitHeight(65);
		this.setFitWidth(65);
	}

	@Override
	public void update() {

	}

	@Override
	public boolean destroy() {
		return destroyed;
	}
}
