package gui.animation;

import javafx.scene.image.Image;

public class CarloAnimation implements CharacterAnimation {

	private final static String PATH_FRAME = "file:resources/images/character/carlo";

	private final static Image[] STATIC_FRAMES = { new Image(PATH_FRAME + "/Static/frame1.png"),
			new Image(PATH_FRAME + "/Static/frame2.png"), new Image(PATH_FRAME + "/Static/frame3.png"),
			new Image(PATH_FRAME + "/Static/frame4.png"), new Image(PATH_FRAME + "/Static/frame4.png"),
			new Image(PATH_FRAME + "/Static/frame5.png"),

	};

	private final static Image[] ATTACK_FRAMES = { new Image(PATH_FRAME + "/Attack/Ranged/Ranged1.png") };

	private final static Image[] RUN_FRAMES = { new Image(PATH_FRAME + "/Movement/frame1.png"),
			new Image(PATH_FRAME + "/Movement/frame2.png"), new Image(PATH_FRAME + "/Movement/frame3.png"),
			new Image(PATH_FRAME + "/Movement/frame4.png"), new Image(PATH_FRAME + "/Movement/frame4.png"),
			new Image(PATH_FRAME + "/Movement/frame5.png"), new Image(PATH_FRAME + "/Movement/frame6.png"),
			new Image(PATH_FRAME + "/Movement/frame7.png"), new Image(PATH_FRAME + "/Movement/frame8.png"),
			new Image(PATH_FRAME + "/Movement/frame9.png"), new Image(PATH_FRAME + "/Movement/frame10.png"),

	};

	private final static Image[] JUMPING_FRAMES = { new Image(PATH_FRAME + "/Jump/frame1.png") };

	private final static Image[] DIE_FRAMES = { new Image(PATH_FRAME + "/Die/1.png"),
			new Image(PATH_FRAME + "/Die/2.png"), new Image(PATH_FRAME + "/Die/3.png"),
			new Image(PATH_FRAME + "/Die/1.png"), new Image(PATH_FRAME + "/Die/2.png"),
			new Image(PATH_FRAME + "/Die/3.png") };

	private final static Image[] FALLING_FRAMES = { new Image(PATH_FRAME + "/Falling/frame1.png"),
			new Image(PATH_FRAME + "/Falling/frame2.png"), new Image(PATH_FRAME + "/Falling/frame3.png"),
			new Image(PATH_FRAME + "/Falling/frame4.png")

	};

	private SpriteAnimation staticAnimation;

	private SpriteAnimation runAnimation;
	private SpriteAnimation attackAnimation;
	private SpriteAnimation jumpAnimation;
	private SpriteAnimation fallAnimation;
	private SpriteAnimation dieAnimation;

	public CarloAnimation() {

		attackAnimation = new SpriteAnimation(ATTACK_FRAMES, 200);
		staticAnimation = new SpriteAnimation(STATIC_FRAMES, 300);
		runAnimation = new SpriteAnimation(RUN_FRAMES, 140);
		jumpAnimation = new SpriteAnimation(JUMPING_FRAMES, 100);
		fallAnimation = new SpriteAnimation(FALLING_FRAMES, 100);
		dieAnimation = new SpriteAnimation(DIE_FRAMES, 300);
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
		return jumpAnimation.nextFrame();
	}

	@Override
	public Image getCharacterFallAnimation() {
		return fallAnimation.nextFrame();
	}

	@Override
	public double getValueX() {
		return 0;
	}

	@Override
	public double getValueY() {
		return 0;
	}

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	public Image getCharacterAttackAnimation() {
		return attackAnimation.nextFrame();
	}

	@Override
	public Image getCharacterDieAnimation() {
		return dieAnimation.nextFrame();
	}

	@Override
	public boolean animationFinished() {
		return false;
	}
}
