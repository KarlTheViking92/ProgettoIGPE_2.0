package gui.panel;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public interface CustomComponent extends UpdatablePane {

	public Font TITLE_FONT = Font.loadFont("file:resources/font/Engcomica.otf", Screen.getPrimary().getBounds().getWidth()*0.05);
	public Font INFO_FONT = Font.loadFont("file:resources/font/Engcomica.otf", Screen.getPrimary().getBounds().getWidth()*0.03);
	
	
	public abstract double getComponentWidth();

	public abstract double getComponentHeight();

	public abstract void setComponentX(double x);

	public abstract void setComponentY(double y);

	public abstract void setComponentBackground(String image);

	public abstract void addItem(Node n);

	public abstract void addAll(Node...nodes);
	
	public abstract void reset();
	
	public abstract void resetChoice();
}
