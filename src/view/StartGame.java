package view;

import java.util.ArrayList;


import engine.Game;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class StartGame  {
	
	VBox window = new VBox();
	
	HBox topsherita =new HBox();
	HBox center = new HBox();
	HBox bottomsherita =new HBox();
	GridPane grid = new  GridPane();
	VBox sidebar = new VBox ();
	HBox sideaction = new HBox ();
	VBox action = new VBox();
	HBox abilitiesdet = new HBox();
	VBox currentcham = new VBox();
	VBox champdet = new VBox();	
	ArrayList<MenuItem> champabil = new ArrayList<MenuItem>();
	HBox winner = new HBox();
	HBox bottomchamp = new HBox();
//	HBox p1 = new HBox();
//	HBox p2 = new HBox();

Stage stage;
public StartGame(Stage stage,Game game)
{
	//topshereeta
	
	Game game2 = new Game(game.getFirstPlayer(),game.getSecondPlayer());
	
	Label p1 = new Label ("Player 1:   "+game.getFirstPlayer().getName());
	Label p2 = new Label ("Player 2:   "+game.getSecondPlayer().getName());
	p1.setFont(new Font("Impact",65));
	p1.setTextFill(Color.WHITE);
	p2.setFont(new Font("Impact",65));
	p2.setTextFill(Color.WHITE);
	VBox play1 = new VBox();
	VBox play2 = new VBox();
	HBox play1lead = new HBox();
	HBox play2lead = new HBox();
	Text t1 = new Text(game.getFirstPlayer().getName()+" leader ability is not used");
	Text t2 = new Text(game.getSecondPlayer().getName()+" leader ability is not used");
	t1.setFont(new Font("Impact",30));
	t1.setFill(Color.WHITE);
	t2.setFont(new Font("Impact",30));
	t2.setFill(Color.WHITE);
	play1lead.getChildren().add(t1);
	play2lead.getChildren().add(t2);
	
	//if(game2.isFirstLeaderAbilityUsed()) {
		
	//}
//	//if(game2.isSecondLeaderAbilityUsed()) {
//		play2lead.getChildren().clear();
//		play2lead.getChildren().add(new Text("Second Player leader ability is used"));
//	//}
	play1.getChildren().addAll(p1,play1lead);
	play2.getChildren().addAll(p2,play2lead);
//	Rectangle champ1 = new Rectangle(50, 50);
//	Rectangle champ2 = new Rectangle(50, 50);
//	Rectangle champ3 = new Rectangle(50, 50);
//	Rectangle champ4 = new Rectangle(50, 50);
//	Rectangle champ5 = new Rectangle(50, 50);
//	Rectangle champ6 = new Rectangle(50, 50);
	
	topsherita.setSpacing(100);
	topsherita.getChildren().addAll(play1,play2);
	
	//center
	//grid
	//center.prefWidth(800);
	grid.setPrefSize(800, 800);
	//game.placeChampions();
	updateboard(game2,grid);
	abilitiesdet=abilitiesdetu(game2);
	bottomchamp = updatechampics(game2);
	//endofgrid
	
	//sidebar
	
	champdet = currentchampdets(game2);
	//Move
	MenuItem down = new MenuItem("Down");
	MenuItem up = new MenuItem("Up");
	MenuItem left = new MenuItem("Left");
	MenuItem right = new MenuItem("Right");
	MenuButton move = new MenuButton("Move",null,down,up,left);
	move.setFont(new Font("Impact",20));
	move.getItems().add(right);
	//Champion curr = game2.getCurrentChampion();
	
	down.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				game2.move(Direction.UP);
				
			} catch (NotEnoughResourcesException | UnallowedMovementException e) {
				//e.printStackTrace(); //error message
				//ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
				Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
				//error.getDialogPane().getButtonTypes().add(ok);
				error.show();
				
			}
			updateboard(game2, grid);
			champdet.getChildren().clear();
			champdet.getChildren().add(currentchampdets(game2));
			bottomchamp.getChildren().clear();
			bottomchamp.getChildren().add(updatechampics(game2));
		}
		
	});
	up.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				game2.move(Direction.DOWN);
			
			} catch (NotEnoughResourcesException | UnallowedMovementException e) {
				Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
				error.show();
			}
			updateboard(game2, grid);
			champdet.getChildren().clear();
			champdet.getChildren().add(currentchampdets(game2));
			bottomchamp.getChildren().clear();
			bottomchamp.getChildren().add(updatechampics(game2));
		}
		
	});
	left.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				game2.move(Direction.LEFT);
				
				//Label l= new Label (game2.getCurrentChampion().);
			} catch (NotEnoughResourcesException | UnallowedMovementException e) {
				Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
				error.show();
			}
			updateboard(game2, grid);
			//champdet =currentchampdets(game2);
			champdet.getChildren().clear();
			champdet.getChildren().add(currentchampdets(game2));
			bottomchamp.getChildren().clear();
			bottomchamp.getChildren().add(updatechampics(game2));
		}
		
	});
	right.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				game2.move(Direction.RIGHT);
				
			} catch (NotEnoughResourcesException | UnallowedMovementException e) {
				Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
				error.show();
			}
			updateboard(game2, grid);
			champdet.getChildren().clear();
			champdet.getChildren().add(currentchampdets(game2));
			bottomchamp.getChildren().clear();
			bottomchamp.getChildren().add(updatechampics(game2));
			
		}
		
	});
	
	
	// Attack
	MenuItem downa = new MenuItem("Down");
	MenuItem upa = new MenuItem("Up");
	MenuItem lefta = new MenuItem("Left");
	MenuItem righta = new MenuItem("Right");
	MenuButton attack = new MenuButton("Attack",null,downa,upa,lefta,righta);
	attack.setFont(new Font("Impact",20));
	//Champion curr = game2.getCurrentChampion();
	
	downa.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
		
				try {
					game2.attack(Direction.UP);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e) {
					// TODO Auto-generated catch block
					Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
					error.show();
				}
				updateboard(game2, grid);
				champdet.getChildren().clear();
				champdet.getChildren().add(currentchampdets(game2));
				checkgame(game2);
				bottomchamp.getChildren().clear();
				bottomchamp.getChildren().add(updatechampics(game2));
		}
		
	});
	upa.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
		
				try {
					game2.attack(Direction.DOWN);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e) {
					// TODO Auto-generated catch block
					Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
					error.show();
				}
				updateboard(game2, grid);
				champdet.getChildren().clear();
				champdet.getChildren().add(currentchampdets(game2));
				checkgame(game2);
				bottomchamp.getChildren().clear();
				bottomchamp.getChildren().add(updatechampics(game2));
				
		}
		
	});
	lefta.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
		
				try {
					game2.attack(Direction.LEFT);
				
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e) {
					// TODO Auto-generated catch block
					Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
					error.show();
				}
				updateboard(game2, grid);
				champdet.getChildren().clear();
				champdet.getChildren().add(currentchampdets(game2));
				checkgame(game2);
				bottomchamp.getChildren().clear();
				bottomchamp.getChildren().add(updatechampics(game2));
		}
		
	});
	righta.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
		
				try {
					game2.attack(Direction.RIGHT);
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e) {
					// TODO Auto-generated catch block
					Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
					error.show();
				}
				updateboard(game2, grid);
				champdet.getChildren().clear();
				champdet.getChildren().add(currentchampdets(game2));
				checkgame(game2);
				bottomchamp.getChildren().clear();
				bottomchamp.getChildren().add(updatechampics(game2));
		}
		
	});
	//end of attack
	
	//Cast ability

	HBox abox = new HBox();
	abox.getChildren().add(updateabil (game2));
	
	//end of cast ability
