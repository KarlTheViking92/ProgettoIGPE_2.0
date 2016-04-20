package mapEditor;

import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ChoiceObject extends VBox {
	private MapEditor mapEditor;
	private int x;
	private int y;
	private int choice = 1;
	private VBox vboxChoice;
	private HBox hboxScroll;
	private Pane pane;
	private Pane paneScrollPane;
	private List<AbstractObject> listVBox;
	private ScrollPane scrollChoice;
	private AbstractObject objectSelected;

	public ChoiceObject(List<AbstractObject> listVBox, MapEditor mapEditor) {
		this.pane = new Pane();
		this.mapEditor = mapEditor;
		// this.setAlignment(Pos.TOP_CENTER);
		this.paneScrollPane = new Pane();
		this.vboxChoice = new VBox();
		this.hboxScroll = new HBox(10);
		this.listVBox = listVBox;

		this.scrollChoice = new ScrollPane();
		// this.setPrefSize(500, 100);
		this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		this.getChildren().add(vboxChoice);
		addObjectEditor();

		vboxChoice.setSpacing(10);
		vboxChoice.setPadding(new Insets(5.0));
		vboxChoice.setAlignment(Pos.TOP_CENTER);

		this.setAlignment(Pos.TOP_CENTER);
		Rectangle r3 = new Rectangle(50, 50);
		r3.setFill(Color.CYAN);
		this.getChildren().add(r3);
		choiceMenuListener();
		focusScroll();
	}

	private void choiceMenuListener() {
		vboxChoice.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// System.out.println("Evento target eliana
				// "+event.getTarget());

				if (event.getTarget() instanceof AbstractObject) {
					// setChoice(((AbstractObject) objectSelected).getCode());

					choice = ((AbstractObject) event.getTarget()).getCode();

					System.out.println("choice " + choice);
					System.out.println("x rettangolo " + event.getX());
					x = (int) event.getX();// il 50 è la dimensione del
											// rettangolo
					y = (int) event.getY();
					scroll();
					choiceObjectListener();
				}
			}
		});
	}

	private void choiceObjectListener() {
		hboxScroll.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// if (objectSelected instanceof MyBlock) {
				// setChoice(((MyBlock) objectSelected).getCode());
				// System.out.println(((MyBlock) objectSelected).getCode());
				// }
			}
		});
	}

	private void scroll() {
		if (!this.getChildren().contains(scrollChoice))
			mapEditor.addObject(pane);
		pane.setPrefSize(1000, 1000);
		pane.setVisible(true);
		// pane.setBackground(new Background(new
		// BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		if (!pane.getChildren().contains(scrollChoice)) {
			pane.getChildren().add(scrollChoice);
			addPaneAtScrollPane();
		}
		scrollChoice.setLayoutX(x);
		scrollChoice.setLayoutY(y);
		scrollChoice.setPrefSize(1000, 100);
		scrollChoice.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollChoice.setVbarPolicy(ScrollBarPolicy.NEVER);
	}

	private void addPaneAtScrollPane() {
		scrollChoice.setContent(paneScrollPane);
		paneScrollPane.setPrefSize(1000, 100);
		// paneScrollPane.setBackground(new Background(new
		// BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		paneScrollPane.getChildren().add(hboxScroll);
		addBlock();
	}

	// controllo sul cursore/scrollpane
	private boolean containsPoint(Point2D point) {
		if (point.getX() > scrollChoice.getLayoutX()
				&& point.getX() < scrollChoice.getLayoutX() + scrollChoice.getPrefWidth()
				&& point.getY() > scrollChoice.getLayoutY()
				&& point.getY() < scrollChoice.getLayoutY() + scrollChoice.getPrefHeight())
			return true;

		return false;
	}

	private void focusScroll() {
		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (!containsPoint(new Point2D(event.getX(), event.getY())))
					pane.setVisible(false);
			}
		});
	}

	public void addBlock() {
		// hboxScroll.getChildren().add(new Block1(3));
		// hboxScroll.getChildren().add(new Block2(4));
	}

	public void addObjectEditor() {
		// listVBox.add(new MyGem(1,this));
		// listVBox.add(new MyBlock(2,this));

		// vboxChoice.getChildren().addAll(listVBox);
		System.out.println("vbox size " + vboxChoice.getChildren().size());
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public void setObjectSelected(AbstractObject objectSelected) {
		this.objectSelected = objectSelected;
	}
}
