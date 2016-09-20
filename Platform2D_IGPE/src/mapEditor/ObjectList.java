package mapEditor;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mapEditor.AbstractObject;

public class ObjectList extends ImageView {

	private HBox list = new HBox();
	ChoicePane choice;
	public ObjectList(ChoicePane c) {
		this.setFitHeight(100);
		this.setFitWidth(100);
//		System.out.println(" scrollpane c " + c);
		choice = c;
		list.setSpacing(10);
		// TODO Auto-generated constructor stub
		list.setStyle("	-fx-background-color: rgba(0, 0, 0, 0.45);-fx-alignment: center;");
		list.setPrefSize(1000, 81);
		
		this.setOnMouseClicked(e -> {
//			System.out.println("click event preview");
			choice.showSubMenu(list , e.getSceneX() , e.getSceneY());
		});
		
	}
	
	public void addObject(AbstractObject t){
		list.getChildren().add(t);
	}

	public void setPreview() {
		this.setImage(((ImageView) list.getChildren().get((int) (Math.random()*
				list.getChildren().size()))).getImage());
	}
	
	
	
}
