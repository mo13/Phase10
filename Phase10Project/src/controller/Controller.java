package controller;

import java.awt.event.ActionEvent;
import java.util.*;

import strategy.*;
import view.*;

import model.*;

public class Controller {
	Deck drawPile = new Deck(Deck.deckType.DrawPile);
	Deck discardPile = new Deck(Deck.deckType.DiscardPile);
	
	Player player1 = new Player("Chief");
	Player player2 = new Player("Cortona");
	Player player3 = new Player("Johnson");
	Player player4 = new Player("Arbiter");
	
	ArrayList<Player> playerList;
	
	public void setPlayerOrder(){
		playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		playerList.add(player4);
		
	}
  
  public void dealCards() {
	  drawPile.createDeck();
	  for(int i = 0; i < 10; i++){
		  for(int player = 0; player < playerList.size(); player++){
			  playerList.get(player).hand.draw(drawPile);
		  } 
	  }
  }

  public void scoreRound() {
	  for(int player = 0; player < playerList.size(); player++){

		  for (int i = 0; i < playerList.get(player).hand.size(); i++){
			  if (playerList.get(player).hand.get(i).getType() == Card.type.Normal){
					if (Card.number < 10){
	//add 5 points
					}
					}else if (playerList.get(player).hand.get(i).getType()  == Card.type.Normal){
						if (Card.number > 10){
	//add 10 points
						}
						else if (playerList.get(player).hand.get(i).getType()  == Card.type.Wild){
	//add 25 points
						
					}		else if (playerList.get(player).hand.get(i).getType()  == Card.type.Skip){ 
	//add 15 points
					}
				}
		  }
	  }
  }

  public void checkRound() {
	  
  }

  public void doTurn() {
	  
  }
  
  public void scorePlayers(){
	  
  }

  public void phasetracker(){
	  
  }
  
  public void displayScore(){
	  
  }

  public void exitRound(){
	  
  }
  
  public void exitGame(){
				if (Gui.ActionEvent.event.getSource() == Gui.exitMenu){
					Gui.frame.setVisible(false);
					Gui.frame.dispose();
					System.exit(0);
				
			}
  }
  
  public void showOrder(){
	  setPlayerOrder();
  }
  
  public void resetDrawPile(){
	  
  }
  
  
  public void checkPhase(){
//	  Model.checkPhase(0, player1);
//	  Model.checkPhase(0, player2);
//	  Model.checkPhase(0, player3);
//	  Model.checkPhase(0, player4);
  }
 
  
  public void checkHit(){
	  
  }
  
  public void finishTurn(){
	  
  }
  
  
  
  
 //can't be implemented with out first finishing other parts.
  
  
 public void draw(){
	  
  }
  
  public void hit(){
	  
  }
  
  public void setupBoard(){
	  
  }
  
  public void discard(){
	  
  }

  public void setStrategy(){
	  
  }
  
 public void phaseOut(){
	  
  }

}


