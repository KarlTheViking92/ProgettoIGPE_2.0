package gui.panel;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public interface GamePane extends UpdatablePane {
	public void draw();
	public Rectangle getBackgroundImage();
	public void removePanel(Node n);
	public void restart();
	public void resume();
}
