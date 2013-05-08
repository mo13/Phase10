package controller;



import static org.junit.Assert.assertTrue;

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
	public boolean roundDone;
	
	
	
	public Controller(GameModel model, GameObserver view) {
		this.model = model;
		this.view = view;
		view.registerController(this);
		view.createUI();
	}
	
	
	
	public void dealCards() {
		roundDone = false;
		  if(playerList.isEmpty()){
			  this.setPlayerOrder();
		  }
		  drawPile.createDeck();
		  for(int i = 0; i < 10; i++){
			  for(int player = 0; player < playerList.size(); player++){
				  playerList.get(player).hand.draw(drawPile);
				  playerList.get(player).displayPhasedOut = true;
			  } 
		  }
		  topDiscard = drawPile.remove((int)(Math.random()* drawPile.size()-1));
		  discardPile.add(topDiscard);
		  
	  }
	  
	public Card getTopDiscard(){
		  return this.topDiscard;
	  }
		
	public void setPlayerOrder(){
		if(playerList.isEmpty()){
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		playerList.add(cortona);
		}
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
			  drawPile = discardPile;
		  }else{
			  if(playerList.get(i).draw(drawPile, topDiscard)){  // the player drew a card from the discard pile
				  System.out.println("the player took from the discard pile");
				  discardPile.remove(discardPile.indexOf(topDiscard));
				 try{
					 topDiscard = discardPile.get(discardPile.size()-1);
				 } catch (ArrayIndexOutOfBoundsException e){
					 topDiscard = null;
					 System.out.println("the player took the topdiscard the new discard is is now empty.");
				 }	
				  
			  }
		  }
		  playerList.get(i).hand.orderHand();
	  }
/*
 * score Round is not done yet.
 */
  public void scoreRound() {
	  if(roundDone){
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
		  tempScore = 0;
		  }
	  }
  }
  
  public String displayScore(){
	  String str = "";
	  for(Player p: playerList){
		  str+= p.getName() + " score is " + p.getScore() + " and is now on phase " + p.getPhaseNumber() + "\n";
	  }
	  return str;
  }
     
  public void setStrategy(Integer i, Strategy.strategyType strategy){
	  playerList.get(i).setStrategy(strategy);
  }
  public void discard(Integer i ){
	 topDiscard = playerList.get(i).discard(); 
	 discardPile.add(topDiscard);
	 playerList.get(i).hand.orderHand();
	 playerList.get(i).emptyPossiblePhaseOutCard();
  }
  
  public void doTurn(Integer i) {
	  playerList.get(i).hand.orderHand();
	  playerList.get(i).getPhaseInfo();
	  drawCard(i);
	  System.out.println("Drew a card");
	  if(!playerList.get(i).getPhasedOut()){
		  phaseOut(i);
	  }
	  hit(i);
	  if(playerList.get(i).hand.size() != 0){
		  discard(i);
	  }
	  if(playerList.get(i).hand.size()== 0){
			roundDone = true;
		}
	  
  }
  
  public Boolean DoAWholeRound(){
	  int i = 0;
	  boolean roundIsDone = false;
	  while(i < 5){
		  for(int j =0; j< playerList.size(); j++){
			 doTurn(j); 
			 if(roundDone){
				 roundIsDone = true;
				 break;
			 }
		  }
		  if(roundIsDone){
			  i =5;
			  break;
		  }
		  i++;
	  }
	  return roundIsDone;
	  
  }
  
  
  public void doGame(){
	  Boolean gameDone = false;
	  setPlayerOrder();
	  setStrategy(0,Strategy.strategyType.newLowestScore);
	  setStrategy(1,Strategy.strategyType.newRecklessPlayer);
	  setStrategy(2,Strategy.strategyType.newRed);
	  setStrategy(3,Strategy.strategyType.randomPlayer);	
	  while(!gameDone){
			dealCards();
			System.out.println("Entering a new round");
			boolean roundIsDone = false;
			while(!roundIsDone){
				
				roundIsDone = DoAWholeRound();
			}
			System.out.println("Done with that round");
			scoreRound();
			for(Player p : playerList){
				if(p.getPhaseNumber() == 11){
					gameDone = true;
				}
			}
			resetPlayer();
		}
	  System.out.println(displayScore());
	}
  
  public void resetPlayer(){
	  emptyHands();
	  for(Player p: playerList){
		  p.emptyPhasedOutSets();
		  
		  p.setPhasedOut(false);
		  p.setCanPhaseOut(false);
		  p.setDisplayPhasedOut(false);
	  }
  }
  
  public void emptyHands(){
	  for(Player p : playerList){
		  for(int i = 0; i<p.hand.size(); i++){
			  p.hand.remove(i);
			  i--;
		  }
		  
	  }
  }
  
  public void hit(Integer i){
	  playerList.get(i).hit(playerList);
  }

  
 public Boolean phaseOut(Integer i){
	playerList.get(i).hand.orderHand();
	playerList.get(i).getPhaseInfo();
	
	if(playerList.get(i).checkPhase() & !playerList.get(i).getPhasedOut()){
		System.out.println("The player can phase out");
		playerList.get(i).phaseOut();
		int previousPhase = playerList.get(i).getPhaseNumber();
		playerList.get(i).setPhaseNumber(previousPhase + 1);
		
		return true;
	} else{
		System.out.println("The player can't phase out yet");
		return false;
	}
	
	  
  }

}

