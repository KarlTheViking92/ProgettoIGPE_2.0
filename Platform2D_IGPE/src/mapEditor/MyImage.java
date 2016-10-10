package mapEditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class MyImage extends ImageView {

	public MyImage(Image s) {
		this.setFitHeight(Screen.getPrimary().getBounds().getWidth()*0.08);
		this.setFitWidth(Screen.getPrimary().getBounds().getWidth()*0.08);
		this.setImage(s);
	}
	
	public void setPosition(double x, double y){
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
