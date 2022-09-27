package view;

import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import engine.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.abilities.Ability;
import model.world.Champion;
import javafx.scene.input.*;


public class SubClass {

StackPane root = new StackPane();
Stage stage;
Player pl1;
Player pl2;
Game game;
ArrayList<String> team1name = new ArrayList<String>();
ArrayList<String> team2name = new ArrayList<String>();

ArrayList<Button> champbtn = new ArrayList<Button>();



public SubClass(Stage stage)
{	
	HBox main = new HBox();
	VBox left = new VBox();
	HBox player1n = new HBox();
	VBox right = new VBox();
//	main.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE,null,null)));
//	left.setBackground(new Background(new BackgroundFill(Color.GOLD,null,null)));
//	right.setBackground(new Background(new BackgroundFill(Color.INDIANRED,null,null)));
	Image background = new Image("bg.png");
	BackgroundImage  c=new BackgroundImage (background, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,null);
	Background bg = new Background(c);
    
	main.setBackground(bg);
		
	
	Label l1 = new Label("Player 1 name: ");
	l1.setFont(new Font("Impact",20));
	l1.setTextFill(Color.WHITE);
	TextField t1 = new TextField();
	t1.setFont(new Font("Impact",20));
	player1n.getChildren().addAll(l1, t1);
	player1n.setSpacing(30);
	
	//correct code for enter
	t1.setOnKeyPressed(new EventHandler<KeyEvent>()
   {
        @Override
        public void handle(KeyEvent t)
        {
            if (t.getCode() == KeyCode.ENTER)
            {
            	if (t1.getText() != null && !t1.getText().isEmpty()) {
    				l1.setText("Player 1 name is " + t1.getText());
    					player1n.getChildren().removeAll(t1);
   					pl1 = new Player(t1.getText());
    					
    				} else {
    				l1.setText("Please Enter Name");
    				l1.setFont(new Font("Impact",20));
    				l1.setTextFill(Color.WHITE);
   				}
    		}
        }}
    );
//end of correct enter
	
	
//	t1.onKeyPressedProperty(new EventHandler<  KeyEvent>(){
//
//		@Override
//		public void handle(KeyEvent event) {
//			if (t1.getText() != null && !t1.getText().isEmpty()) {
//				l1.setText("Player 1 name is " + t1.getText());
//					player1n.getChildren().removeAll(t1, b);
//					pl1 = new Player(t1.getText());
//					
//				} else {
//				l1.setText("Please Enter Name");
//				}
//		}
//		
//	});

//	b.setOnAction(new EventHandler<ActionEvent>() {
//		public void handle(ActionEvent event) {
//			if (t1.getText() != null && !t1.getText().isEmpty()) {
//			l1.setText("Player 1 name is " + t1.getText());
//				player1n.getChildren().removeAll(t1, b);
//				pl1 = new Player(t1.getText());
//				
//			} else {
//			l1.setText("Please Enter Name");
//			}
//		}
//	}
//	
//
//	);
	
	Label l2 = new Label("Player 2 name: ");
	l2.setFont(new Font("Impact",20));
	l2.setTextFill(Color.WHITE);
	TextField t2 = new TextField();
	t2.setFont(new Font("Impact",20));
	HBox player2n = new HBox();
	player2n.getChildren().addAll(l2, t2);
	player2n.setSpacing(30);

	t2.setOnKeyPressed(new EventHandler<KeyEvent>()
    {
        @Override
        public void handle(KeyEvent t)
        {
            if (t.getCode() == KeyCode.ENTER)
            {
            	if (t1.getText() != null && !t1.getText().isEmpty()) {
    				l2.setText("Player 2 name is " + t2.getText());
    					player2n.getChildren().removeAll(t2);
    					l2.setTextFill(Color.WHITE);
   					pl2 = new Player(t2.getText());
    					
    				} else {
    				l2.setText("Please Enter Name");
    				l2.setFont(new Font("Impact",20));
    				l2.setTextFill(Color.WHITE);
   				}
    		}
        }}
    );

	VBox enter = new VBox();
	GridPane champions = new GridPane();
	Button b3 = new Button("Choose Champions");
	b3.setFont(new Font("Impact",20));
	b3.setTextFill(Color.BLACK);
	//b3.setDefaultButton(true);
	//StackPane stacktext = new StackPane();
	b3.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			if (t2.getText()!=null && t1.getText()!=null && !t1.getText().isEmpty() && !t2.getText().isEmpty()) {
				game = new Game(pl1,pl2);
				try {
					game.loadAbilities("Abilities.csv");
					game.loadChampions("Champions.csv");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Label l4 = new Label("Available Champions are: ");
				l4.setFont(new Font("Impact",20));
				l4.setTextFill(Color.WHITE);
				enter.getChildren().add(l4);
				int j=0;
				int c=0;
				int k=0;
				for (int i=0;i<game.getAvailableChampions().size();i++) {
					Button b = new Button(game.getAvailableChampions().get(i).getName());
					b.setFont(new Font("Impact",15));
					b.setTextFill(Color.BLACK);
					b.setPrefSize(150, 80);
					champions.add(b, k,j);
					champbtn.add(b);
					c++;
					k++;
					if (c==3) {
						c=0;
						j++;
						k=0;
					}
				}
			  
				for (Button b1: champbtn) {
				
					b1.setOnAction(new EventHandler<ActionEvent>() {

						public void handle(ActionEvent e) {
							int i =champbtn.indexOf(b1);
							right.getChildren().clear();
							String s =abilitiestostring(game.getAvailableChampions().get(i));
							Label nk = new Label(game.getAvailableChampions().get(i).toString()+"\nAbilities :"+s);
							nk.setFont(new Font("Impact",15));
		    				nk.setTextFill(Color.WHITE);
							right.getChildren().add(nk);
							Button cham1 = new Button(" Add Champion to "+game.getFirstPlayer().getName() +"'s Team ");
							Button cham2 = new Button(" Add Champion to "+game.getSecondPlayer().getName() + "'s Team ");
							cham1.setFont(new Font("Impact",15));
							cham1.setTextFill(Color.BLACK);
							cham2.setFont(new Font("Impact",15));
							cham2.setTextFill(Color.BLACK);
							VBox choose = new VBox();
						    HBox team1=new HBox();
						    HBox team2=new HBox();
						    team1.getChildren().addAll(cham1);
						    team2.getChildren().addAll(cham2);
						    Label text1 = new Label(game.getFirstPlayer().getName()+"'s Team:  "+team1name.toString());
						    text1.setFont(new Font("Impact",15));
		    				text1.setTextFill(Color.WHITE);
		    				Label text2 = new Label(game.getSecondPlayer().getName()+"'s Team  "+team2name.toString());
							text2.setFont(new Font("Impact",15));
			    			text2.setTextFill(Color.WHITE);
						    choose.getChildren().addAll(team1,team2 ,text1,text2);
						    right.getChildren().addAll(choose);
					
						 
						    cham1.setOnAction(new EventHandler<ActionEvent>() {
						    
								@Override
								public void handle(ActionEvent event) {
									if(pl1.getTeam().size()<3&& !b1.isDisabled()) {
										
										pl1.getTeam().add(game.getAvailableChampions().get(i));
										team1name.add(game.getAvailableChampions().get(i).getName());
								
										b1.setDisable(true);
										Label lo2 =new Label(game.getAvailableChampions().get(i).getName());
										lo2.setFont(new Font("Impact",15));
					    				lo2.setTextFill(Color.WHITE);
										choose.getChildren().add(lo2);
										
									}
									else if(pl1.getTeam().size()>=3){
										cham1.setDisable(true);
										Label lo1 =new Label(game.getAvailableChampions().get(i).getName());
										lo1.setFont(new Font("Impact",15));
					    				lo1.setTextFill(Color.WHITE);
										choose.getChildren().add(lo1);
										
									}
									
									
								}
						    	
						    });
						    cham2.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									if(pl2.getTeam().size()<3 &&  !b1.isDisabled()) {
										pl2.getTeam().add(game.getAvailableChampions().get(i));
										team2name.add(game.getAvailableChampions().get(i).getName());
										b1.setDisable(true);
										Label lo =new Label(game.getAvailableChampions().get(i).getName());
										choose.getChildren().add(lo);
										lo.setFont(new Font("Impact",15));
					    				lo.setTextFill(Color.WHITE);
										
									}
									else if (pl2.getTeam().size()>=3){
										cham2.setDisable(true);
										Label lo1 =new Label(game.getAvailableChampions().get(i).getName());
										choose.getChildren().add(lo1);
										lo1.setFont(new Font("Impact",15));
					    				lo1.setTextFill(Color.WHITE);
									}
									
									
								}
						    	
						    });
						  
////						    lead1.setOnAction(new EventHandler<ActionEvent>() {
////
////								@Override
////								public void handle(ActionEvent event) {
////									if(pl1.getLeader()==null && !b1.isDisabled()) {
////										pl1.getTeam().add(game.getAvailableChampions().get(i));
////										//team1name.add(game.getAvailableChampions().get(i).getName());
////										choose.getChildren().add(new Label(team1name.toString()));
////										choose.getChildren().add(new Label("Player One Leader is  "+ game.getAvailableChampions().get(i).getName()));
////										pl1.setLeader(game.getAvailableChampions().get(i));
////										b1.setDisable(true);
////									}
////									else {
////										lead1.setDisable(true);
////										choose.getChildren().add(new Label("Player 1 Team :\n"+ team1name.toString()));
////										choose.getChildren().add(new Label("\nLeader is  "+ game.getAvailableChampions().get(i).getName()));
////									}
////									// TODO Auto-generated method stub
//									
//								}
//						    	
//						    	
//						    	
//						    });
						 
						    
//						    lead2.setOnAction(new EventHandler<ActionEvent>() {
//
//								@Override
//								public void handle(ActionEvent event) {
//									if(pl2.getLeader()==null && !b1.isDisabled()) {
//										pl2.getTeam().add(game.getAvailableChampions().get(i));
//										team1name.add(game.getAvailableChampions().get(i).getName());
//										choose.getChildren().add(new Label(team2name.toString()));
//										choose.getChildren().add(new Label("Player two Leader is  "+ game.getAvailableChampions().get(i).getName()));
//										pl2.setLeader(game.getAvailableChampions().get(i));
//										b1.setDisable(true);
//									}
//									else {
//										lead1.setDisable(true);
//										choose.getChildren().add(new Label("Player 2 Team :\n"+ team1name.toString()));
//										choose.getChildren().add(new Label("\nLeader is  "+ game.getAvailableChampions().get(i).getName()));
//									}
//									// TODO Auto-generated method stub
//									
//								}
//						    	
//						    	
//						    	
//						    });
//						    
//						    
							//right.getChildren().add(stacktext);
						}
						
					});
					
					
					
				}
				enter.getChildren().addAll(champions);
			}
			else {//only add it once then remove it
				Label l3 = new Label("Enter Player Names");
				l3.setFont(new Font("Impact",20));
				l3.setTextFill(Color.WHITE);
				enter.getChildren().add(l3);
			}
			b3.setDisable(true);
		}
		
	});
	

	
	
