package mapEditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyImage extends ImageView {

	public MyImage(Image s) {
		this.setFitHeight(100);
		this.setFitWidth(100);
		this.setImage(s);
	}
	
	public void setPosition(double x, double y){
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
