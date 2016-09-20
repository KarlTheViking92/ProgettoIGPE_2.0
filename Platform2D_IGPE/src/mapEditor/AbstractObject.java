package mapEditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AbstractObject extends ImageView {
	private int code;
//	private Image image;
	private String color;

	public AbstractObject(int code, Image image, String color) {
		this.code = code;
//		this.image = image;
		this.setImage(image);
		this.color = color;
		this.setImage(image);
		this.setFitHeight(65);
		this.setFitWidth(65);
	}

	public AbstractObject(int code, Image image) {
		this.code = code;
		this.setImage(image);
		this.setImage(image);
		this.setFitHeight(65);
		this.setFitWidth(65);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
