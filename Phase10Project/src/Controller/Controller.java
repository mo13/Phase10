package Controller;
import Model.*;
import View.*;

import java.awt.event.ActionEvent;
import java.util.*;

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
	  for(int i = 0; i < 10; i++){
		  for(int player = 0; player < playerList.size(); player++){
			  Deck.draw();
		  }
		 
	  }
	  
	  
  }

  public void scoreRound() {
	  for(int player = 0; player < playerList.size(); player++){
		  for (int i = 0; i < playerList.get(player).hand.size(); i++){
			  if (Card.getType() == Card.type.Normal){
					if (Card.number <= 12 & Card.number >= 1){
					}else
			
		  }
	  }
	  
  }

  public void checkRound() {
	  
  }

  public void doTurn() {
	  
  }
  
  public void showBoard() {
	  
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
	  
  }
  
  public void resetDrawPile(){
	  
  }
  
  public void draw(){
	  
  }
  
  public void phaseOut(){
	  
  }
  
  public void checkPhase(){
	  
  }
  
  public void hit(){
	  
  }
  
  public void checkHit(){
	  
  }
  
  public void finishTurn(){
	  
  }
  
  
  
  
  //can't be implemented with out first finishing other parts.
  
  
  public void setupBoard(){
	  
  }
  
  public void discard(){
	  
  }

  public void setStrategy(){
	  
  }

}


