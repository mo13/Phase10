package model;

import java.util.*;

import model.Card.cardColor;

import strategy.*;
import strategy.Strategy.strategyType;

// I am just making another test change.
public class Player {
	
  private String name;
  public Strategy.strategyType currentStrategy;
  public  Hand hand;
  private int phaseNumber;
  private int score;
  private boolean canPhaseOut;

public boolean displayPhasedOut;
  

private boolean phasedOut;

  
	
	ArrayList<Integer> possiblePhasedOutRun = new ArrayList<Integer>();

	ArrayList<Integer> possiblePhasedOutSets = new ArrayList<Integer>();
	ArrayList<Card.cardColor> possiblePhasedOutColor = new ArrayList<Card.cardColor>();


  ArrayList<Card> phasedOutSecondSet = new ArrayList<Card>();
  ArrayList<Card> phasedOutRun = new ArrayList<Card>();
  ArrayList<Card> phasedOutColoredSet = new ArrayList<Card>();
  ArrayList<Card> phasedOutSet = new ArrayList<Card>();
  
  
  // the phase info
  
  
  public void emptyPossiblePhaseOutCard(){

	 possiblePhasedOutRun = new ArrayList<Integer>();
	 possiblePhasedOutSets = new ArrayList<Integer>();
	 possiblePhasedOutColor = new ArrayList<Card.cardColor>();
  }
  
  public void emptyPhasedOutSets(){
	 phasedOutSecondSet = new ArrayList<Card>();
	 phasedOutRun = new ArrayList<Card>();
	 phasedOutColoredSet = new ArrayList<Card>();
	 phasedOutSet = new ArrayList<Card>();
  }
  
 
  public String phasedOutStuffToString(){
	  String str = "";
	  if(!phasedOutSet.isEmpty()){
		  str+= "First set is \n" + phasedOutSet.get(0).getNumber() +"\n";
	  } 
	  if(!phasedOutSecondSet.isEmpty()){
		  str+= " The second set is  \n" + phasedOutSecondSet.get(0).getNumber() +"\n";
	  }
	  if (!phasedOutRun.isEmpty()){
		  str+= " 1st num is "+ phasedOutRun.get(0) +" the num is "+ phasedOutRun.get(phasedOutRun.size()-1) +"\n";
	  }
	  if (!phasedOutColoredSet.isEmpty()){
		  str+= " The phased out color is "+ phasedOutColoredSet.get(0).getColor() +"\n";
	  }
		return str;
  }
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
  	
  public void setCanPhaseOut(boolean canPhaseOut) {
	this.canPhaseOut = canPhaseOut;
  }

	public void setDisplayPhasedOut(boolean displayPhasedOut) {
		this.displayPhasedOut = displayPhasedOut;
	}
  	public Strategy.strategyType getStrategy() {
  		return currentStrategy;
  	}
	public void setStrategy(strategyType strategy) {
		currentStrategy = strategy;
		
	}
	
	public boolean isCanPhaseOut() {
		return canPhaseOut;
	}
	
