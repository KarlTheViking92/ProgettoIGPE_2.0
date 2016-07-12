package gui;

import core.element.block.Block;
import javafx.scene.image.ImageView;


public interface GraphicBlock {

	public abstract void update(); //aggiorna il blocco

	public abstract void animate(); // anima facoltativamente il blocco

	public abstract void draw();
}
