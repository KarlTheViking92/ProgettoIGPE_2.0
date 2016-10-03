package gui.popup;

import gui.ImageProvider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class AbstractPopup extends Pane implements Popup{

	protected static final Font POPUP_FONT = Font.loadFont("file:resources/font/Engcomica.otf", 30);
	
	protected boolean delete = false;
	protected Rectangle background;
	
	protected double width;
	protected double height;
	
	public AbstractPopup(double width , double height) {
		this.width = width;
		this.height = height;
		this.setWidth(width);
		this.setHeight(height);
		this.background = new Rectangle(width, height);
		this.getStyleClass().add("popup");
		setBackground(ImageProvider.getInstance().getEditorImage("popupBackground"));
		this.getChildren().add(background);
	}

	@Override
	public double getX() {
		return this.getLayoutX();
	}

	@Override
	public double getY() {
		return this.getLayoutY();
	}

	@Override
	public void setPosition(double x, double y) {
		this.setLayoutX(x - (width/2));
		this.setLayoutY(y - (height/2));
	}

	@Override
	public boolean isDeleted() {
		return delete;
	}

	@Override
	public void setBackground(Image b) {
		background.setFill(new ImagePattern(b));
	}

	@Override
	public void restart() {
		delete = false;
	}

}
