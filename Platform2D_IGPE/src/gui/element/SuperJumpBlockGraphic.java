package gui.element;

import core.element.block.Block;
import gui.ImageProvider;
import gui.animation.SpriteAnimation;
import javafx.scene.image.Image;

public class SuperJumpBlockGraphic extends AbstractGraphicBlock {

	private final static String ANIMATION_PATH = "file:resources/images/animation/SuperJump";
	private SpriteAnimation sprint;

	private double originalWidth, originalHeight;
	private double originalX, originalY;

	private final static Image[] SPRINT_MOVING = { new Image(ANIMATION_PATH + "/1.png"),
			new Image(ANIMATION_PATH + "/2.png"), new Image(ANIMATION_PATH + "/3.png"),
			new Image(ANIMATION_PATH + "/4.png"), new Image(ANIMATION_PATH + "/5.png"),
			new Image(ANIMATION_PATH + "/6.png"), new Image(ANIMATION_PATH + "/7.png"),
			new Image(ANIMATION_PATH + "/8.png"), new Image(ANIMATION_PATH + "/9.png") };

	public SuperJumpBlockGraphic(Block logic) {
		super(logic);
		originalWidth = this.getFitWidth();
		originalHeight = this.getFitHeight();
		originalX = this.getLayoutX();
		originalY = this.getLayoutY();
		this.setImage(ImageProvider.getInstance().getSpecialBlock("SuperJumpBlock"));
		sprint = new SpriteAnimation(SPRINT_MOVING, 100);
	}

	@Override
	public void update() {

		if (sprint.isFinished())
			logicBlock.setAnimated(false);

		if (logicBlock.isAnimated()) {
			this.setImage(sprint.nextFrame());
			this.setFitHeight(originalHeight + 120);
			this.setLayoutY(originalY - 120);
		} else {
			this.setImage(ImageProvider.getInstance().getSpecialBlock("SuperJumpBlock"));
			sprint.restartAnimation();
			this.setFitWidth(originalWidth);
			this.setFitHeight(originalHeight);
			this.setLayoutX(originalX);
			this.setLayoutY(originalY);
		}
	}
}
