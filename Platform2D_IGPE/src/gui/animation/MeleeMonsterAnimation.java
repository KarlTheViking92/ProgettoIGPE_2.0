package gui.animation;

import javafx.scene.image.Image;

public class MeleeMonsterAnimation implements CharacterAnimation {

	private final static String PATH_FRAME = "file:resources/images/enemies/MeleeMonster";

	private final static Image[] MOVING_FRAMES = { new Image(PATH_FRAME + "/Moving/1.png"),
			new Image(PATH_FRAME + "/Moving/2.png"), new Image(PATH_FRAME + "/Moving/3.png"),
			new Image(PATH_FRAME + "/Moving/4.png"), new Image(PATH_FRAME + "/Moving/5.png"),
			new Image(PATH_FRAME + "/Moving/6.png"), new Image(PATH_FRAME + "/Moving/6.png"),

	};

	private SpriteAnimation movingAnimation;

	public MeleeMonsterAnimation() {
		movingAnimation = new SpriteAnimation(MOVING_FRAMES, 100);
	}

	@Override
	public Image getCharacterMoveAnimation() {
		return movingAnimation.nextFrame();
	}

	@Override
	public Image getCharacterIdleAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getCharacterJumpAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getCharacterFallAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValueX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValueY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
