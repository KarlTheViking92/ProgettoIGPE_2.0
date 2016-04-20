package mapEditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMapEditor extends Application {

	MapEditor map; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		AnchorPane anchor = new AnchorPane();
		
		map = new MapEditor(30,40);
//		anchor.setTopAnchor(map, 0.0);
//		anchor.setBottomAnchor(map, 0.0);
//		anchor.setLeftAnchor(map, 0.0);
//		anchor.setRightAnchor(map, 0.0);
//		anchor.getChildren().add(map);
		
		Scene scene = new Scene(map,1500,1000);
		primaryStage.setScene(scene);
		primaryStage.setTitle("asdasda");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