//
//	Button c1 = new Button("thor");
//	Button c2 = new Button("ironman");
//	Button c3 = new Button("hela");
//	Button c11 = new Button("captain america");
//	Button c21 = new Button("hulk");
//	Button c31= new Button("black widow");

//	champions.add(c1, 0, 0);
//	champions.add(c2, 10, 0);
//	champions.add(c3, 20, 0);
//	champions.add(c11, 0, 10);
//	champions.add(c21, 10, 10);
//	champions.add(c31, 20, 10);
	champions.setVgap(0.5);
    champions.setHgap(0.5);
	

   Button next = new Button("Next");
	left.getChildren().addAll(player1n, player2n,b3,enter,next);
	left.setSpacing(30);
	next.setFont(new Font("Impact",20));
	next.setTextFill(Color.BLACK);
	next.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()==3) {
			ChooseLeader s = new ChooseLeader( stage,game);
			}
		}
		
	});
	
	
	
	main.getChildren().addAll(left,right);
    Scene scene=new Scene(main,1100,960);
    stage.setScene(scene);
    stage.show();
   // main.getChildren().addAll(right);
//    Scene sc = new Scene(main,800,800);
//    stage.setScene(sc);
//    stage.show();
}
public String abilitiestostring (Champion c) {
	String s = "";
	for (Ability a:c.getAbilities()) {
		
		s+= "\n"+ a.getName();
		
	}
	return s;
	
}
public static void main(String[] args) {
	launch(args);
}

private static void launch(String[] args) {
	// TODO Auto-generated method stub
	
}

}