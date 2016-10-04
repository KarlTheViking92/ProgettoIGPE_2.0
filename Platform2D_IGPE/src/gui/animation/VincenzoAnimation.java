package gui.animation;

import javafx.scene.image.Image;
import core.element.character.AbstractCharacter;

public class VincenzoAnimation implements CharacterAnimation {

	private final static String PATH_FRAME = "file:resources/images/character/vincenzo";

	private final static Image[] STATIC_FRAMES = { new Image(PATH_FRAME + "/Static/frame1.png"),
			new Image(PATH_FRAME + "/Static/frame2.png"), new Image(PATH_FRAME + "/Static/frame3.png"),
			new Image(PATH_FRAME + "/Static/frame4.png"), new Image(PATH_FRAME + "/Static/frame4.png"),
			new Image(PATH_FRAME + "/Static/frame5.png"),

	};

	private final static Image[] RUN_FRAMES = { new Image(PATH_FRAME + "/Movement/frame1.png"),
			new Image(PATH_FRAME + "/Movement/frame2.png"), new Image(PATH_FRAME + "/Movement/frame3.png"),
			new Image(PATH_FRAME + "/Movement/frame4.png"), new Image(PATH_FRAME + "/Movement/frame4.png"),
			new Image(PATH_FRAME + "/Movement/frame5.png"), new Image(PATH_FRAME + "/Movement/frame6.png"),
			new Image(PATH_FRAME + "/Movement/frame7.png"), new Image(PATH_FRAME + "/Movement/frame8.png"),
			new Image(PATH_FRAME + "/Movement/frame9.png"), new Image(PATH_FRAME + "/Movement/frame10.png"),

	};

	private final static Image[] JUMPING_FRAMES = { new Image(PATH_FRAME + "/Jump/frame1.png")};
	private final static Image[] FALLING_FRAMES = { 
			new Image(PATH_FRAME + "/Falling/frame1.png"),
			new Image(PATH_FRAME + "/Falling/frame2.png"),
			new Image(PATH_FRAME + "/Falling/frame3.png"),
			new Image(PATH_FRAME + "/Falling/frame4.png"),
			
	};

	private SpriteAnimation staticAnimation;

	private SpriteAnimation runAnimation;
 
	private SpriteAnimation jumpAnimation;
	private SpriteAnimation fallAnimation;
	
	private Character character;

	private StatusOfAnimation currentStatus;

	public VincenzoAnimation() {

		staticAnimation = new SpriteAnimation(STATIC_FRAMES, 300);
		runAnimation = new SpriteAnimation(RUN_FRAMES, 140);
		jumpAnimation = new SpriteAnimation(JUMPING_FRAMES, 100);
		fallAnimation = new SpriteAnimation(FALLING_FRAMES, 100);
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