//	
//	Champion next =  (Champion) game2.getTurnOrder().getsecond();
//	  
//	Label nextturn = new Label();
//	nextturn.setText("Next turn: "+next.getName());
//nextturn.setFont(new Font("Impact",15));
//nextturn.setTextFill(Color.BLACK);
//
//
//	bottomchamp.getChildren().add(nextturn);

	//endturn
	Button end = new Button("End Turn");
	end.setFont(new Font("Impact",20));
	end.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			game2.endTurn();
			updateboard(game2, grid);
			champdet.getChildren().clear();
			champdet.getChildren().add(currentchampdets(game2));
			abox.getChildren().clear();
			abox.getChildren().add(updateabil (game2));
			abilitiesdet.getChildren().clear();
			abilitiesdet.getChildren().add(abilitiesdetu(game2));
			checkgame(game2);
			bottomchamp.getChildren().clear();
			bottomchamp.getChildren().add(updatechampics(game2));
//			Label nextturn = new Label();
//
//    if(game2.getTurnOrder().size()>=2) {
//			Champion nex =  (Champion) game2.getTurnOrder().getsecond();
//
//			nextturn.setText("Next turn: "+nex.getName());
//		nextturn.setFont(new Font("Impact",15));
//		nextturn.setTextFill(Color.BLACK);
//		
//    }
//    else {
//    	Champion nex ;
//    	PriorityQueue q = new PriorityQueue(game2.getFirstPlayer().getTeam().size()+game2.getSecondPlayer().getTeam().size());
//    	for(Champion c :game2.getFirstPlayer().getTeam()) {
//    		q.insert(c);
//    	}
//    	for(Champion c :game2.getSecondPlayer().getTeam()) {
//    		q.insert(c);
//    	}
//    	
//    	nex =  (Champion) q.peekMin();
//    	nextturn.setText("Next turn: "+nex.getName());
//    	nextturn.setFont(new Font("Impact",15));
//    	nextturn.setTextFill(Color.BLACK);
//    	
//    }
//
//				bottomchamp.getChildren().add(nextturn);
//		
			
			
		}
		
	});
	
	//end of endturn
	
	
	//leader ability
	//if(game2.getCurrentChampion()==game2.getFirstPlayer().getLeader()||game2.getCurrentChampion()==game2.getFirstPlayer().getLeader()) {
		Button leader = new Button("Use Leader Ability");
		leader.setFont(new Font("Impact",20));
		leader.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					game2.useLeaderAbility();	
					updateboard(game2, grid);
					champdet.getChildren().clear();
					champdet.getChildren().add(currentchampdets(game2));
					abox.getChildren().clear();
					abox.getChildren().add(updateabil (game2));
					bottomchamp.getChildren().clear();
					bottomchamp.getChildren().add(updatechampics(game2));
					if(game2.isFirstLeaderAbilityUsed()) {
						Text t1 = new Text("First Player leader ability is used");
						play1lead.getChildren().clear();
						play1lead.getChildren().add(t1);
						t1.setFont(new Font("Impact",30));
						t1.setFill(Color.WHITE);
						
					}
					if(game2.isSecondLeaderAbilityUsed()) {
						Text t2 = new Text("Second Player leader ability is used");
						play2lead.getChildren().clear();
						play2lead.getChildren().add(t2);
						t2.setFont(new Font("Impact",30));
						t2.setFill(Color.WHITE);
					}
					checkgame(game2);
				} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e) {
					// TODO Auto-generated catch block
					Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
					error.show();
				}
				
			
			}
			
		});
	
	
	//}
	//end of leaderability
	
	sideaction.setSpacing(15);
	action.getChildren().addAll(move,attack,abox,leader,end);
	sideaction.getChildren().addAll(champdet ,action);
	sidebar.getChildren().addAll(sideaction,abilitiesdet);
	//endofsidebar
	
	center.getChildren().addAll(grid,sidebar);
	//end of center
	
	//start of bottom shereeta
	
	
	//bottomchamp.getChildren().addAll(l1,l2);
	bottomsherita.getChildren().addAll(bottomchamp);
	//end of bottom shereeta
	
	Image background = new Image("bg.png");
	BackgroundImage  c=new BackgroundImage (background, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,null);
	Background bg = new Background(c);
    
	window.setBackground(bg);
	window.getChildren().addAll( topsherita,center, bottomsherita);
	Scene scene=new Scene(window,1100,960);
    stage.setScene(scene);
    stage.show();	
    
    
    
}
public HBox updatechampics (Game game2) {
	HBox bottomchamp = new HBox();
	Label l1 = new Label(game2.getFirstPlayer().getName()+"'s Team");
	l1.setFont(new Font("Impact",15));
	l1.setTextFill(Color.WHITE);
	bottomchamp.getChildren().addAll(l1);
	for (Champion c: game2.getFirstPlayer().getTeam()) {
		ImageView im = champimage(c.getName());
		Tooltip t = new Tooltip();
		String n = "";
		if(game2.getFirstPlayer().getLeader().equals(c)) {
			    n= game2.getFirstPlayer().getName()+"'s leader" ;
			
		}
		   		    else {
		    	n= "not leader";
		    }
		
		t.setText(c.toString()+"\n"+n);
		im.setFitWidth(150);
		im.setFitHeight(150);
		im.setPreserveRatio(true);
		im.setSmooth(true);
		im.setCache(true);
		Tooltip.install(im, t);
		bottomchamp.getChildren().addAll(im);
	}
	Label l2 = new Label(game2.getSecondPlayer().getName()+"'s Team");
	l2.setFont(new Font("Impact",15));
	l2.setTextFill(Color.WHITE);
	bottomchamp.getChildren().addAll(l2);
	for (Champion c: game2.getSecondPlayer().getTeam()) {
		ImageView im = champimage(c.getName());
		Tooltip t = new Tooltip();
		String n = "";
		 if (game2.getSecondPlayer().getLeader().equals(c)) {
	            n= game2.getSecondPlayer().getName()+"'s leader" ;
		
	}
	    else {
	    	n= "not leader";
	    }
	
	    t.setText(c.toString()+"\n"+n);
	
		im.setFitWidth(150);
		im.setFitHeight(150);
		im.setPreserveRatio(true);
		im.setSmooth(true);
		im.setCache(true);
		Tooltip.install(im, t);
		bottomchamp.getChildren().addAll(im);

					
			
	}
	Label nextturn = new Label();
		if(game2.getTurnOrder().size()>=2) {
			Champion nex= (Champion) game2.getTurnOrder().getsecond();
			nextturn.setText("Next turn: "+nex.getName());
			nextturn.setFont(new Font("Impact",15));
			nextturn.setTextFill(Color.WHITE);
			bottomchamp.getChildren().add(nextturn);
		}
		
		else {
			PriorityQueue p=new PriorityQueue(game2.getFirstPlayer().getTeam().size()+ game2.getSecondPlayer().getTeam().size());
			for(Champion c: game2.getFirstPlayer().getTeam()) {
				p.insert(c);
			}
			for(Champion c: game2.getSecondPlayer().getTeam()) {
				p.insert(c);
			}
		
			while(hasEffect(p.peekMin(), "Stun")) {
				p.remove();
			}
			
			Champion nex= (Champion) p.peekMin();
			nextturn.setText("Next turn: "+nex.getName());
			nextturn.setFont(new Font("Impact",15));
			nextturn.setTextFill(Color.WHITE);
			bottomchamp.getChildren().add(nextturn);
		}
		
		
		
	
	//Champion nex=prep(game2) ;
		
	
		
	
	return bottomchamp;
	
}


