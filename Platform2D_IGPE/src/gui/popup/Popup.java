package gui.popup;

import javafx.scene.image.Image;

public interface Popup {
	
	public abstract double getX();
	public abstract double getY();
	
	public abstract double getWidth();
	public abstract double getHeight();
	
	public abstract void setBackground(Image b);
	
	public abstract void restart();
	public abstract boolean isDeleted();
	
	public abstract void setPosition(double x, double y);
	
	
}
