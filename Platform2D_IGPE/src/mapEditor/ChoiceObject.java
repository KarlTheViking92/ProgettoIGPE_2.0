package mapEditor;

import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
	private List<AbstractObject> listVBox;
	// private ScrollPane scrollChoice;
	private AbstractObject objectSelected;

	public ChoiceObject(List<AbstractObject> listVBox, MapEditor mapEditor) {
		this.pane = new Pane();
		this.pane.setPrefSize(1000, 1000);
		this.vboxChoice = new VBox();
		// this.scrollChoice = new ScrollPane();

		this.mapEditor = mapEditor;
		this.listVBox = listVBox;
		this.setSpacing(5);
		this.hboxScroll = new HBox(10);
		hboxScroll.setBackground(new Background(new BackgroundFill(Color.DARKSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
		this.hboxScroll.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(10));
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
		Rectangle r6 = new Rectangle(50, 50);
		r6.setFill(Color.CYAN);
		this.getChildren().add(r6);
		Rectangle r4 = new Rectangle(50, 50);
		r4.setFill(Color.BLUE);
		hboxScroll.getChildren().add(r4);
		Rectangle r5 = new Rectangle(50, 50);
		r5.setFill(Color.BLUE);
		hboxScroll.getChildren().add(r5);
		choiceMenuListener(r3);
		choiceMenuListener(r6);
		focusScroll();
	}

	private void choiceMenuListener(Node n) {
		n.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				// choice = ((Rectangle) event.getTarget()).getCode();

				System.out.println("choice " + choice);

				x = (int) event.getSceneX();
				y = (int) event.getSceneY();
				System.out.println(event.getSceneX() + "  " + event.getSceneY());
				scroll();
				// choiceObjectListener();

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
		Pane scrollChoice = new Pane();
		scrollChoice.setBackground(new Background(new BackgroundFill(Color.FUCHSIA, CornerRadii.EMPTY, Insets.EMPTY)));
		System.out.println("hash code " + scrollChoice.hashCode());
		System.out.println("children " + pane.getChildren().size());

		mapEditor.addObject(pane);
		pane.setVisible(true);
//		pane.setBackground(new Background(new BackgroundFill(Color.FUCHSIA, CornerRadii.EMPTY, Insets.EMPTY)));
		if (!pane.getChildren().contains(scrollChoice)) {
			scrollChoice.setPrefSize(500, 100);
			addBlock();	
			scrollChoice.setLayoutX(x);
			scrollChoice.setLayoutY(y);
			scrollChoice.setPadding(new Insets(10));
			// scrollChoice.setPrefSize(1000, 100);
//			scrollChoice.setHbarPolicy(ScrollBarPolicy.NEVER);
//			scrollChoice.setVbarPolicy(ScrollBarPolicy.NEVER);
			scrollChoice.getChildren().add(hboxScroll);
			pane.getChildren().add(scrollChoice);
			scrollChoice.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					pane.getChildren().clear();
					pane.setVisible(false);
				}
			});
			// addPaneAtScrollPane();
		} else
			System.out.println("sono io che non disegno");

	}


	// controllo sul cursore/scrollpane
	// private boolean containsPoint(Point2D point) {
	// if (point.getX() > scrollChoice.getLayoutX()
	// && point.getX() < scrollChoice.getLayoutX() + scrollChoice.getPrefWidth()
	// && point.getY() > scrollChoice.getLayoutY()
	// && point.getY() < scrollChoice.getLayoutY() +
	// scrollChoice.getPrefHeight())
	// return true;
	//
	// return false;
	// }

	private void focusScroll() {

	}

	public void addBlock() {
		// hboxScroll.getChildren().add(new Block1(3));
		// hboxScroll.getChildren().add(new Block2(4));
	}

	public void addObjectEditor() {
		// listVBox.add(new MyGem(1,this));
		// listVBox.add(new MyBlock(2,this));

		// vboxChoice.getChildren().addAll(listVBox);
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
