package gui.gameMenu;

import gui.ImageProvider;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;

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
		
		if(Screen.getPrimary().getBounds().getHeight() < 1080){
			this.setFitHeight(100);
			this.setFitWidth(100);
		}
			
		
		String onPath = name+"On";
		String offPath = name+"Off";
		on = new ImageView(ImageProvider.getInstance().getMenuImage(onPath));
		off = new ImageView(ImageProvider.getInstance().getMenuImage(offPath));
		this.setImage(off.getImage());
				
		this.setOnMouseEntered(e -> {
			this.setImage(on.getImage());
			GameMenu.playSound("select.mp3");
			info.setText(stringInfo);
			info.setVisible(true);
		});
		
		this.setOnMouseExited(e -> {
			this.setImage(off.getImage());
			info.setVisible(false);
		});
	}

}
