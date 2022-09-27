package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



public class View extends Application {
	Button btn = new Button("Start Game");
	
	Stage primaryStage;
	Scene scene;
	VBox root;
	
	
	public View() {
		btn.setDefaultButton(true);
		btn.setFont(new Font("Impact",15));
		btn.setTextFill(Color.BLACK);
		btn.setOnAction((e)->{
			SubClass s = new SubClass(primaryStage);
		});
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage= primaryStage;
			root = new VBox();
			
			root.setAlignment(Pos.CENTER);
			Label l = new Label("Marvel Ultimate War");
			l.setFont(new Font("Impact",65));
			l.setTextFill(Color.WHITESMOKE);
			root.getChildren().addAll(l,btn);
			Image background = new Image("back.png");
		BackgroundImage  c=new BackgroundImage (background, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,null);
		Background bg = new Background(c);
	    
		root.setBackground(bg);
			
			//root.setBackground(new Background(new BackgroundFill(Color.DARKRED,null,null)));
			//Font f = new Font();
			scene = initStage();
			primaryStage.setScene(scene);
			primaryStage.show();
			//scene.setTitle("Marvel Infinity War");
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private Scene initStage() {
		// TODO Auto-generated method stub
		return new Scene(root,1100,960);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
