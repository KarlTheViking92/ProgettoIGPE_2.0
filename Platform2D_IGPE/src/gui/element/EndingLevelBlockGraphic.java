package gui.element;

import core.element.block.Block;
import core.element.character.Player;
import core.gameManagers.PlayManager;
import gui.ImageProvider;
import javafx.scene.image.Image;

public class EndingLevelBlockGraphic extends AbstractGraphicBlock {

	private Player c = PlayManager.getInstance().getPlayer();
	private final static String ANIMATION_PATH = "file:resources/images/animation/End";

	private final static Image[] ENDING_FRAMES = { new Image(ANIMATION_PATH + "/1.gif"),
			new Image(ANIMATION_PATH + "/2.gif"), new Image(ANIMATION_PATH + "/3.gif"),

	};

	public EndingLevelBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("EndingLevelBlock"));
		this.setFitHeight(130);
		this.setFitWidth(75);
		this.setLayoutY(getLayoutY() - 70);
	}

	@Override
	public void update() {
		if (c.getCollectedGems() == 1)
			this.setImage(ENDING_FRAMES[0]);
		if (c.getCollectedGems() == 2)
			this.setImage(ENDING_FRAMES[1]);
		if (c.getCollectedGems() >= 3)
			this.setImage(ENDING_FRAMES[2]);
	}
}
