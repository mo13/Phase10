package Model;

import java.util.*;

import Strategy.*;

public class Player {

  private String name;

  public Strategy strategy;

  public  Hand hand;

  private int phaseNumber;

  private int score;

  public  PhaseCollections<Card> currPhase;


  public Player(String name){
	  this.name = name;
	  this.strategy = strategy;
	  this.phaseNumber = 1;
	  this.score = 0;
	  this.hand = new Hand();
	  
  }
  
  	public Strategy getStrategy() {
  		return strategy;
  	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public int getPhaseNumber() {
		return phaseNumber;
	}
	public void setPhaseNumber(int phaseNumber) {
		this.phaseNumber = phaseNumber;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
		
	public String getName() {
		return name;
	}
	
	public void drawCard(Deck deck) {
		Card tempCard = deck.draw();
		this.hand.add(tempCard);
	}
	
	public void discardCard() {
		
	}
	  
	public void phaseOut(){
		  
	}
	  
	public void hit(){
		  
	}
	  
	public void doTurn(){
		  
	}
	
	public void checkHand(){
		
	}
  
  
}