public static void main(String[] args) {
	launch(args);
}
private static void launch(String[] args) {

	
}
public VBox currentchampdets(Game game) {
	VBox det = new VBox();
	Champion curr = game.getCurrentChampion();
	Label leader = new Label();
	if(game.getFirstPlayer().getLeader().equals(curr)) {
		leader.setText(game.getFirstPlayer().getName()+"'s Leader");
	}
	if(game.getSecondPlayer().getLeader().equals(curr)) {
		leader.setText(game.getSecondPlayer().getName()+"'s Leader");
	}
	leader.setFont(new Font("Impact",30));
	leader.setTextFill(Color.WHITE);
	
	Label name = new Label(curr.getName());
	name.setFont(new Font("Impact",35));
	name.setTextFill(Color.WHITE);
	String type="";
	if (curr instanceof Hero) {
		type = "Hero";
	}
	if (curr instanceof AntiHero) {
		type = "AntiHero";
	}
	if (curr instanceof Villain) {
		type = "Villain";
	}
	Label typ = new Label(type);
	typ.setFont(new Font("Impact",15));
	typ.setTextFill(Color.WHITE);
	HBox hp = new HBox ();
	Label hpt = new Label("CurrentHp: "+curr.getCurrentHP());
	hpt.setFont(new Font("Impact",15));
	hpt.setTextFill(Color.WHITE);
	ProgressBar hpbar = new ProgressBar(((double)curr.getCurrentHP()/(double)curr.getMaxHP()));
	hp.getChildren().addAll(hpt,hpbar);
	HBox mana = new HBox ();
	Label manat = new Label("Mana: "+curr.getMana());
	manat.setFont(new Font("Impact",15));
	manat.setTextFill(Color.WHITE);
	ProgressBar manabar = new ProgressBar(((double)curr.getCurrentHP()/(double)curr.getMaxHP()));
	mana.getChildren().addAll(manat,manabar);
	Label acp = new Label("ActionPoints: "+curr.getCurrentActionPoints());
	acp.setFont(new Font("Impact",15));
	acp.setTextFill(Color.WHITE);
	Label abili = new Label(curr.getAbilities().toString());
	abili.setFont(new Font("Impact",15));
	abili.setTextFill(Color.WHITE);
	Label eff = new Label(curr.getAppliedEffects().toString());
	eff.setFont(new Font("Impact",15));
	eff.setTextFill(Color.WHITE);
	Label atd = new Label("AttackDamage: "+curr.getAttackDamage());
	atd.setFont(new Font("Impact",15));
	atd.setTextFill(Color.WHITE);
	Label atr = new Label("AttackRange: "+curr.getAttackRange());
	atr.setFont(new Font("Impact",15));
	atr.setTextFill(Color.WHITE);
	det.getChildren().addAll(name,leader,typ,hp,mana,acp,abili,eff,atd,atr);
	
	
	
	return det;
}


