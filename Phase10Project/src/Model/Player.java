package Model;

import java.util.*;

import Strategy.*;

public class Player {
	
  private String name;
  public Strategy strategy;
  public  Hand hand;
  private int phaseNumber;
  private int score;
  public boolean phasedOut;
  
  // the phase info
  
  public int numSets;
  public int colorSets;
  public int setSize;
  public int secondSetSize;
  public int runSize;
  Phase phase = new Phase();
  
  public Player(){
	  
  }
  
  public Player(String name){
	  this.name = name;
	  this.phaseNumber = 1;
	  this.score = 0;
	  this.hand = new Hand();
	  this.phasedOut = false;
	  
  }
  
  public Player(String name, Strategy strategy){
	  this.name = name;
	  this.strategy = strategy;
	  this.phaseNumber = 1;
	  this.score = 0;
	  this.hand = new Hand();
	  this.phasedOut = false;
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
	
	public void checkPhase(){
		// Phase 8 with color sets. 
		if(this.numSets == 0 & this.setSize == 7 & this.colorSets == 1){
			System.out.println("This is a Phase 8 with color sets. ");
		} // this is 2 sets with different sizes
		else if (this.numSets == 2 & this.setSize > 0 & this.secondSetSize > 0){
			System.out.println("this is 2 sets with different sizes ");
		}// this is a set and a run
		else if (this.numSets == 1 & setSize > 0 & runSize > 0){
			System.out.println("this is a set and a run");
		}// this is 2 sets of the same size
		else if (this.numSets == 2 & secondSetSize == 0){
			System.out.println("this is 2 sets of the same size");
		}// this is a run
		else if(this.numSets == 0 & this.runSize > 0){
			System.out.println("this is a run");
		}
	}
	
	public void phaseOut(){
		  
	}
	  
	public void hit(){
		  
	}
	  
	public void doTurn(){
		  
	}
	
	
	
	public void getPhaseInfo(){
		phase.checkPhase(this.getPhaseNumber(), this);
	}
  
  
}