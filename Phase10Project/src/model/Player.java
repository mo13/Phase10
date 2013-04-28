package model;

import java.util.*;

import model.Card.cardColor;

import strategy.*;
import strategy.Strategy.strategyType;


public class Player {
	
  private String name;
  public Strategy.strategyType currentStrategy;
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
	  this.currentStrategy = null;
	  
  }
  
  public Player(String name, strategyType strategy){
	  this.name = name;
	  this.currentStrategy = strategy;
	  this.phaseNumber = 1;
	  this.score = 0;
	  this.hand = new Hand();
	  this.phasedOut = false;
  }
  
  	public Strategy.strategyType getStrategy() {
  		return currentStrategy;
  	}
	public void setStrategy(strategyType strategy) {
		currentStrategy = strategy;
		
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
	
//	numSets;
//	colorSets;
//	setSize;
//	secondSetSize; 
//	runSize;
	public boolean checkPhase(){
		// check a color set
		if (colorSets != 0 & numSets == 0 & runSize == 0 & setSize == 7){
			hand.orderHand();			
			ArrayList<Integer> tempColor = hand.checkColor();			
			for(Integer i: tempColor){
				if (i == setSize){
					return true;
				}
			}
		}
		// check 2 sets with different sizes
		else if (numSets == 2 & setSize != 0 & secondSetSize != 0 & runSize == 0){
			hand.orderHand();
			ArrayList<Integer> tempSet1 = hand.checkSet(1, setSize);
			ArrayList<Card> tempHand = new ArrayList<Card>();
			for(int i = 0; i < tempSet1.size(); i++){
				for(int j = 0; j < hand.size(); j++){
					if (tempSet1.get(i) == hand.get(j).getNumber()){
						tempHand.add(hand.get(j));
					} 
					else if (tempSet1.get(i) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							tempHand.add(hand.get(j));
						}
					}
				}
			}
			int i = 0;
			while (i < tempHand.size()){
				for(int j = 0; j< hand.size(); j++){
					if (tempHand.get(i) == hand.get(j)){
						hand.remove(j);
					}
				}
				i++;
			}
			hand.orderHand();
			ArrayList<Integer> tempSet2 = hand.checkSet(1, secondSetSize);
			for(Card c: tempHand){
				hand.add(c);
			}
			if(!tempSet1.isEmpty()& tempSet2.isEmpty()){
				return true;
			} else {
				return false;
			}
		}
		// check a set and a run
		else if (numSets == 1 & setSize != 0 &  runSize != 0){
			ArrayList<Integer> possibleSets = hand.checkSet(1,setSize);			
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(possibleSets, runSize);
			if(!possibleSets.isEmpty() & !possibleRun.isEmpty()){
				return true;
			}else{
				return false;
			}
			
		}
		// check 2 normal sets
		else if (numSets == 2 & setSize != 0 & secondSetSize == 0 & runSize == 0){
			ArrayList<Integer> possibleSets = hand.checkSet(numSets, setSize);
			int counter = 0;
			for(int i = 0; i < possibleSets.size(); i++){
				if (possibleSets.get(i) != 99){
					counter++;
				}
			}
			if (counter ==numSets){
				return true;
			}
			return false;
		}
		// check a run
		else if (numSets == 0 & setSize == 0 & runSize != 0) {
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(runSize);
			if (!possibleRun.isEmpty()){
				return true;
			} else{
				return false;
			}
		} else {
			return false;
		}
		return false;		
	}

	  
	public ArrayList<Card> phaseOut(){
		if (colorSets != 0 & numSets == 0 & runSize == 0 & setSize == 7){
			hand.orderHand();			
			ArrayList<Integer> tempColor =  hand.checkColor();	
			
			int index= -99;
			for(Integer i: tempColor){
				if (i == setSize){
					index = tempColor.indexOf(i);
				}
			}
			Card.cardColor daColor = Card.cardColor.Black;
			if(index == 0){
				daColor = Card.cardColor.Red;
			} else if (index == 1){
				daColor = Card.cardColor.Blue;
			} else if (index == 2){
				daColor = Card.cardColor.Green;
			} else if (index == 3){
				daColor = Card.cardColor.Yellow;
			}
			ArrayList<Card> done = new ArrayList<Card>();
			for(int i =0; i < hand.size(); i++){
				if(hand.get(i).getColor() == daColor){
					done.add(hand.get(i));
				}
			}
			while(done.size() < setSize){
				done.add( new Card(0, Card.cardColor.Black, Card.type.Wild));	
			}
			int i = 0;
			while (i < done.size()){
				for(int j = 0; j< hand.size(); j++){
					if (done.get(i) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (done.get(i).getType() == Card.type.Wild){
						if(hand.get(j).getType() == Card.type.Wild){
							hand.remove(j);
							break;
						}
					}
				}
				i++;
			}
					
			this.phasedOut = true;
			return done;
		}
		// check 2 sets with different sizes
		else if (numSets == 2 & setSize != 0 & secondSetSize != 0 & runSize == 0 ){
			hand.orderHand();
			ArrayList<Integer> tempSet1 = hand.checkSet(1, setSize);
			ArrayList<Card> tempHand = new ArrayList<Card>();
			for(int i = 0; i < tempSet1.size(); i++){
				for(int j = 0; j < hand.size(); j++){
					if (tempSet1.get(i) == hand.get(j).getNumber()){
						tempHand.add(hand.get(j));
					} 
					else if (tempSet1.get(i) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							tempHand.add(hand.get(j));
						}
					}
				}
			}
			int i = 0;
			while (i < tempHand.size()){
				for(int j = 0; j< hand.size(); j++){
					if (tempHand.get(i) == hand.get(j)){
						hand.remove(j);
					}
				}
				i++;
			}
			hand.orderHand();
			ArrayList<Integer> tempSet2 = hand.checkSet(1, secondSetSize);
			
			
			for(int z = 0; z < tempSet2.size(); z++){
				for(int j = 0; j < hand.size(); j++){
					if (tempSet2.get(z) == hand.get(j).getNumber()){
						tempHand.add(hand.get(j));
					} 
					else if (tempSet2.get(z) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							tempHand.add(hand.get(j));
						}
					}
				}
			}
			this.phasedOut = true;
			return tempHand;
		}
		// check a set and a run
		else if (numSets == 1 & setSize != 0 &  runSize != 0){
			ArrayList<Integer> possibleSets = hand.checkSet(1,setSize);			
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(possibleSets, runSize);
			ArrayList<Card> done = new ArrayList<Card>();
			for(int i = 0; i < possibleSets.size(); i++){
				for(int j = 0; j < hand.size(); j++){
					if (possibleSets.get(i) == hand.get(j).getNumber()){
						done.add(hand.get(j));
					} 
					else if (possibleSets.get(i) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							done.add(hand.get(j));
						}
					}
				}
			}
			
			int i=0;
			while (i < done.size()){
				for(int j = 0; j< hand.size(); j++){
					if (done.get(i) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (done.get(i).getType() == Card.type.Wild){
						if(hand.get(j).getType() == Card.type.Wild){
							hand.remove(j);
							break;
						}
					}
				}
				i++;
			}
				for(int k = 0; k < possibleRun.size(); k++){
					for(int j = 0; j < hand.size(); j++){
						if (possibleRun.get(k) == hand.get(j).getNumber()){
							done.add(hand.get(j));
						} 
						else if (possibleRun.get(k) == 99){
							if(hand.get(j).getType() == Card.type.Wild){
								done.add(hand.get(j));
							}
						}
					}
				}
				int l = 0;
				while (l < done.size()){
					for(int j = 0; j< hand.size(); j++){
						if (done.get(l) ==(hand.get(j))){
							hand.remove(j);
							break;
						} else if (done.get(l).getType() == Card.type.Wild){
							if(hand.get(j).getType() == Card.type.Wild){
								hand.remove(j);
								break;
							}
						}
					}
					l++;
				}
			return done;
		}
			
		
		// check 2 normal sets
		else if (numSets == 2 & setSize != 0 & secondSetSize == 0 & runSize == 0){
			ArrayList<Integer> possibleSets = hand.checkSet(numSets, setSize);
			int counter = 0;
			for(int i = 0; i < possibleSets.size(); i++){
				if (possibleSets.get(i) != 99){
					counter++;
				}
			}
			ArrayList<Card> done = new ArrayList<Card>();
			for(int i = 0; i < possibleSets.size(); i++){
				for(int j = 0; j < hand.size(); j++){
					if (possibleSets.get(i) == hand.get(j).getNumber()){
						done.add(hand.get(j));
					} 
					else if (possibleSets.get(i) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							done.add(hand.get(j));
						}
					}
				}
			}
			int l = 0;
			while (l < done.size()){
				for(int j = 0; j< hand.size(); j++){
					if (done.get(l) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (done.get(l).getType() == Card.type.Wild){
						if(hand.get(j).getType() == Card.type.Wild){
							hand.remove(j);
							break;
						}
					}
				}
				l++;
			}

			return done;
		}
		// check a run
		else if (numSets == 0 & setSize == 0 & runSize != 0) {
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(runSize);
			System.out.println("possible run "+possibleRun);
			ArrayList<Card> done = new ArrayList<Card>();
			for(int k = 0; k < possibleRun.size(); k++){
				for(int j = 0; j < hand.size(); j++){
					if (possibleRun.get(k) == hand.get(j).getNumber()){
						done.add(hand.get(j));
						break;
					} 
					else if (possibleRun.get(k) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							done.add(hand.get(j));
							break;
						}
					}
				}
			}
			System.out.println(done);
			int l = 0;
			while (l < done.size()){
				for(int j = 0; j< hand.size(); j++){
					if (done.get(l) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (done.get(l).getType() == Card.type.Wild){
						if(hand.get(j).getType() == Card.type.Wild){
							hand.remove(j);
							break;
						}
					}
				}
				l++;
			}
			System.out.println("done " + done);
			
			System.out.println("hand" + hand);
			return done;
		} else {
			return null;
		}	
		  
	}
	  
	public void hit(){
		  
	}
	  
	public void doTurn(){
		  
	}
	DrunkPlayer d = new DrunkPlayer();
	Preventer p = new Preventer();
	LowestScore l = new LowestScore();
	RecklessPlayer r = new RecklessPlayer();
	public Card discard(){
		if(this.getStrategy() == Strategy.strategyType.drunkPlayer){
			d.setPlayer(this);
			Card tempCard = d.discard();
			return tempCard;
		} else if (this.getStrategy() == Strategy.strategyType.preventer){
			p.setPlayer(this);
			Card tempCard = p.discard();
			return tempCard;
		} 
//		else if (this.getStrategy() == Strategy.strategyType.lowestScore){
//			l.setPlayer(this);
//			Card tempCard = l.discard();
//			return tempCard;
//		}
		return null;
	}
	
	public void draw(Deck drawPile, Deck discardPile){
		if(this.getStrategy() == Strategy.strategyType.drunkPlayer){
			d.setPlayer(this);
			d.draw(drawPile, discardPile);
			}
		
	}
	
	public void getPhaseInfo(){
		phase.checkPhase(this.getPhaseNumber(), this);
	}
  
  
}