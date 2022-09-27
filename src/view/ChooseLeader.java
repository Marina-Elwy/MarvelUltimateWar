package view;

import java.util.ArrayList;

import engine.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.world.Champion;

public class ChooseLeader {
	StackPane root = new StackPane();
	VBox all = new VBox();
	HBox p1 = new HBox();
	HBox p2 = new HBox();
	Label p1n = new Label ();
	Label p2n = new Label ();

Stage stage;
ArrayList<Button> team1but = new ArrayList<Button>();
ArrayList<Button> team2but = new ArrayList<Button>();
	public ChooseLeader(Stage stage,Game game)
	{
	   
	
		Button next = new Button("Next"); 
		next.setFont(new Font("Impact",15));
		next.setTextFill(Color.BLACK);
		Label p1head = new Label("Choose Player One Leader");
		p1head.setFont(new Font("Impact",20));
		p1head.setTextFill(Color.WHITE);
		//p1.getChildren().add(p1head);
		
		for(Champion c : game.getFirstPlayer().getTeam()) {
			Button b = new Button(c.getName());
			b.setFont(new Font("Impact",15));
			b.setTextFill(Color.BLACK);
			team1but.add(b);
			p1.getChildren().add(b);
		}
		Label p2head = new Label("Choose Player Two Leader");
		p2head.setFont(new Font("Impact",20));
		p2head.setTextFill(Color.WHITE);
		//p1.getChildren().add(p2head);
		
		for(Champion c : game.getSecondPlayer().getTeam()) {
			Button b = new Button(c.getName());
			b.setFont(new Font("Impact",15));
			b.setTextFill(Color.BLACK);
			team2but.add(b);
			p2.getChildren().add(b);
		}
		for(Button b :team1but ){
			int i = team1but.indexOf(b);
			b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(i));
			      p1n.setText(game.getFirstPlayer().getLeader().getName());
			      p1n.setFont(new Font("Impact",15));
			      p1n.setTextFill(Color.WHITE);
//					for(Button b :team1but ){
					//b.setDisable(true);
//					}
					
				}
				
			});
			
			
		}
		for(Button b :team2but ){
			int i = team2but.indexOf(b);
			b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(i));
					p2n.setFont(new Font("Impact",15));
					p2n.setText(game.getSecondPlayer().getLeader().getName());
					p2n.setTextFill(Color.WHITE);
//					for(Button b :team2but ){
//						b.setDisable(true);
//					}
					
				}
				
			});
		
		}
//		Label p1n = new Label (game.getFirstPlayer().getLeader().getName());
//		Label p2n = new Label (game.getSecondPlayer().getLeader().getName());
//		VBox box = new VBox(); 
//		box.getChildren().add(new Label(game.getFirstPlayer().getLeader().getName()));
//		
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(game.getSecondPlayer().getLeader()!=null && game.getFirstPlayer().getLeader()!=null ) {
				StartGame s = new StartGame(stage,game);
				}	
			}
			
		});
		
		
//		
//		p1.getChildren().add(box);
//		VBox last = new VBox();
//		last.getChildren().addAll(p1n,p2n);
		all.getChildren().addAll(p1head,p1, p1n,p2head,p2,next,p2n);
		Image background = new Image("bg.png");
		BackgroundImage  c=new BackgroundImage (background, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,null);
		Background bg = new Background(c);
	    
		all.setBackground(bg);
			
		Scene scene=new Scene(all,1100,960);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	
	
	
	

//private Scene initStage() {
//		// TODO Auto-generated method stub
//		return new Scene(all,800,800);
//	}
	public static void main(String[] args) {
		launch(args);
	}
	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	
}
