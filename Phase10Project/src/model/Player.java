package model;

import java.util.*;

import model.Card.cardColor;

import strategy.*;
import strategy.Strategy.strategyType;

// I am just making a test change.
public class Player {
	
  private String name;
  public Strategy.strategyType currentStrategy;
  public  Hand hand;
  private int phaseNumber;
  private int score;
  private boolean phasedOut;

  



ArrayList<Card> phasedOutSecondSet = new ArrayList<Card>();
  ArrayList<Card> phasedOutRun = new ArrayList<Card>();
  ArrayList<Card> phasedOutColoredSet = new ArrayList<Card>();
  ArrayList<Card> phasedOutSet = new ArrayList<Card>();
  
  
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
	
	public ArrayList<Card> getPhasedOutSet() {
		return phasedOutSet;
	}

	public ArrayList<Card> getPhasedOutSecondSet() {
		return phasedOutSecondSet;
	}

	public ArrayList<Card> getPhasedOutRun() {
		return phasedOutRun;
	}

	public ArrayList<Card> getPhasedOutColoredSet() {
		return phasedOutColoredSet;
	}
	
	public boolean getPhasedOut() {
		return phasedOut;
	}
	
	public void setPhasedOut(boolean phasedOut) {
		this.phasedOut = phasedOut;
	}

//	numSets;
//	colorSets;
//	setSize;
//	secondSetSize; 
//	runSize;
	public void checkPhase(){
		// check a color set
		if (colorSets != 0 & numSets == 0 & runSize == 0 & setSize == 7){
			hand.orderHand();			
			ArrayList<Integer> tempColor = hand.checkColor();			
			for(Integer i: tempColor){
				if (i >= setSize){
					this.phasedOut = true;
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
			if(!tempSet1.isEmpty()& !tempSet2.isEmpty()){
				phasedOut = true;
			} else {
				phasedOut = false;
			}
		}
		// check a set and a run
		else if (numSets == 1 & setSize != 0 &  runSize != 0){
			ArrayList<Integer> possibleSets = hand.checkSet(1,setSize);			
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(possibleSets, runSize);
			if(!possibleSets.isEmpty() & !possibleRun.isEmpty()){
				phasedOut =  true;
			}else{
				phasedOut =  false;
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
			if (counter >=numSets){
				
				phasedOut =  true;
			}
		}
		// check a run
		else if (numSets == 0 & setSize == 0 & runSize != 0) {
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(runSize);
			if (!possibleRun.isEmpty()){
				phasedOut =  true;
			} else{
				phasedOut =  false;
			}
		} else {
			phasedOut =  false;
		}	
	}
	
	public void phaseOut(){
		if (colorSets != 0 & numSets == 0 & runSize == 0 & setSize == 7 & phasedOut){
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
			
			phasedOutColoredSet = done;
		}
		// check 2 sets with different sizes
		else if (numSets == 2 & setSize != 0 & secondSetSize != 0 & runSize == 0 & phasedOut){
			hand.orderHand();
			ArrayList<Integer> tempSet1 = hand.checkSet(1, setSize);
			
			for(int i = 0; i < tempSet1.size(); i++){
				for(int j = 0; j < hand.size(); j++){
					if (tempSet1.get(i) == hand.get(j).getNumber()){
						phasedOutSet.add(hand.get(j));
					} 
					else if (tempSet1.get(i) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							phasedOutSet.add(hand.get(j));
						}
					}
				}
			}
			int i = 0;
			while (i < phasedOutSet.size()){
				for(int j = 0; j< hand.size(); j++){
					if (phasedOutSet.get(i) == hand.get(j)){
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
						phasedOutSecondSet.add(hand.get(j));
					} 
					else if (tempSet2.get(z) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							phasedOutSecondSet.add(hand.get(j));
						}
					}
				}
			}
			this.phasedOut = true;
			
		}
		// check a set and a run
		else if (numSets == 1 & setSize != 0 &  runSize != 0 & phasedOut){
			ArrayList<Integer> possibleSets = hand.checkSet(1,setSize);			
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(possibleSets, runSize);
			
			for(int i = 0; i < possibleSets.size(); i++){
				for(int j = 0; j < hand.size(); j++){
					if (possibleSets.get(i) == hand.get(j).getNumber()){
						phasedOutSet.add(hand.get(j));
					} 
					else if (possibleSets.get(i) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							phasedOutSet.add(hand.get(j));
						}
					}
				}
			}
			
			int i=0;
			while (i < phasedOutSet.size()){
				for(int j = 0; j< hand.size(); j++){
					if (phasedOutSet.get(i) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (phasedOutSet.get(i).getType() == Card.type.Wild){
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
						phasedOutRun.add(hand.get(j));
					} 
					else if (possibleRun.get(k) == 99){
						if(hand.get(j).getType() == Card.type.Wild){
							phasedOutRun.add(hand.get(j));
						}
					}
				}
			}
				int l = 0;
				while (l < phasedOutRun.size()){
					for(int j = 0; j< hand.size(); j++){
						if (phasedOutRun.get(l) ==(hand.get(j))){
							hand.remove(j);
							break;
						} else if (phasedOutRun.get(l).getType() == Card.type.Wild){
							if(hand.get(j).getType() == Card.type.Wild){
								hand.remove(j);
								break;
							}
						}
					}
					l++;
				}
		}
			
		
		// check 2 normal sets
		else if (numSets == 2 & setSize != 0 & secondSetSize == 0 & runSize == 0 & phasedOut){
			ArrayList<Integer> possibleSets = hand.checkSet(numSets, setSize);
			int counter = 0;
			for(int j = 0; j < hand.size(); j++){
					if (possibleSets.get(0) == hand.get(j).getNumber()){
						phasedOutSet.add(hand.get(j));
					} 
			}
			while (phasedOutSet.size() < setSize){
				if(possibleSets.contains(99)){
					
					phasedOutSet.add(new Card(0,Card.cardColor.Black,Card.type.Wild));
					possibleSets.remove(possibleSets.indexOf(99));
				}
			}
			
			for(int j = 0; j < hand.size(); j++){
				if (possibleSets.get(1) == hand.get(j).getNumber()){
					phasedOutSecondSet.add(hand.get(j));
				} 
			}
			while(phasedOutSecondSet.size() < setSize){
				if(possibleSets.contains(99)){
					phasedOutSecondSet.add(new Card(0,Card.cardColor.Black,Card.type.Wild));
					possibleSets.remove(possibleSets.indexOf(99));
				}
			}
			int l = 0;
			while (l < phasedOutSet.size()){
				for(int j = 0; j< hand.size(); j++){
					if (phasedOutSet.get(l) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (phasedOutSet.get(l).getType() == Card.type.Wild){
						if(hand.get(j).getType() == Card.type.Wild){
							hand.remove(j);
							break;
						}
					}
				}
				l++;
			}
			int k = 0;
			while (k < phasedOutSecondSet.size()){
				for(int j = 0; j< hand.size(); j++){
					if (phasedOutSecondSet.get(k) ==(hand.get(j))){
						hand.remove(j);
						break;
					} else if (phasedOutSecondSet.get(k).getType() == Card.type.Wild){
						if(hand.get(j).getType() == Card.type.Wild){
							hand.remove(j);
							break;
						}
					}
				}
				k++;
			}
		}
		// check a run
		else if (numSets == 0 & setSize == 0 & runSize != 0 & phasedOut) {
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
			
			phasedOutRun= done;
		} 	
		  
	}
	  
	public void hit(ArrayList<Player>players){
		if(phasedOut){
			for(Player p: players){
				if(p.phasedOut){
					System.out.println("I phased out.");
					if(!p.getPhasedOutColoredSet().isEmpty()){
						System.out.println("In a colored set.");
						Card.cardColor daColor = p.getPhasedOutColoredSet().get(0).getColor();
						for(int j = 0; j < 6; j++){
						for(int i = 0; i < hand.size(); i ++){
							if(hand.get(i).getColor()== daColor){
								hand.remove(i);
								i--;
							} else if(hand.get(i).getType() == Card.type.Wild){
								hand.remove(i);
							}
						}
						}
					} 
					if(!p.getPhasedOutRun().isEmpty()){
						System.out.println("In a run set");
						int lowerNumber;
						int higherNumber;
						if(p.getPhasedOutRun().get(0).getNumber() != 1){
							lowerNumber = p.getPhasedOutRun().get(0).getNumber();
						} else {
							lowerNumber = -99;
						}
						if(p.getPhasedOutRun().get(p.getPhasedOutRun().size()-1).getNumber() != 12){
							higherNumber = p.getPhasedOutRun().get(p.getPhasedOutRun().size()-1).getNumber();
						}else if(p.getPhasedOutRun().get(p.getPhasedOutRun().size()-1).getNumber() == 99){// if the last card is a wild card
							if(p.getPhasedOutRun().get(p.getPhasedOutRun().size()-2).getNumber() == 11){// if the card before the last card is 11 so wild is 12
								higherNumber = -99;
							} else{
								higherNumber = p.getPhasedOutRun().get(p.getPhasedOutRun().size()-2).getNumber()+1;
							}
						} else { // the last card is 12
							higherNumber = -99;
						}
						System.out.println(lowerNumber + " fjdkafj;aslfd " + higherNumber);
						for(int j = 0; j < 6; j++){
						for(int i=0; i<hand.size();i++){
							
							if(hand.get(i).getNumber() == lowerNumber -1){
								
								hand.remove(i);
								i--;
								if(lowerNumber -1 == 1){
									lowerNumber = -99;
								}else{
									lowerNumber--;
								}
							} else if(hand.get(i).getNumber() == higherNumber +1){
								hand.remove(i);
								i--;
								if(higherNumber +1 == 12){
									higherNumber = -99;
								} else{
									higherNumber++;
								}
							}
						}
						}
					} 
					if(!p.getPhasedOutSet().isEmpty()){
						System.out.println("In the first set.");
						int daNumba = p.getPhasedOutSet().get(0).getNumber();
						for(int j = 0; j < 6; j++){
						for(int i =0; i < hand.size(); i++){
							if(hand.get(i).getNumber() == daNumba){
								hand.remove(i);
								i--;
							}else if(hand.get(i).getType()== Card.type.Wild){
								hand.remove(i);
							}
						}
						}
					} 
					if(!p.getPhasedOutSecondSet().isEmpty()){
						System.out.println("In the second set.");
						int daNumba = p.getPhasedOutSecondSet().get(0).getNumber();
						for(int j = 0; j < 6; j++){
						for(int i =0; i < hand.size(); i++){
							if(hand.get(i).getNumber() == daNumba){
								hand.remove(i);
								i--;
							}else if(hand.get(i).getType()== Card.type.Wild){
								hand.remove(i);
							}
						}
						}
					}
				}
			  }
		}
		  
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
		} else if (this.getStrategy() == Strategy.strategyType.lowestScore){
			l.setPlayer(this);
			Card tempCard = l.discard();
			return tempCard;
		} else {
			r.setPlayer(this);
			Card tempCard = r.discard();
			return tempCard;
		}
	}
	
	public void draw(Deck drawPile, Deck discardPile){
		if(this.getStrategy() == Strategy.strategyType.drunkPlayer){
			d.setPlayer(this);
			d.draw(drawPile, discardPile);
		} else if(this.getStrategy() == Strategy.strategyType.preventer){
			p.setPlayer(this);
			p.draw(drawPile,discardPile);
		} else if (this.getStrategy() == Strategy.strategyType.lowestScore){
			l.setPlayer(this);
			l.draw(drawPile, discardPile);
		} else {
			r.setPlayer(this);
			r.draw(drawPile, discardPile);
		}
		
	}
	
	public void getPhaseInfo(){
		phase.checkPhase(this.getPhaseNumber(), this);
	}
  
  
}