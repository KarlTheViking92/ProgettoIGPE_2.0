package gui.gameMenu;

import gui.ImageProvider;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MyMenuButton extends ImageView {

	private Text info;
	
	private ImageView on;
	private ImageView off;
	private String buttonName;
	private String buttonInfo;
	
	public MyMenuButton(String name, String stringInfo, Text i) {
		buttonName = name;
		buttonInfo = stringInfo;
		info = i;

		String onPath = name+"On";
		String offPath = name+"Off";
		on = new ImageView(ImageProvider.getInstance().getMenuImage(onPath));
		off = new ImageView(ImageProvider.getInstance().getMenuImage(offPath));
		this.setImage(off.getImage());
				
		this.setOnMouseEntered(e -> {
			this.setImage(on.getImage());
			info.setText(stringInfo);
			info.setVisible(true);
		});
		
		this.setOnMouseExited(e -> {
			this.setImage(off.getImage());
			info.setVisible(false);
		});
	}

}