public void updateboard(Game game2,GridPane grid) {
	
	for (int i = 0; i < game2.getBoardheight(); i++) {
        for (int j = 0; j < game2.getBoardwidth(); j++) {

            Rectangle tile = new Rectangle(120, 120);
            
            tile.setFill(Color.NAVY);
            tile.setStroke(Color.WHEAT);
           ImageView im = new ImageView();
         //   Text text = new Text();
            //text.setFont(Font.font(15));
            if (game2.getBoard()[i][j]!=null) {
            	if(game2.getBoard()[i][j] instanceof Champion) {
            		Champion c = (Champion) game2.getBoard()[i][j];
            		Tooltip t = new Tooltip();
            		String s = "";
            		if(game2.getFirstPlayer().getTeam().contains(c)) {
            			 s = game2.getFirstPlayer().getName()+"'s team";
            			
            		}
            		else
            			 s = game2.getSecondPlayer().getName()+"'s team";
            		String n = "";
            		if(game2.getFirstPlayer().getLeader().equals(c)) {
           			    n= game2.getFirstPlayer().getName()+"'s leader" ;
           			
           		}
           		    else if (game2.getSecondPlayer().getLeader().equals(c)) {
           		            n= game2.getSecondPlayer().getName()+"'s leader" ;
           			
           		}
           		    else {
           		    	n= "not leader";
           		    }
           			
        			
            		t.setText(c.getName()+"\n"+"Current HP: "+c.getCurrentHP()+""+"\n"+s+"\n"+n);
            		
            		   im =  champimage2(c.getName());
            		   im.setFitWidth(110);
            			im.setFitHeight(110);
            			im.setPreserveRatio(true);
            			im.setSmooth(true);
            			im.setCache(true);
            			Tooltip.install(im, t);
            			
            		//text .setText(c.getName()) ;
            	}
            	else {
            		//text.setText("Cover"+"\nHp: "+((Cover) game2.getBoard()[i][j]).getCurrentHP());
            		Cover co= (Cover)game2.getBoard()[i][j];
            		Tooltip t = new Tooltip();
            		t.setText("Current HP: "+co.getCurrentHP()+"");
            		
            		
            		im.setImage(new Image("cover.png"));
            		  im.setFitWidth(110);
          			im.setFitHeight(110);
          			im.setPreserveRatio(true);
          			im.setSmooth(true);
          			im.setCache(true);
          			Tooltip.install(im, t);
            	}
            }
            
            grid.add(new StackPane(tile, im), j, i);
        }
    }
	//grid.setHalignment(grid, HPos.CENTER);
	//HBox.setMargin(grid, new Insets(30,0,30,0));
	
}

