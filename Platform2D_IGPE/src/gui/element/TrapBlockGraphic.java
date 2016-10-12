package gui.element;

import core.element.block.Block;
import gui.ImageProvider;
import gui.animation.SpriteAnimation;
import javafx.scene.image.Image;

public class TrapBlockGraphic extends AbstractGraphicBlock {
	private final static String ANIMATION_PATH = "file:resources/images/animation/Trap";
	private SpriteAnimation spike;

	private final static Image[] SPIKE_MOVING = { new Image(ANIMATION_PATH + "/1.png"),
			new Image(ANIMATION_PATH + "/2.png"), new Image(ANIMATION_PATH + "/3.png") };

	public TrapBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("TrapBlock"));
		this.setFitHeight(getFitHeight() + (getFitHeight()));
		this.setFitWidth(getFitWidth() + getFitWidth() / 10);
		this.setLayoutY(getLayoutY() - getFitHeight() / 2);
		spike = new SpriteAnimation(SPIKE_MOVING, 10);
	}

	@Override
	public void update() {
		if (logicBlock.isAnimated()) {
			this.setImage(spike.nextFrame());
			if (spike.isFinished())
				spike.setLoop(2);
		} else {
			this.setImage(ImageProvider.getInstance().getSpecialBlock("TrapBlock"));
			spike.restartAnimation();
		}
	}
}
