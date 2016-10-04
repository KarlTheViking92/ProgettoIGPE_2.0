package gui.element;

import com.sun.imageio.plugins.common.I18N;

import core.element.block.Block;
import gui.ImageProvider;
import gui.animation.SpriteAnimation;
import javafx.scene.image.Image;

public class ExplosiveBlockGraphic extends AbstractGraphicBlock {

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

	public ExplosiveBlockGraphic(Block logic) {
		super(logic);
		this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock"));
		explosion = new SpriteAnimation(EXPLOSION_FRAMES, 150);
	}

	// settare il timer nella logica per innescare la sequenza di distruzione

	public void update() {

		if (logicBlock.isAnimated() && !explosion.isFinished()) {
			this.setImage(explosion.nextFrame());
//			 this.setFitHeight(200);
//			 this.setFitWidth(200);
//			 this.setTranslateY(-70);
//			 this.setTranslateX(-75);

		}

		else { 
	
			this.setImage(ImageProvider.getInstance().getSpecialBlock("ExplosiveBlock"));
			explosion.restartAnimation();
//			this.setFitHeight(60);
//			this.setFitWidth(60);
//			 this.setTranslateY(0);
//			 this.setTranslateX(0);
			

		}

	}
}