public HBox updateabil (Game game2) {
	HBox abilitiesbox = new HBox();
	MenuButton ability = new MenuButton("Cast Ability"); 
	ability.setFont(new Font("Impact",20));
	abilitiesbox.getChildren().add(ability);
	for ( Ability a : game2.getCurrentChampion().getAbilities()) {
		MenuItem m = new MenuItem(a.getName());
		
		ability.getItems().add(m);
		champabil.clear();
		champabil.add(m);
		m.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (a.getCastArea()==AreaOfEffect.SELFTARGET ||a.getCastArea()==AreaOfEffect.SURROUND  ||a.getCastArea()==AreaOfEffect.TEAMTARGET  ) {
					try {
						game2.castAbility(a);
					} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
						error.show();
					}
					updateboard(game2, grid);
					champdet.getChildren().clear();
					champdet.getChildren().add(currentchampdets(game2));
				
					abilitiesdet.getChildren().clear();
					abilitiesdet.getChildren().add(abilitiesdetu(game2));
					checkgame(game2);
					bottomchamp.getChildren().clear();
					bottomchamp.getChildren().add(updatechampics(game2));
				  	
				}
				if (a.getCastArea()==AreaOfEffect.SINGLETARGET) {
					MenuButton singab = new MenuButton("Choose target",null);
					singab.setFont(new Font("Impact",15));
					abilitiesbox.getChildren().addAll(singab);
					for (Object[] oa : game2.getBoard()) {
						for(Object o : oa) {
						if (o instanceof Cover) {
							Cover cov = (Cover) o;
							MenuItem cover = new MenuItem("Cover"+cov.getLocation());
							singab.getItems().addAll(cover);
							cover.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									try {
										game2.castAbility(a, cov.getLocation().x, cov.getLocation().y);
									} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
											| CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
										error.show();
									}
									updateboard(game2, grid);
									champdet.getChildren().clear();
									champdet.getChildren().add(currentchampdets(game2));
								
									abilitiesdet.getChildren().clear();
									abilitiesdet.getChildren().add(abilitiesdetu(game2));
									checkgame(game2);
									bottomchamp.getChildren().clear();
									bottomchamp.getChildren().add(updatechampics(game2));
									
								}
								
							});
						}
						else {
							if(o!=null ) {
								Champion c = (Champion) o;
								if(c!=game2.getCurrentChampion()) {
									MenuItem champ = new MenuItem(c.getName());
									singab.getItems().add(champ);
									champ.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent event) {
											try {
												game2.castAbility(a, c.getLocation().x, c.getLocation().y);
											} catch (NotEnoughResourcesException | AbilityUseException
													| InvalidTargetException | CloneNotSupportedException e) {
												// TODO Auto-generated catch block
												Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
												error.show();
											}
											updateboard(game2, grid);
											champdet.getChildren().clear();
											champdet.getChildren().add(currentchampdets(game2));
										
											abilitiesdet.getChildren().clear();
											abilitiesdet.getChildren().add(abilitiesdetu(game2));
											checkgame(game2);
											bottomchamp.getChildren().clear();
											bottomchamp.getChildren().add(updatechampics(game2));
										}
										
									});
								}
							}
						}
						
						
						}	
					}
					
				}
				if (a.getCastArea()==AreaOfEffect.DIRECTIONAL) {
					MenuItem downab = new MenuItem("Down");
					MenuItem upab = new MenuItem("Up");
					MenuItem leftab = new MenuItem("Left");
					MenuItem rightab = new MenuItem("Right");
					MenuButton direction = new MenuButton("Choose direction for ability",null,downab,upab,leftab,rightab);
					abilitiesbox.getChildren().add(direction);
					
					downab.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
								try {
									game2.castAbility(a, Direction.UP);
								} catch (NotEnoughResourcesException | AbilityUseException
										| CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
									error.show();
								}
								updateboard(game2, grid);
								champdet.getChildren().clear();
								champdet.getChildren().add(currentchampdets(game2));
							
								abilitiesdet.getChildren().clear();
								abilitiesdet.getChildren().add(abilitiesdetu(game2));
								checkgame(game2);
								bottomchamp.getChildren().clear();
								bottomchamp.getChildren().add(updatechampics(game2));
						}
						
					});
					upab.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							try {
								game2.castAbility(a, Direction.DOWN);
							} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
								error.show();
							}
							updateboard(game2, grid);
							champdet.getChildren().clear();
							champdet.getChildren().add(currentchampdets(game2));
						
							abilitiesdet.getChildren().clear();
							abilitiesdet.getChildren().add(abilitiesdetu(game2));
							checkgame(game2);
							bottomchamp.getChildren().clear();
							bottomchamp.getChildren().add(updatechampics(game2));
					
						}	
						
					});
					leftab.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							try {
								game2.castAbility(a, Direction.LEFT);
							} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
								error.show();
							}
							updateboard(game2, grid);
							champdet.getChildren().clear();
							champdet.getChildren().add(currentchampdets(game2));
						
							abilitiesdet.getChildren().clear();
							abilitiesdet.getChildren().add(abilitiesdetu(game2));
							checkgame(game2);
							bottomchamp.getChildren().clear();
							bottomchamp.getChildren().add(updatechampics(game2));
						}	
						
						
					});
					rightab.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							try {
								game2.castAbility(a, Direction.RIGHT);
							} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								Alert error = new Alert(Alert.AlertType.WARNING, e.getMessage());
								error.show();
							}
							updateboard(game2, grid);
							champdet.getChildren().clear();
							champdet.getChildren().add(currentchampdets(game2));
						
							abilitiesdet.getChildren().clear();
							abilitiesdet.getChildren().add(abilitiesdetu(game2));
							checkgame(game2);
							bottomchamp.getChildren().clear();
							bottomchamp.getChildren().add(updatechampics(game2));
						}	
						
					});
					
				}
				
				
			}
			
		});
	}
	
	return abilitiesbox;
}
public HBox abilitiesdetu (Game game) {
	HBox result = new HBox ();
	result.setSpacing(20);
	for(Ability a: game.getCurrentChampion().getAbilities() ) {
		VBox ab = new VBox ();
	
		Label l = new Label (a.toString2());
		l.setFont(new Font("Impact",15));
		l.setTextFill(Color.WHITE);
		ab.getChildren().add(l);
		
		
		
		 result.getChildren().add(ab);	
		 
	}
	return result;
}


