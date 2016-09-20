package mapEditor;

import java.util.List;

import gui.ImageProvider;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import mapEditor.AbstractObject;

public class ChoicePane extends GridPane {

	// private List<AbstractObject> listImageView;
	private AbstractObject choice;
	private Scene s;
	private Map mapEditor;
	private WorldSize worldSize = new WorldSize(s);
	private ScrollPane scrollPaneChoice = new ScrollPane();
	private Pane transparentPane = new Pane();

	private HBox hBoxScroll;

	private ObjectList standardBlock = new ObjectList(this);
	private ObjectList animatedBlock = new ObjectList(this);
	private ObjectList items = new ObjectList(this);
	private ObjectList enemies = new ObjectList(this);

	private VBox choices = new VBox();
	private VBox buttonContainer = new VBox();
	private ImageView save;
	private ImageView clear;
	private ImageView back;

	public ChoicePane(List<AbstractObject> listImageView, Map mapEditor, Scene s) {
		this.mapEditor = mapEditor;
		this.choice = null;
		this.s = s;
		this.save = new ImageView(new Image("file:resources/images/editor_panel_elements/saveEditor.png"));
		this.clear = new ImageView(new Image("file:resources/images/editor_panel_elements/clearEditor.png"));
		this.back = new ImageView(new Image("file:resources/images/editor_panel_elements/backButton.png"));
		this.scrollPaneChoice.getStyleClass().add("scrollpane");
		this.transparentPane.getStyleClass().add("transparent");
		this.hBoxScroll = new HBox();
		this.hBoxScroll.getStyleClass().add("hboxscroll");
		this.choices.getStyleClass().add("choices");
		this.buttonContainer.getStyleClass().add("buttoncontainer");
		loadEditor();
		save.setFitWidth(80);
		save.setFitHeight(80);
		clear.setFitHeight(80);
		clear.setFitWidth(80);
		back.setFitHeight(70);
		back.setFitWidth(90);
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.getChildren().addAll(save, clear, back);
		addEvents();
		this.add(choices, 0, 0);
		this.add(buttonContainer, 0, 1);

		setConstraints();

		this.scrollPaneChoice.setOnMouseMoved(e -> {

			if (scrollPaneChoice.getWidth() - e.getX() < 200.0) {
				double hval = scrollPaneChoice.getHvalue();
				hval += 0.012;
				scrollPaneChoice.setHvalue(hval);
			}

			if (scrollPaneChoice.getWidth() - (scrollPaneChoice.getWidth() - e.getX()) < 200.0) {
				double hval = scrollPaneChoice.getHvalue();
				hval -= 0.012;
				scrollPaneChoice.setHvalue(hval);
			}
		});
	}

	public void loadEditor() {
		addStandardBlock();
		addAnimatedBlock();
		addItem();
		addEnemy();

	}

	public void showSubMenu(HBox o, double x, double y) {
		if (!choices.getChildren().contains(scrollPaneChoice))
			mapEditor.addObject(transparentPane);

		transparentPane.setPrefSize(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		transparentPane.setVisible(true);

		scrollPaneChoice.setPrefSize(1000, 81);

		if (!transparentPane.getChildren().contains(scrollPaneChoice)) {
			transparentPane.getChildren().add(scrollPaneChoice);
			scrollPaneChoice.setContent(o);
			scrollPaneChoice.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					transparentPane.getChildren().clear();
					transparentPane.setVisible(false);
					scrollPaneChoice.setHvalue(0);
				}
			});
		}

		scrollPaneChoice.setLayoutX(x);
		scrollPaneChoice.setLayoutY(y);
		scrollPaneChoice.setPrefSize(1000, 100);

	}

	private void addItem() {
		List<String> cubes = ImageProvider.getInstance().getItemPaths();
		for (int i = 0; i < cubes.size(); i++) {
			AbstractObject abstractObject = new AbstractObject(
					ImageProvider.getInstance().getImageTypes().get(cubes.get(i)),
					ImageProvider.getInstance().getItems(cubes.get(i)));
			addEvent(abstractObject);
			items.addObject(abstractObject);
		}
		choices.getChildren().add(items);
		items.setPreview();
	}

	private void addEnemy() {
		List<String> cubes = ImageProvider.getInstance().getEnemyPaths();
		for (int i = 0; i < cubes.size(); i++) {
			AbstractObject abstractObject = new AbstractObject(
					ImageProvider.getInstance().getImageTypes().get(cubes.get(i)),
					ImageProvider.getInstance().getEnemy(cubes.get(i)));
			addEvent(abstractObject);
			enemies.addObject(abstractObject);
		}
		choices.getChildren().add(enemies);
		enemies.setPreview();
	}

	private void addAnimatedBlock() {

		List<String> cubes = ImageProvider.getInstance().getAnimatedPaths();
		for (int i = 0; i < cubes.size(); i++) {
			System.out.println("cube: "+cubes.get(i));
			AbstractObject abstractObject = new AbstractObject(
					ImageProvider.getInstance().getImageTypes().get(cubes.get(i)),
					ImageProvider.getInstance().getSpecialBlock(cubes.get(i)));
			addEvent(abstractObject);
			animatedBlock.addObject(abstractObject);
		}
		choices.getChildren().add(animatedBlock);
		animatedBlock.setPreview();

	}

	private void addStandardBlock() {
		List<String> cubes = ImageProvider.getInstance().getColoredPaths();

		for (int i = 0; i < cubes.size(); i++) {
			AbstractObject abstractObject = new AbstractObject(5,
					ImageProvider.getInstance().getSimpleBlock1(cubes.get(i)), cubes.get(i));
			addEvent(abstractObject);

			standardBlock.addObject(abstractObject);
		}
		choices.getChildren().add(standardBlock);
		standardBlock.setPreview();
	}

	public void setConstraints() {
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(60);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(40);
		this.getRowConstraints().addAll(row1, row2);
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(100);
		this.getColumnConstraints().add(col);
	}

	public AbstractObject getChoice() {
		return choice;
	}

	public void setChoice(AbstractObject choice) {
		this.choice = choice;
	}

	private void addEvents() {
		this.back.setOnMouseClicked(e -> {
			EditorManager.getInstance().goToWorldSize(worldSize);
		});
		this.save.setOnMousePressed(e -> {
			save.setEffect(new Glow(0.8));
			mapEditor.saveMap();
		});
		this.save.setOnMouseReleased(e -> {
			save.setEffect(null);
		});
		this.clear.setOnMousePressed(e -> {
			clear.setEffect(new Glow(0.8));
			mapEditor.clearEditor();
		});
		this.clear.setOnMouseReleased(e -> {
			clear.setEffect(null);
		});
	}

	private void addEvent(AbstractObject abstractObject) {

		abstractObject.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (event.getTarget() instanceof AbstractObject) {
					choice = (AbstractObject) event.getTarget();
					transparentPane.setVisible(false);
				}
			}
		});
	}

}
