package gui.panel.singlePlayer;

import gui.panel.AbstractCustomComponent;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class CustomListView extends AbstractCustomComponent {

	private CustomListObject selectedItem = null;
	private int selectedLevel = 0;
	// private Rectangle item;
	private VBox container = new VBox(5);
	private ScrollPane scrollContainer = new ScrollPane();

	public CustomListView(double width, double height, double x, double y) {
		super(width, height, x, y);
		this.setComponentBackground("listBackground");

		scrollContainer.setLayoutX(width * 0.5 - ((width * 0.8) / 2));
		scrollContainer.setLayoutY(height * 0.08);

		scrollContainer.setPrefWidth(width * 0.85);
		scrollContainer.setPrefHeight(height * 0.85);
		scrollContainer.setStyle("-fx-background:transparent; -fx-background-color: transparent;");
		scrollContainer.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollContainer.setHbarPolicy(ScrollBarPolicy.NEVER);

		this.scrollContainer.setContent(container);
		this.getChildren().add(scrollContainer);
	}

	@Override
	public void addItem(Node n) {
		this.container.getChildren().add(n);
	}

	@Override
	public void reset() {
		container.getChildren().clear();
	}

	public void setSelection(int index) {
		selectedLevel = index;
	}

	public String getSelectedMap() {
		if (selectedItem != null)
			return selectedItem.getName();

		return null;
	}

	public void selectMap(CustomListObject selected) {
		if(selectedItem != null)
			selectedItem.resetBackground();
		selectedItem = selected;
	}
}
