package controller;

import java.awt.event.ActionEvent;
import java.util.*;

import strategy.Strategy;
import view.*;

import model.*;

public class Controller {
	
	
	GameModel model = new GameModel();
	GameObserver view;
	public Deck drawPile = new Deck(Deck.deckType.DrawPile);
	public Deck discardPile = new Deck(Deck.deckType.DiscardPile);
	Card topDiscard;
	public ArrayList<Player> playerList = new ArrayList<Player>();
	Player chief = new Player("Master Chief");
	Player johnson = new Player("Sgt. Johnson");
	Player arbiter = new Player("The Arbiter");
	Player cortona = new Player("Cortona");
	
	
	public Controller(GameModel model, GameObserver view) {
		this.model = model;
		this.view = view;
		view.registerController(this);
		view.createUI();
	}
	
	public void dealCards() {
		  if(playerList.isEmpty()){
			  this.setPlayerOrder();
		  }
		  drawPile.createDeck();
		  for(int i = 0; i < 10; i++){
			  for(int player = 0; player < playerList.size(); player++){
				  playerList.get(player).hand.draw(drawPile);
			  } 
		  }
		  topDiscard = drawPile.remove((int)(Math.random()* drawPile.size()-1));
	  }
	  
	public Card getTopDiscard(){
		  return this.topDiscard;
	  }
		
	public void setPlayerOrder(){
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		playerList.add(cortona);
	}
	
	public String showPlayerOrder(){
		String str = "";
		for(int i = 0; i < playerList.size(); i++){
			str+= "Player " + (i+1) + " is ";
			str+= playerList.get(i).getName();
			str+= "\n";
		}
		return str;
		
	}

	public void drawCard(Integer i){
		  if(drawPile.isEmpty()){
			  System.out.println(topDiscard	);
			  drawPile = discardPile;
		  }else{
			  if(playerList.get(i).draw(drawPile, topDiscard)){  
				  topDiscard = discardPile.remove(discardPile.size()-1);  
			  }
		  }
	  }
/*
 * score Round is not done yet.
 */
  public void scoreRound(ArrayList<Player> playerList) {
	  int tempScore  = 0;
	  Player currPlayer;
	  for(int player = 0; player < playerList.size(); player++){
		  currPlayer = playerList.get(player);
		  System.out.println(currPlayer.getName());
		  for (int i = 0; i < currPlayer.hand.size(); i++){
			  if (currPlayer.hand.get(i).getType() == Card.type.Normal){
					if (currPlayer.hand.get(i).getNumber() < 10){
						System.out.println(currPlayer.hand.get(i).toString());
						System.out.println("adding 5");
						tempScore += 5; 				//add 5 points
					} else { 							//(playerList.get(player).hand.get(i).getType()  == Card.type.Normal){
						System.out.println(currPlayer.hand.get(i).toString());
						System.out.println("adding 10");
						tempScore += 10;				//add 10 points
					}
			  }	else if (currPlayer.hand.get(i).getType()  == Card.type.Wild){
				  System.out.println(currPlayer.hand.get(i).toString());
				  System.out.println("adding 25");
				  	tempScore += 25;					//	add 25 points
						
			  } else if (currPlayer.hand.get(i).getType()  == Card.type.Skip){ 
				  System.out.println(currPlayer.hand.get(i).toString());
				  System.out.println("adding 15");
				  	tempScore += 15;					//add 15 points
					}
				}
		  int originalScore =  currPlayer.getScore();
		  playerList.get(player).setScore(originalScore + tempScore);
		  }
	  }
     
  public void showOrder(){
//	  setPlayerOrder();
  }
    
  public void checkRound() {
	  
  }

  public void doTurn() {
	  
  }
  
  public void displayScore(){
	  
  }

  public void exitRound(){
	  
  }
  

  public void checkHit(){
	  
  }
  
  public void finishTurn(){
	  
  }
  
  public void hit(){
	  
  }

  public void discard(){
	  
  }

  public void setStrategy(Integer i, Strategy.strategyType strategy){
	  playerList.get(i).setStrategy(strategy);
  }
  
 public void phaseOut(){
	  
  }

}

