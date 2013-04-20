package Controller;
import Model.*;
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
			  
		  }
		 
	  }
	  
	  
  }

  public void scoreRound() {
	  
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
	  
  }
  
  public void showOrder(){
	  
  }
  
  public void setupBoard(){
	  
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
  
  public void discard(){
	  
  }
  
  public void finishTurn(){
	  
  }

  public void setStrategy(){
	  
  }

}


