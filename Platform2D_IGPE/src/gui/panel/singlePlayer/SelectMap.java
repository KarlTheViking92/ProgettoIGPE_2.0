package gui.panel.singlePlayer;

import gui.panel.AbstractGamePage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SelectMap extends AbstractGamePage {

	private ImageView next = new ImageView();
	private ImageView back = new ImageView();

	public SelectMap() {
		this.setBackground("SelectMapMenu.png");
		this.next.setImage(new Image("file:resources/images/editor/startButton.png"));
		this.back.setImage(new Image("file:resources/images/editor/backButton.png"));

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
		
		this.getChildren().addAll(next, back);
	}

}
