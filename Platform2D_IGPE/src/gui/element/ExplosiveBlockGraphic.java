package gui.element;

import com.sun.imageio.plugins.common.I18N;

import core.element.block.Block;
import gui.ImageProvider;
import gui.animation.SpriteAnimation;
import javafx.scene.image.Image;

public class ExplosiveBlockGraphic extends AbstractGraphicBlock {
	
	private double originalWidth, originalHeight;
	private double originalX, originalY;
	private double opacity = 1;
	private SpriteAnimation explosion;
	private final static Image[] EXPLOSION_FRAMES = {
			new Image("file:resources/images/block/SpecialCube/Explosion/1.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/2.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/3.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/4.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/5.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/6.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/7.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/8.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/9.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/10.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/11.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/12.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/13.png"),
			new Image("file:resources/images/block/SpecialCube/Explosion/14.png"),

	};
	private static final double SCALEFACTOR = 4;

	public ExplosiveBlockGraphic(Block logic) {
		super(logic);
		
		originalWidth = this.getFitWidth(); originalHeight = this.getFitHeight();
		originalX = this.getLayoutX(); originalY = this.getLayoutY();
		
		this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock"));
		explosion = new SpriteAnimation(EXPLOSION_FRAMES, 150);
	}

	// settare il timer nella logica per innescare la sequenza di distruzione

	public void update() {

		if (logicBlock.isAnimated() && !explosion.isFinished()) {
			this.setImage(explosion.nextFrame());
			System.out.println("new size is " + originalWidth*SCALEFACTOR + "  " + originalHeight*SCALEFACTOR);
			this.setFitWidth(originalWidth*SCALEFACTOR);
			this.setFitHeight(originalWidth*SCALEFACTOR);
			this.setLayoutX(originalX - ((originalWidth*SCALEFACTOR)/3));
			this.setLayoutY(originalY - ((originalWidth*SCALEFACTOR)/3));
		}

		else { 
	
			this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock"));
			explosion.restartAnimation();
			this.setFitWidth(originalWidth);
			this.setFitHeight(originalHeight);
			this.setLayoutX(originalX);
			this.setLayoutY(originalY);
		}

	}
}
