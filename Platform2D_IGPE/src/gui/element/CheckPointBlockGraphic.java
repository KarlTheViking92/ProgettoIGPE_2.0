package gui.element;

import core.element.block.Block;
import gui.ImageProvider;
import gui.animation.SpriteAnimation;
import javafx.scene.image.Image;

public class CheckPointBlockGraphic extends AbstractGraphicBlock {
	private final static String ANIMATION_PATH = "file:resources/images/animation/CheckPoint";
	private SpriteAnimation moving_flag;

	private final static Image[] FLAG_MOVING = { new Image(ANIMATION_PATH + "/1.png"),
			new Image(ANIMATION_PATH + "/2.png"), new Image(ANIMATION_PATH + "/3.png"),
			new Image(ANIMATION_PATH + "/4.png"), new Image(ANIMATION_PATH + "/5.png"),
			new Image(ANIMATION_PATH + "/6.png"), new Image(ANIMATION_PATH + "/7.png"),
			new Image(ANIMATION_PATH + "/8.png"), new Image(ANIMATION_PATH + "/9.png"),
			new Image(ANIMATION_PATH + "/10.png"), new Image(ANIMATION_PATH + "/11.png"),
			new Image(ANIMATION_PATH + "/12.png")};

	public CheckPointBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("CheckPointBlock"));
		this.setFitHeight(getFitHeight() + (getFitHeight() * 1.5));
		this.setFitWidth(getFitWidth() + getFitWidth() / 6);
		this.setLayoutY(getLayoutY() - getFitHeight() / 1.7);
		moving_flag = new SpriteAnimation(FLAG_MOVING, 200);
	}

	@Override
	public void update() {
		if (logicBlock.isAnimated()){
			this.setImage(moving_flag.nextFrame());
				if(moving_flag.isFinished())
				moving_flag.setLoop(6);
		}

	}

}
