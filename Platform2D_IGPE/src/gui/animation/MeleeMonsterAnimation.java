package gui.animation;

import javafx.scene.image.Image;

public class MeleeMonsterAnimation implements CharacterAnimation {

	private final static String PATH_FRAME = "file:resources/images/enemies/MeleeMonster";

	private final static Image[] RUN_FRAMES = { new Image(PATH_FRAME + "/Moving/1.png"),
			new Image(PATH_FRAME + "/Moving/2.png"), new Image(PATH_FRAME + "/Moving/3.png"),
			new Image(PATH_FRAME + "/Moving/4.png"), new Image(PATH_FRAME + "/Moving/5.png"),
			new Image(PATH_FRAME + "/Moving/6.png"), new Image(PATH_FRAME + "/Moving/6.png"),

	};

	private final static Image[] STATIC_FRAMES = { new Image(PATH_FRAME + "/Static/frame1.png"),
			new Image(PATH_FRAME + "/Static/frame2.png"),

	};
	
	private SpriteAnimation runAnimation;
	private SpriteAnimation staticAnimation;

	public MeleeMonsterAnimation() {
		runAnimation = new SpriteAnimation(RUN_FRAMES, 100);
		staticAnimation = new SpriteAnimation(STATIC_FRAMES, 300);
	}

	@Override
	public Image getCharacterMoveAnimation() {
		return runAnimation.nextFrame();
	}

	@Override
	public Image getCharacterIdleAnimation() {
		return staticAnimation.nextFrame();
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
