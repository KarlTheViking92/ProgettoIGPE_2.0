package gui.panel;

import gui.ImageProvider;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AbstractCustomComponent extends Pane implements CustomComponent{ 

	private double width, height;
	protected Rectangle background;
	
	public AbstractCustomComponent(double width, double height, double x, double y) {
		this.width = width;
		this.height = height;
		this.background = new Rectangle(width, height);
		this.setWidth(width);
		this.setHeight(height);
		this.setComponentX(x);
		this.setComponentY(y);
		
		this.getChildren().add(background);
	}

	@Override
	public void update() {}

	@Override
	public double getComponentWidth() {
		return width;
	}

	@Override
	public double getComponentHeight() {
		return height;
	}

	@Override
	public void reset() {		
	}

	@Override
	public void setComponentBackground(String image) {
		background.setFill(new ImagePattern(new Image("file:resources/images/"+image+".png")));
	}

	@Override
	public void setComponentX(double x) {
		this.setLayoutX(x);
	}

	@Override
	public void setComponentY(double y) {
		this.setLayoutY(y);
	}

	@Override
	public void addItem(Node n) {
		
	}

}
