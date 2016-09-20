package gui.panel.singlePlayer;

import gui.panel.UpdatablePane;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class SelectPlayer extends Pane implements UpdatablePane {

	public static final Font FONT_BIG = Font.loadFont("file:resources/font/Engcomica.otf", 55);
	public static final Font FONT_SMALL = Font.loadFont("file:resources/font/Engcomica.otf", 30);
	Rectangle2D screen = Screen.getPrimary().getBounds();
	Rectangle background = new Rectangle(screen.getWidth(), screen.getHeight());
	
	ImageView previewBackground = new ImageView();
	CustomButton leftArrow = new CustomButton(); 
	CustomButton rightArrow = new CustomButton(); 
	CustomTextField nameField = new CustomTextField();
	Text t = new Text("Choose Your Name");
	
	public SelectPlayer() {
		this.setHeight(screen.getHeight());
		this.setWidth(screen.getWidth());
		this.background.setFill(new ImagePattern(new Image("file:resources/images/SelectPlayerMenu.png")));
		this.previewBackground.setImage(new Image("file:resources/images/borderPreview.png"));
		this.leftArrow.setImage(new Image("file:resources/images/leftArrow.png"));
		this.rightArrow.setImage(new Image("file:resources/images/rightArrow.png"));
		
		initComponent();
		
		this.getChildren().addAll(background,previewBackground, leftArrow, rightArrow, nameField, t);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	private void initComponent(){
		this.previewBackground.setFitHeight(400);
		this.previewBackground.setFitWidth(400);
		this.previewBackground.setLayoutX((screen.getWidth()*0.5) - (previewBackground.getFitWidth()/2));
		this.previewBackground.setLayoutY((screen.getHeight()*0.5) - (previewBackground.getFitHeight()/2));
		this.leftArrow.setLayoutX(previewBackground.getLayoutX() - leftArrow.getFitWidth());
		this.leftArrow.setLayoutY(previewBackground.getLayoutY() + (previewBackground.getFitHeight() * 0.5) - (leftArrow.getFitHeight()/2));
		this.rightArrow.setLayoutX(previewBackground.getLayoutX() + previewBackground.getFitWidth());
		this.rightArrow.setLayoutY(leftArrow.getLayoutY());
		this.t.setFont(FONT_BIG);
		this.t.setFill(Color.web("#DC8014"));
//		this.t.setStroke(Color.);
		this.t.setLayoutX(previewBackground.getLayoutX() + (previewBackground.getFitWidth()*0.5) - (t.getLayoutBounds().getWidth()/2));
		this.t.setLayoutY(previewBackground.getLayoutY() + previewBackground.getFitHeight() + 40);
		this.nameField.setLayoutX(previewBackground.getLayoutX() + (previewBackground.getFitWidth()*0.5) - (nameField.getWidth()/2));
		this.nameField.setLayoutY(previewBackground.getLayoutY() + previewBackground.getFitHeight() + 50);
		
	}

}
