package gui.animation;

import javafx.scene.image.Image;

public class MeleeEnemyAnimation implements CharacterAnimation {

	private final static String PATH_FRAME = "file:resources/images/animation/MeleeMonster";

	private final static Image[] RUN_FRAMES = { new Image(PATH_FRAME + "/Moving/1.png"),
			new Image(PATH_FRAME + "/Moving/2.png"), new Image(PATH_FRAME + "/Moving/3.png"),
			new Image(PATH_FRAME + "/Moving/4.png"), new Image(PATH_FRAME + "/Moving/5.png"),
			new Image(PATH_FRAME + "/Moving/6.png"), new Image(PATH_FRAME + "/Moving/6.png"),

	};

	private final static Image[] DEAD_FRAMES = { new Image(PATH_FRAME + "/Dead/frame1.png"),
			new Image(PATH_FRAME + "/Dead/frame1.png"), new Image(PATH_FRAME + "/Dead/frame1.png") };

	private final static Image[] ATTACK_FRAMES = { new Image(PATH_FRAME + "/Attack/1.png"),
			new Image(PATH_FRAME + "/Attack/2.png"), new Image(PATH_FRAME + "/Attack/3.png") };

	private final static Image[] STATIC_FRAMES = { new Image(PATH_FRAME + "/Static/frame1.png"),
			new Image(PATH_FRAME + "/Static/frame2.png"),

	};

	private SpriteAnimation runAnimation;
	private SpriteAnimation staticAnimation;
	private SpriteAnimation attackAnimation;
	private SpriteAnimation deadAnimation;

	public MeleeEnemyAnimation() {
		deadAnimation = new SpriteAnimation(DEAD_FRAMES, 200);
		runAnimation = new SpriteAnimation(RUN_FRAMES, 100);
		staticAnimation = new SpriteAnimation(STATIC_FRAMES, 300);
		attackAnimation = new SpriteAnimation(ATTACK_FRAMES, 200);
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
		return null;
	}

	@Override
	public Image getCharacterFallAnimation() {
		return null;
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
		return deadAnimation.nextFrame();
	}

	@Override
	public boolean animationFinished() {
		return deadAnimation.isFinished();
	}
}
