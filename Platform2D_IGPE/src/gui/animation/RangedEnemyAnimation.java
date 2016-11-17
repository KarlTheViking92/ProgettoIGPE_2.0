package gui.animation;

import javafx.scene.image.Image;

public class RangedEnemyAnimation implements CharacterAnimation {

	private final static String PATH_FRAME = "file:resources/images/animation/RangedMonster";

	private final static Image[] ATTACK_ANIMATIONS = { new Image(PATH_FRAME + "/Attack/1.png"),
			new Image(PATH_FRAME + "/Attack/2.png"), new Image(PATH_FRAME + "/Attack/3.png"),
			new Image(PATH_FRAME + "/Attack/4.png"), new Image(PATH_FRAME + "/Attack/5.png"),
			new Image(PATH_FRAME + "/Attack/6.png"), new Image(PATH_FRAME + "/Attack/6.png"),
			new Image(PATH_FRAME + "/Attack/7.png"), new Image(PATH_FRAME + "/Attack/8.png")

	};
	private final static Image[] STATIC_ANIMATIONS = { new Image(PATH_FRAME + "/Static/frame1.png"),
			new Image(PATH_FRAME + "/Static/frame2.png") };

	private final static Image[] DIE_ANIMATIONS = { new Image(PATH_FRAME + "/Die/frame1.png"),
			new Image(PATH_FRAME + "/Die/frame1.png") };

	private SpriteAnimation attackAnimation;
	private SpriteAnimation deadAnimation;
	private SpriteAnimation statickAnimation;

	public RangedEnemyAnimation() {
		attackAnimation = new SpriteAnimation(ATTACK_ANIMATIONS, 150);
		deadAnimation = new SpriteAnimation(DIE_ANIMATIONS, 300);
		statickAnimation = new SpriteAnimation(STATIC_ANIMATIONS, 200);
	}

	@Override
	public Image getCharacterMoveAnimation() {
		return null;
	}

	@Override
	public Image getCharacterIdleAnimation() {
		return statickAnimation.nextFrame();
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
	public Image getCharacterAttackAnimation() {
		return attackAnimation.nextFrame();
	}

	@Override
	public Image getCharacterDieAnimation() {
		return deadAnimation.nextFrame();
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
	public boolean animationFinished() {
		return deadAnimation.isFinished();
	}

}
