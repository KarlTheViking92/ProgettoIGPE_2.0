package mapEditor;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class AbstractObject extends Rectangle{
	private int code;
	protected Image image;
	
	public AbstractObject(int code) {
		this.code = code;
		
		this.setWidth(70);
		this.setHeight(70);
		
		this.setFill(Color.BLACK);
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
