package gui.panel;

import javafx.scene.Node;

public interface CustomComponent extends UpdatablePane {

	public abstract double getComponentWidth();

	public abstract double getComponentHeight();

	public abstract void setComponentX(double x);

	public abstract void setComponentY(double y);

	public abstract void setComponentBackground(String image);

	public abstract void addItem(Node n);

	public abstract void reset();
}
