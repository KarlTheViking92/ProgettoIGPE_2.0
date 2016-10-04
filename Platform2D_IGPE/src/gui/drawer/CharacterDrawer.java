package gui.drawer;

import core.element.Position;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Direction;
import gui.animation.CharacterAnimation;
import javafx.geometry.Point3D;
import javafx.scene.chart.Axis;
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
		this.setFitWidth(45);
		this.setFitHeight(50);
		// TODO Auto-generated constructor stub
	}

	private CharacterAnimation loadAnimation(Character c) {
		String name ="gui.animation."+c.getName()+"Animation";
		
		Class animation;
		try {
			
			System.out.println(name);
			animation = Class.forName(name);
			return (CharacterAnimation) animation.newInstance();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void draw(){
		this.setLayoutX(character.getX());
		this.setLayoutY(character.getY());
		
		rotation.setPivotX(this.getX()+(character.getWidth()/2));
		System.out.println(oldDirection + "  " + character.getDirection());
		
		if(oldDirection == Direction.RIGHT && character.getDirection() == Direction.LEFT){
			rotation.setAngle(180);
			oldDirection = character.getDirection();
		}
		
		
		if(oldDirection == Direction.LEFT && character.getDirection() == Direction.RIGHT){
			rotation.setAngle(0);
			oldDirection = character.getDirection();
		}
		if (character.isJumping())
			this.setImage(animation.getCharacterJumpAnimation());    

		
		if(character.getDirection() == Direction.STOP)
			this.setImage(animation.getCharacterIdleAnimation());
		else
			this.setImage(animation.getCharacterMoveAnimation());
		
		
		
		
			 
	}
}
