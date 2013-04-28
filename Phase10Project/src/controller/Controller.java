package controller;

import java.awt.event.ActionEvent;
import java.util.*;

import strategy.*;
import view.*;

import model.*;

public class Controller {
	Deck drawPile = new Deck(Deck.deckType.DrawPile);
	Deck discardPile = new Deck(Deck.deckType.DiscardPile);
	

	
	public ArrayList<Player> setPlayerOrder(Player p1, Player p2, Player p3, Player p4){
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		return playerList;
	}
  
  public void dealCards(ArrayList<Player> playerList) {
	  drawPile.createDeck();
	  for(int i = 0; i < 10; i++){
		  for(int player = 0; player < playerList.size(); player++){
			  playerList.get(player).hand.draw(drawPile);
		  } 
	  }
  }

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
  


  
  public void scorePlayers(){
	  
  }

  public void phasetracker(){
	  
  }
  
  public void emptyHand(ArrayList<Player> playerList){
	  Player currPlayer;
	  boolean empty = false;
	  for(int player = 0; player < playerList.size(); player++){
		  currPlayer = playerList.get(player);
		  System.out.println(currPlayer.getName());
		  for (int i = 0; i < currPlayer.hand.size(); i++){
			  if (currPlayer.hand.size() == 0){
				  empty = true;
			  }else{ 
				  empty = false;
			  }
		  }
	  }
  }
  

  
  public void exitGame(){
//				if (Gui.ActionEvent.event.getSource() == Gui.exitMenu){
//					Gui.frame.setVisible(false);
//					Gui.frame.dispose();
//					System.exit(0);
//				
//			}
  }
  

  public void checkRound() {
	  
  }

  public void doTurn() {
	  
  }
  
  public void displayScore(){
	  
  }

  public void exitRound(){
	  
  }
  
  
  public void resetDrawPile(){
	  
  }
  
  
  public void checkHit(){
	  
  }
  
  public void finishTurn(){
	  
  }
  

 //can't be implemented with out first finishing other parts.
  
  
  public void draw(ArrayList<Player> playerList){
	  Player currPlayer;
	  for(int player = 0; player < playerList.size(); player++){
		  currPlayer = playerList.get(player);
		  System.out.println(currPlayer.getName());
		  for (int i = 0; i < currPlayer.hand.size(); i++){
		  }
	  }
	  
  }
  
  public void hit(){
	  
  }
  
  public void setupBoard(){
	  
  }
  
  public void discard(ArrayList<Player> playerList){
	  Player currPlayer;
	  for(int player = 0; player < playerList.size(); player++){
		  currPlayer = playerList.get(player);
		  System.out.println(currPlayer.getName());
		  for (int i = 0; i < currPlayer.hand.size(); i++){
		  }
	  }
	  
  }

  public void setStrategy(){
	  
  }
  
 public void phaseOut(){
	  
  }

}