public void checkgame(Game game) {
//	HBox h = new HBox();
	Label win = new Label();
	Label lose = new Label();
	win.setFont(new Font("Impact",130));
	win.setTextFill(Color.WHITE);
	lose.setFont(new Font("Impact",130));
	lose.setTextFill(Color.WHITE);
	HBox winner = new HBox();
	HBox loser = new HBox();
	if((game.checkGameOver()==null)){
		return;
	}
	if (game.checkGameOver().equals(game.getFirstPlayer())) {
	//	l.setText(game.getFirstPlayer().getName());
		String s = "Congratulations!\n";
		 s += game.getFirstPlayer().getName();
		 s +=" you won!";
		ButtonType ok = new ButtonType("Exit", ButtonData.OK_DONE);
		Alert error = new Alert(Alert.AlertType.NONE, s);
		error.getDialogPane().getButtonTypes().add(ok);
		error.show();
		win.setText("The Winner is "+game.getFirstPlayer().getName());
		win.setAlignment(Pos.CENTER);
		winner.getChildren().add(win);
		lose.setText("The Loser is "+game.getSecondPlayer().getName());
		lose.setAlignment(Pos.CENTER);
		loser.getChildren().add(lose);
		window.getChildren().clear();
		window.getChildren().addAll(winner,loser);
		//Winner w = new Winner(this.stage,s);
	}
else if(game.checkGameOver().equals(game.getSecondPlayer())){
//		//l.setText(game.getFirstPlayer().getName());
	String s = "Congratulations!\n";
	 s += game.getSecondPlayer().getName();
	 s +=" you won!";
	
	ButtonType ok = new ButtonType("Exit", ButtonData.OK_DONE);
	Alert error = new Alert(Alert.AlertType.NONE, s);
	error.getDialogPane().getButtonTypes().add(ok);
	error.show();
	win.setText("The Winner is "+game.getSecondPlayer().getName());
	winner.getChildren().add(win);
	lose.setText("The Loser is "+game.getFirstPlayer().getName());
	loser.getChildren().add(lose);
	window.getChildren().clear();
	window.getChildren().addAll(winner,loser);
		//Winner w = new Winner(stage,s);
	}
	
	
}


