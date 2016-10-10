package gui.panel.singlePlayer;

import gui.panel.AbstractGamePage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SelectMap extends AbstractGamePage {

	private ImageView next = new ImageView();
	private ImageView back = new ImageView();
//	private Text info = new Text("Select A Level");

	public SelectMap() {
//		this.setBackground("SelectMapMenu.png");
		this.next.setImage(new Image("file:resources/images/editor/Button_Next.png"));
		this.back.setImage(new Image("file:resources/images/editor/Button_Back.png"));

//		this.info.setFont(FONT_BIG);
//		this.info.setStrokeWidth(1);
//		this.info.setStroke(Color.BLACK);
//		this.info.setFill(Color.web("#DC8014"));
		this.title.setText("Select a level");
		this.title.setLayoutX(screen.getWidth()*0.5 - (title.getBoundsInLocal().getWidth()/2));
//		this.info.setLayoutY(screen.getHeight()*0.2);
		next.setOnMouseClicked(e -> {
			nextPage();
		});
		this.back.setOnMouseClicked(e -> {
			previousPage();
		});
		this.back.setLayoutX(screen.getWidth()*0.1);
		this.next.setLayoutX(screen.getWidth()*0.8);
		this.back.setLayoutY(screen.getHeight()*0.8);
		this.next.setLayoutY(screen.getHeight()*0.8);
		this.next.setFitWidth(screen.getWidth()*0.08);
		this.next.setFitHeight(screen.getWidth()*0.08);
		this.back.setFitWidth(screen.getWidth()*0.08);
		this.back.setFitHeight(screen.getWidth()*0.08);
		
		this.getChildren().addAll(next, back, title);
	}

}
