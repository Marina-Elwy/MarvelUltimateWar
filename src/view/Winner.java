package view;

//import engine.Player;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Winner {
	StackPane root = new StackPane();
	Stage stage;
	Scene scene;
public Winner(Stage stage,String p) {
	this.stage=stage;
	HBox win= new HBox();
	Label l = new Label("Congratulations");
	Label l2 = new Label(p+" you have won!");
	win.getChildren().addAll(l,l2);
	scene=new Scene(win,200,200);
	 stage.setScene(scene);
	 stage.show();
}
public static void main(String[] args) {
	launch(args);
}
//private Scene initStage() {
//	// TODO Auto-generated method stub
//	return new Scene(root,1000,1000);
//}
private static void launch(String[] args) {
	// TODO Auto-generated method stub
	
}

}