public ImageView champimage(String s) {
	ImageView image = new ImageView();
	switch (s) {
	
	case "Captain America" : image.setImage (new Image("cap.png"));break;	

	case "Deadpool" :  image.setImage (new Image("deadpool.png"));	break;
	case "Dr Strange" : image.setImage (new Image("drstrange.png"));break;
	case "Electro" : image.setImage (new Image("electro.png"));	break;
	case "Ghost Rider" : image.setImage ( new Image("ghostrider.png"));	break;
	case "Hela" : image.setImage ( new Image("hela.png"));	break;
	case "Hulk" :  image.setImage ( new Image("hulk.png"));	break;
	case "Iceman" : image.setImage ( new Image("iceman.png"));	break;
	case "Ironman" :  image.setImage (new Image("ironman.png"));break;
	case "Loki" :  image.setImage (new Image("loki.png"));	break;
	case "Quicksilver" :  image.setImage (new Image("quicksilver.png"));break;
	case "Spiderman" : image.setImage (new Image("spiderman.png"));	break;
	case "Thor" : image.setImage (new Image("thor.png"));	break;
	case "Venom" : image.setImage (new Image("venom.png"));	break;
	case "Yellow Jacket" :  image.setImage ( new Image("yellowjacket.png"));break;
	default:image.setImage ( new Image("loki.png"));	break;
	
	}
	return image;
}
public ImageView champimage2(String s) {
	ImageView image = new ImageView();
	switch (s) {
	
	case "Captain America" : image.setImage (new Image("captain.png"));break;	

	case "Deadpool" :  image.setImage (new Image("deadpool2.png"));	break;
	case "Dr Strange" : image.setImage (new Image("drstrange2.png"));break;
	case "Electro" : image.setImage (new Image("electro2.png"));	break;
	case "Ghost Rider" : image.setImage ( new Image("ghostrider2.png"));	break;
	case "Hela" : image.setImage ( new Image("hela2.png"));	break;
	case "Hulk" :  image.setImage ( new Image("hulk2.png"));	break;
	case "Iceman" : image.setImage ( new Image("iceman2.png"));	break;
	case "Ironman" :  image.setImage (new Image("ironman2.png"));break;
	case "Loki" :  image.setImage (new Image("loki2.png"));	break;
	case "Quicksilver" :  image.setImage (new Image("quicksilver2.png"));break;
	case "Spiderman" : image.setImage (new Image("spider2.png"));	break;
	case "Thor" : image.setImage (new Image("thor2.png"));	break;
	case "Venom" : image.setImage (new Image("venom2.png"));	break;
	case "Yellow Jacket" :  image.setImage ( new Image("yellow2.png"));break;
	default:image.setImage ( new Image("loki2.png"));	break;
	
	}
	return image;
}
//public Champion prep(Game game2) {
//	PriorityQueue turnOrder = new PriorityQueue(game2.getFirstPlayer().getTeam().size()+game2.getSecondPlayer().getTeam().size());
//	if (turnOrder.isEmpty())
//		prepareChampionTurns(game2,turnOrder);
//	while (!turnOrder.isEmpty() && hasEffect((Champion) turnOrder.peekMin(), "Stun")) {
//		Champion current = (Champion) turnOrder.peekMin();
//	//	updateTimers(current);
//		turnOrder.remove();
//	}
//	Champion current = (Champion) turnOrder.peekMin();
//	//updateTimers(current);
//	//current.setCurrentActionPoints(current.getMaxActionPointsPerTurn());
//	Champion next;
//	//PriorityQueue q = new PriorityQueue(game2.getFirstPlayer().getTeam().size()+game2.getSecondPlayer().getTeam().size());
//	if(turnOrder.size()>=2) {
//		turnOrder.remove();
//		next = (Champion) turnOrder.remove();
//	}
//	else {
//		turnOrder = new PriorityQueue(game2.getFirstPlayer().getTeam().size()+game2.getSecondPlayer().getTeam().size());
//		for(Champion c1: game2.getFirstPlayer().getTeam()) {
//			turnOrder.insert(c1);
//		}
//		for(Champion c2: game2.getSecondPlayer().getTeam()) {
//			turnOrder.insert(c2);
//		}
//		while(hasEffect(turnOrder.peekMin(),"Stun")) {
//			turnOrder.remove();
//		}
//		next = (Champion) turnOrder.remove();
//		
//	}
//	return next;
//}
public boolean hasEffect(Comparable comparable, String s) {
	Champion c = (Champion) comparable;
	for (Effect e : c.getAppliedEffects()) {
		if (e.getName().equals(s))
			return true;
	}
	return false;
}
public void prepareChampionTurns(Game game,PriorityQueue turnOrder) {
	for (Champion c : game.getFirstPlayer().getTeam())
		turnOrder.insert(c);
	for (Champion c : game.getSecondPlayer().getTeam())
		turnOrder.insert(c);

}


}
