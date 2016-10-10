package mapEditor;

import gui.ImageProvider;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class ImageContainer extends Group {
	private MyImage preview;
	private MyImage increase;
	private MyImage decrease;
	private Pane numberContainer = new Pane();
	private Text number;
	private String name;
	private int size;
	private ImageProvider provider = ImageProvider.getInstance();
	private final double SPACING = 60;
	private final Font FONT = Font.loadFont("file:resources/font/Engcomica.otf", 200);

	public ImageContainer(String name, int size, double x, double y) {
		this.name = name;
		this.size = size;
		this.number = new Text();
		this.number.setFont(FONT);
		this.number.setTextOrigin(VPos.TOP);
		this.number.setFill(Color.web("#E9E484"));
		this.number.setStroke(Color.web("#E98A16"));
		this.number.setStrokeWidth(2);
//		this.number.setBoundsType(TextBoundsType.VISUAL);
		this.preview = new MyImage(provider.getEditorImage("world" + name));
		this.increase = new MyImage(provider.getEditorImage("increase"));
		this.decrease = new MyImage(provider.getEditorImage("decrease"));
		this.number.setText(Integer.toString(size));
		this.setPosition(x, y);
		this.increase.setOnMousePressed(e -> {
			increase.setEffect(new Glow(0.8));
			if (this.size < 40) {
				this.size++;
				number.setText(Integer.toString(this.size));
				System.out.println("size " + this.size);
			}
		});
		this.increase.setOnMouseReleased(e -> {
			increase.setEffect(null);
		});

		this.decrease.setOnMousePressed(e -> {
			decrease.setEffect(new Glow(0.8));
			if (this.size > 15) {
				this.size--;
				number.setText(Integer.toString(this.size));
			}
		});
		this.decrease.setOnMouseReleased(e -> {
			decrease.setEffect(null);
		});
		this.numberContainer.getChildren().add(number);
		this.getChildren().addAll(preview, decrease, numberContainer, increase);
	}

	private void setPosition(double x, double y) {
		preview.setPosition(x, y);
		decrease.setPosition(preview.getLayoutX() + preview.getFitWidth() + SPACING, preview.getLayoutY());
//		number.setLayoutX(decrease.getLayoutX() + decrease.getFitWidth() + SPACING);
//		number.setLayoutY(preview.getLayoutY() + number.getBoundsInLocal().getHeight()/2);
		numberContainer.setPrefWidth(preview.getFitWidth());
		numberContainer.setPrefHeight(preview.getFitHeight());
		numberContainer.setLayoutX(decrease.getLayoutX() + decrease.getFitWidth() + SPACING);
		numberContainer.setLayoutY(preview.getLayoutY());
		number.setLayoutX(numberContainer.getPrefWidth()*0.5 - number.getBoundsInLocal().getWidth()/2);
		number.setLayoutY(numberContainer.getPrefHeight()*0.5 - number.getBoundsInLocal().getHeight()/2);
		increase.setPosition(numberContainer.getLayoutX() + numberContainer.getPrefWidth() + SPACING, preview.getLayoutY());
	}
	
	public int getSize(){
		return size;
	}

	public void update() {

	}
}
