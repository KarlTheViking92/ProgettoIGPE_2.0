package gui.element;

import core.element.block.Block;
import gui.ImageProvider;
import gui.animation.SpriteAnimation;
import javafx.scene.image.Image;

public class ExplosiveBlockGraphic extends AbstractGraphicBlock {
	
	private final static String ANIMATION_PATH = "file:resources/images/animation/Explosion";
	private static final double SCALEFACTOR = 4;
	
	private double originalWidth, originalHeight;
	private double originalX, originalY;
	
	private final static Image cube = ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock");
	
	private SpriteAnimation explosion;
	
	private final static Image[] EXPLOSION_FRAMES = { new Image(ANIMATION_PATH + "/1.png"),
			new Image(ANIMATION_PATH + "/2.png"), new Image(ANIMATION_PATH + "/3.png"),
			new Image(ANIMATION_PATH + "/4.png"), new Image(ANIMATION_PATH + "/5.png"),
			new Image(ANIMATION_PATH + "/6.png"), new Image(ANIMATION_PATH + "/7.png"),
			new Image(ANIMATION_PATH + "/8.png"), new Image(ANIMATION_PATH + "/9.png"),
			new Image(ANIMATION_PATH + "/10.png"), new Image(ANIMATION_PATH + "/11.png"),
			new Image(ANIMATION_PATH + "/12.png"), new Image(ANIMATION_PATH + "/13.png"),
			new Image(ANIMATION_PATH + "/14.png"),

	};

	public ExplosiveBlockGraphic(Block logic) {
		super(logic);
		originalWidth = this.getFitWidth();
		originalHeight = this.getFitHeight();
		originalX = this.getLayoutX();
		originalY = this.getLayoutY();
		this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock"));
		explosion = new SpriteAnimation(EXPLOSION_FRAMES, 150);
	}

	// settare il timer nella logica per innescare la sequenza di distruzione

	public void update() {
		
		if( logicBlock.isAnimated() && explosion.isFinished())
			this.setImage(null);

		else if (logicBlock.isAnimated() && !explosion.isFinished()) {
			this.setImage(explosion.nextFrame());
			this.setFitWidth(originalWidth * SCALEFACTOR);
			this.setFitHeight(originalWidth * SCALEFACTOR);
			this.setLayoutX(originalX - ((originalWidth * SCALEFACTOR) / 3));
			this.setLayoutY(originalY - ((originalWidth * SCALEFACTOR) / 3));
		} else {
			this.setImage(cube);
			explosion.restartAnimation();
			this.setFitWidth(originalWidth);
			this.setFitHeight(originalHeight);
			this.setLayoutX(originalX);
			this.setLayoutY(originalY);
		}
	}
}
