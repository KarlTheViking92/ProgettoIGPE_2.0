package gui.element;

import core.element.block.Block;
import gui.ImageProvider;
import javafx.scene.transform.Rotate;

public class MoveableBlockGraphic extends AbstractGraphicBlock {

	protected int oldDirection;
	protected Rotate rotation = new Rotate();

	public MoveableBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("MoveableBlock"));
		oldDirection = 1;
		this.getTransforms().add(rotation);
		rotation.setAxis(Rotate.Y_AXIS);
	}

	@Override
	public void update() {

		rotation.setPivotX(this.getX() + (logicBlock.getWIDTH() / 2));

		if (oldDirection == 1 && logicBlock.getMovingDirection() == -1) {
			rotation.setAngle(180);
			oldDirection = logicBlock.getMovingDirection();
		}

		if (oldDirection == -1 && logicBlock.getMovingDirection() == 1) {
			rotation.setAngle(0);
			oldDirection = logicBlock.getMovingDirection();
		}

		this.relocate(logicBlock.getX() - 7.5, logicBlock.getY() - 7.5);
	}

}
