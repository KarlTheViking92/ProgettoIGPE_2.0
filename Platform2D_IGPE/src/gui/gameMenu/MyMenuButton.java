package gui.gameMenu;

import gui.ImageProvider;
import javafx.scene.image.ImageView;

public class MyMenuButton extends ImageView {

	ImageView on;
	ImageView off;
	String buttonName;
	
	public MyMenuButton(String name) {
		buttonName = name;
		
		String onPath = name+"On";
		String offPath = name+"Off";
		on = new ImageView(ImageProvider.getInstance().getMenuImage(onPath));
		off = new ImageView(ImageProvider.getInstance().getMenuImage(offPath));
		this.setImage(off.getImage());
		
		this.setOnMouseEntered(e -> {
			this.setImage(on.getImage());
		});
		
		this.setOnMouseExited(e -> {
			this.setImage(off.getImage());
		});
	}

}
