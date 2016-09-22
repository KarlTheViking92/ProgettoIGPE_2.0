package mapEditor;

import java.util.List;

import gui.ImageProvider;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class ChoicePane extends Pane {

	private AbstractObject choice;
	private Scene s;
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Map mapEditor;
	private final int SPACING = 20;
	private WorldSizeSelector worldSize = new WorldSizeSelector(s);
	private ScrollPane scrollPaneChoice = new ScrollPane();
	private Pane transparentPane = new Pane();
	private Rectangle rect = new Rectangle();
	private HBox hBoxScroll;
	private Pane backgroundPane = new Pane();
	private ObjectList standardBlock = new ObjectList(this);
	private ObjectList animatedBlock = new ObjectList(this);
	private ObjectList items = new ObjectList(this);
	private ObjectList enemies = new ObjectList(this);
	private ImagePattern imageBackground;
	private ImageView save;
	private ImageView clear;
	private ImageView back;

	public ChoicePane(List<AbstractObject> listImageView, Map mapEditor, Scene s) {
		this.mapEditor = mapEditor;
		this.choice = null;
		this.s = s;
		this.save = new ImageView(ImageProvider.getInstance().getEditorImage("saveEditor"));
		this.clear = new ImageView(ImageProvider.getInstance().getEditorImage("clearEditor"));
		this.back = new ImageView(ImageProvider.getInstance().getEditorImage("BackButton"));
		this.scrollPaneChoice.getStyleClass().add("scrollpane");
		this.transparentPane.getStyleClass().add("transparent");
		this.imageBackground = new ImagePattern(ImageProvider.getInstance().getEditorImage("PanelTop"));
		this.hBoxScroll = new HBox();
		this.hBoxScroll.getStyleClass().add("hboxscroll");

		// this.choices.getStyleClass().add("choices");
		// this.buttonContainer.getStyleClass().add("buttoncontainer");
		loadEditor();
		addEvents();
		init();
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

	private void init() {
		this.rect.setFill(imageBackground);
		this.save.setFitHeight(80);
		this.save.setFitWidth(80);
		this.clear.setFitHeight(80);
		this.clear.setFitWidth(80);
		this.back.setFitHeight(80);
		this.back.setFitWidth(80);
		this.backgroundPane.getChildren().add(rect);
		this.rect.setWidth(screen.getWidth() - (screen.getWidth() * 0.9));
		this.rect.setHeight(screen.getHeight());
		this.standardBlock.setLayoutX(rect.getWidth() * 0.5 - (standardBlock.getFitWidth() / 2));
		this.standardBlock.setLayoutY(rect.getHeight() * 0.1);
		this.items.setLayoutX(standardBlock.getLayoutX());
		this.items.setLayoutY(standardBlock.getLayoutY() + standardBlock.getFitHeight() + SPACING);
		this.animatedBlock.setLayoutX(standardBlock.getLayoutX());
		this.animatedBlock.setLayoutY(items.getLayoutY() + items.getFitHeight() + SPACING);
		this.enemies.setLayoutX(standardBlock.getLayoutX());
		this.enemies.setLayoutY(animatedBlock.getLayoutY() + animatedBlock.getFitHeight() + SPACING);
		this.save.setLayoutX(standardBlock.getLayoutX());
		this.save.setLayoutY(enemies.getLayoutY() + enemies.getFitHeight() + SPACING);
		this.clear.setLayoutX(standardBlock.getLayoutX());
		this.clear.setLayoutY(save.getLayoutY() + save.getFitHeight() + SPACING);
		this.back.setLayoutX(standardBlock.getLayoutX());
		this.back.setLayoutY(clear.getLayoutY() + clear.getFitHeight() + SPACING);
		this.getChildren().addAll(rect, standardBlock, items, animatedBlock, enemies, save, clear, back);
	}

	public void showSubMenu(HBox o, double x, double y) {
		if (!this.getChildren().contains(scrollPaneChoice))
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
		enemies.setPreview();
	}

	private void addAnimatedBlock() {

		List<String> cubes = ImageProvider.getInstance().getAnimatedPaths();
		for (int i = 0; i < cubes.size(); i++) {
			System.out.println("cube: " + cubes.get(i));
			AbstractObject abstractObject = new AbstractObject(
					ImageProvider.getInstance().getImageTypes().get(cubes.get(i)),
					ImageProvider.getInstance().getSpecialBlock(cubes.get(i)));
			addEvent(abstractObject);
			animatedBlock.addObject(abstractObject);
		}
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
		standardBlock.setPreview();
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
