package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;
import gui.animation.CharacterAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class AbstractDrawer extends ImageView implements Drawer {

	protected Character character;
	protected CharacterAnimation animation;
	protected Direction oldDirection;
	protected Rotate rotation = new Rotate();
	protected boolean destroy = false;

	public AbstractDrawer(Character c) {
		character = c;
		animation = loadAnimation(c);
		oldDirection = Direction.RIGHT;
		this.getTransforms().add(rotation);
		rotation.setAxis(Rotate.Y_AXIS);
		this.setFitWidth(character.getWidth());
		this.setFitHeight(character.getHeight());
	}

	@Override
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

	}

	@Override
	public CharacterAnimation loadAnimation(Character c) {

		String name = "gui.animation." + c.getType() + "Animation";
		Class animation;
		try {

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

	@Override
	public boolean toDestroy() {
		return destroy;
	}

}