	public String getHandSize(){
		return Integer.toString(this.hand.size());
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
	



	public ArrayList<Integer> getPossiblePhasedOutRun() {
		return possiblePhasedOutRun;
	}


	public ArrayList<Integer> getPossiblePhasedOutSet() {
		return possiblePhasedOutSets;
	}


	public ArrayList<Card.cardColor> getPossiblePhaseColor() {
		return possiblePhasedOutColor;
	}

	public Boolean checkPhase(){
		hand.orderHand();
		// red blue green yellow
		// check a color set
		int mostColor =0;
		int bestColor = 0; 
		if (colorSets != 0 & numSets == 0 & runSize == 0 & setSize == 7){
			hand.orderHand();			
			ArrayList<Integer> tempColor = hand.checkColor();			
			for(int  i= 0; i < tempColor.size();i++){
				if(tempColor.get(i) > mostColor){
					mostColor = tempColor.get(i);
					bestColor = i;
				}
				if (tempColor.get(i)>= setSize){
					canPhaseOut = true;
				}
			}
			if (bestColor ==0 ){
				possiblePhasedOutColor.add(Card.cardColor.Red);
			} else if(bestColor == 1){
				possiblePhasedOutColor.add(Card.cardColor.Blue);
			} else if (bestColor == 2){
				possiblePhasedOutColor.add(Card.cardColor.Green);
			} else if (bestColor == 3){
				possiblePhasedOutColor.add(Card.cardColor.Yellow);
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
			
			
			for(Integer z : tempSet1){
				possiblePhasedOutSets.add(z);
			}
			for(Integer z : tempSet2){
				possiblePhasedOutSets.add(z);
			}
			System.out.println(possiblePhasedOutSets);
			if(!tempSet1.isEmpty()& !tempSet2.isEmpty()){
				canPhaseOut = true;
			} else {
				canPhaseOut = false;
			}
		}
		// check a set and a run
		else if (numSets == 1 & setSize != 0 &  runSize != 0){
			ArrayList<Integer> possibleSets = hand.checkSet(1,setSize);			
			possiblePhasedOutSets = possibleSets;
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(possibleSets,setSize, runSize);
			possiblePhasedOutRun = possibleRun;
			if(!possibleSets.isEmpty() & possibleRun.size() == runSize){
				canPhaseOut =  true;
				
			}else{
				canPhaseOut =  false;
			}
			
		}
		// check 2 normal sets
		else if (numSets == 2 & setSize != 0 & secondSetSize == 0 & runSize == 0){
			hand.orderHand();
			ArrayList<Integer> possibleSets = hand.checkSet(numSets, setSize);
			possiblePhasedOutSets = possibleSets;
			hand.orderHand();
			int counter = 0;
			for(int i = 0; i < possibleSets.size(); i++){
				if (possibleSets.get(i) != 99){
					counter++;
				}
			}
		//	System.out.println(possibleSets);
			if (counter >=numSets){
				canPhaseOut =  true;
			} else {
				canPhaseOut = false;
			}
		}
		// check a run
		else if (numSets == 0 & setSize == 0 & runSize != 0) {
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(runSize);
			possiblePhasedOutRun = possibleRun;
			if (possibleRun.size()== runSize){
				canPhaseOut =  true;

			} else{
				canPhaseOut =  false;
			}
		} else {
			canPhaseOut =  false;
		} 
//		System.out.println(canPhaseOut);
		return canPhaseOut;
	}
	
	public void phaseOut(){
		if (colorSets != 0 & numSets == 0 & runSize == 0 & setSize == 7 & canPhaseOut){
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
			phasedOut = true;
			phasedOutColoredSet = done;
		}
		// check 2 sets with different sizes
		else if (numSets == 2 & setSize != 0 & secondSetSize != 0 & runSize == 0 & canPhaseOut){
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
			phasedOut = true;
			
		}
		// check a set and a run
		else if (numSets == 1 & setSize != 0 &  runSize != 0 & canPhaseOut){
			ArrayList<Integer> possibleSets = hand.checkSet(1,setSize);			
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(possibleSets,setSize, runSize);
			
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
				phasedOut = true;
		}
			
		
		// check 2 normal sets
		else if (numSets == 2 & setSize != 0 & secondSetSize == 0 & runSize == 0 & canPhaseOut){
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
			phasedOut = true;
		}
		// check a run
		else if (numSets == 0 & setSize == 0 & runSize != 0 & canPhaseOut) {
			hand.orderHand();
			ArrayList<Integer> possibleRun = hand.checkRun(runSize);
//			System.out.println("possible run "+possibleRun);
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
//			System.out.println(done);
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
			phasedOut = true;
			phasedOutRun= done;
		} 	
		  
	}
	  
	public void hit(ArrayList<Player>players){
		if(phasedOut){
			for(Player p: players){
				if(p.phasedOut){
//					System.out.println("I phased out.");
					if(!p.getPhasedOutColoredSet().isEmpty()){
//						System.out.println("In a colored set.");
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
//						System.out.println("In a run set");
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
//						System.out.println(lowerNumber + " fjdkafj;aslfd " + higherNumber);
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
//						System.out.println("In the first set.");
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
//						System.out.println("In the second set.");
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
	
	Strategy random = new RandomPlayer();
	Strategy olRed = new OldRed();
	Strategy olLow = new OldLowestScore();
	Strategy olReck = new OldReckless();
	Strategy mewRed = new newRed();
	Strategy mewLow = new newLowestScore();
	Strategy mewReck = new newReckless();
	public Card discard(){
		if(this.getStrategy() == Strategy.strategyType.randomPlayer){
			random.setPlayer(this);
			Card tempCard = random.discard();
			return tempCard;
		} else if (this.getStrategy() == Strategy.strategyType.oldRed){
			olRed.setPlayer(this);
			Card tempCard = olRed.discard();
			return tempCard;
		} else if (this.getStrategy() == Strategy.strategyType.oldLowestScore){
			olLow.setPlayer(this);
			Card tempCard = olLow.discard();
			return tempCard;
		} else if(this.getStrategy() == Strategy.strategyType.newRed){
			mewRed.setPlayer(this);
			Card tempCard = mewRed.discard();
			return tempCard;
		} else if (this.getStrategy() == Strategy.strategyType.newLowestScore){
			mewLow.setPlayer(this);
			Card tempCard = mewLow.discard();
			return tempCard;
		} else if (this.getStrategy() == Strategy.strategyType.newRecklessPlayer){
			mewReck.setPlayer(this);
			Card tempCard = mewReck.discard();
			return tempCard;
		} else {
			olReck.setPlayer(this);
			Card tempCard = olReck.discard();
			return tempCard;
		}
	}
	
	public Boolean draw(Deck drawPile, Card discard){
		if(this.getStrategy() == Strategy.strategyType.randomPlayer){
			random.setPlayer(this);
			return random.draw(drawPile, discard);
		} else if(this.getStrategy() == Strategy.strategyType.oldRed){
			olRed.setPlayer(this);
			return olRed.draw(drawPile,discard);
		} else if (this.getStrategy() == Strategy.strategyType.oldLowestScore){
			olLow.setPlayer(this);
			return olLow.draw(drawPile, discard);
		} else if(this.getStrategy()== Strategy.strategyType.oldRecklessPlayer){
			olReck.setPlayer(this);
			return olReck.draw(drawPile, discard);
		} else if (this.getStrategy() == Strategy.strategyType.newRed){
			mewRed.setPlayer(this);
			return mewRed.draw(drawPile, discard);
		} else if (this.getStrategy() == Strategy.strategyType.newLowestScore){
			mewLow.setPlayer(this);
			return mewLow.draw(drawPile, discard);
		} else {
			mewReck.setPlayer(this);
			return mewReck.draw(drawPile, discard);
		} 
		
	}
	
	public void getPhaseInfo(){
		phase.checkPhase(this.getPhaseNumber(), this);
	}
  
  
}