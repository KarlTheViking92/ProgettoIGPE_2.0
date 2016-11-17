package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;
import gui.animation.CharacterAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class CharacterDrawer extends ImageView {

	private Character character;
	private CharacterAnimation animation;
	private Direction oldDirection;
	private Rotate rotation = new Rotate();

	public CharacterDrawer(Character c) {
		character = c;
		animation = loadAnimation(c);
		oldDirection = Direction.RIGHT;
		this.getTransforms().add(rotation);
		rotation.setAxis(Rotate.Y_AXIS);
		this.setFitWidth(character.getWidth());
		this.setFitHeight(character.getHeight());
	}

	private CharacterAnimation loadAnimation(Character c) {
		String name = "gui.animation." + c.getName() + "Animation";

		Class animation;
		try {

			System.out.println(name);
			animation = Class.forName(name);
			return (CharacterAnimation) animation.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void draw() {
		this.setLayoutX(character.getX());
		this.setLayoutY(character.getY());

		rotation.setPivotX(this.getX() + (character.getWidth() / 2));

		if (oldDirection == Direction.RIGHT && character.getDirection() == Direction.LEFT) {
			rotation.setAngle(180);
			oldDirection = character.getDirection();
		}

		if (oldDirection == Direction.LEFT && character.getDirection() == Direction.RIGHT) {
			rotation.setAngle(0);
			oldDirection = character.getDirection();
		}
		if (character.isJumping() || character.isSuperJumping()) {
			System.out.println("jump!!!");
			this.setImage(animation.getCharacterJumpAnimation());
		} else if (character.isFalling()) {
			this.setImage(animation.getCharacterFallAnimation());
		} else if (character.getDirection() == Direction.STOP)
			this.setImage(animation.getCharacterIdleAnimation());
		else
			this.setImage(animation.getCharacterMoveAnimation());

	}
}
