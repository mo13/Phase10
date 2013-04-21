package Model;
import java.util.*;

import Model.Card.cardColor;

public class Hand extends ArrayList<Card> {

  public ArrayList<Card> CardsInHand;

  // preconditions: the deck has to have a card in it. 
  // postconditions: the players hand is incremented by 1. 
  public void draw(Deck deck){
	  try{
		  Card tempCard = deck.draw();
		  this.add(tempCard);
	  } catch(IndexOutOfBoundsException e){
		  System.out.println("Draw Deck is empty, refilling the draw pile.");

//		  this is not done yet I need to come back and fix this code right here to refill.
//		  finish the controller first and then this should run
		  
	  }
	  	
	  
  }
  
  // postcondition the hand must be one card smaller after the operation. 
  // the deck it is adding to is always the discard pile so the discard 
  // pile should be decreased.S
  public void discard(int index){
	  this.remove(index);
  }

  
  public void orderHand(){
	  Card biggerCard;
	  Card smallerCard;
	  Card specialCard;
	  for(int i = 0; i < this.size()-1; i++){
		  for(int j = 0; j <this.size()-1; j++){
			  if (this.get(i).getNumber() == 0){
				  specialCard = this.remove(i);
				  this.add(specialCard);
				  
			  } else if(this.get(j).getNumber() == 0){
				  specialCard = this.remove(j);
				  this.add(specialCard);
			  } else if(this.get(i).getNumber() < this.get(j).getNumber()){
				  biggerCard = this.remove(i);
				  smallerCard = this.remove(j);
				  this.add(j, biggerCard);
				  this.add(i, smallerCard);
			  }
		  }
	  }
  }
  
  public void checkHand(){
	  
  }
  
  public ArrayList<ArrayList<Card>> checkSet(int numSets, int setSize){
	  	int setsToGo = numSets;
		Card tempCard;
		int tempNum;
		// this will create the setHand arrayList
		ArrayList<ArrayList<Card>> setHand = new ArrayList<ArrayList<Card>>();
		//output containing the numbers that we are  possibleSets.size()using for an arrayList
		ArrayList<ArrayList<Card>> possibleSets = new ArrayList<ArrayList<Card>>();
		for (int origCard = 0; origCard < this.size(); origCard++){							// will iterate through the players hand
			if (this.get(origCard).getType() == Card.type.Normal){
			tempNum = this.get(origCard).getNumber();										// gets the number of the current card
			if(!setHand.isEmpty()){															// if the setHand list is not empty.
				for(int setHandIndex = 0; setHandIndex < setHand.size(); setHandIndex++){	// iterate through the setHand list 
					if (setHand.get(setHandIndex).get(0).getNumber() == tempNum ){			// check to see if a list exist for this card
						setHand.get(setHandIndex).add(this.get(origCard));					// add this card to that current list
					} else{																	// if the setHand list doesn't have a list for this number
						ArrayList<Card> temp = new ArrayList<Card>();						// create an arraylist for this number
						temp.add(this.get(origCard));										// add the current card to the arrayList and then 
						setHand.add(temp); 												// add the new arraylist to the setHand list
					}
				} 
			} else {																	// if the setHand list is empty
				ArrayList<Card> temp = new ArrayList<Card>();							// just create a list and then ad it to the setHand list
				temp.add(this.get(origCard));
				setHand.add(temp);
			}
		} else if (this.get(origCard).getType() == Card.type.Wild){
			for (int setHandIndex = 0; setHandIndex < setHand.size(); setHandIndex++){
				if (setsToGo == 0){
					break;
				}
				if (setHand.get(setHandIndex).size() == setSize){		// if the size of the set is good
					setsToGo--;
				} else if (setHand.get(setHandIndex).size() == setSize -1){ // if the size of the set is 1 smaller 
					tempCard = this.get(origCard);
					setHand.get(setHandIndex).add(tempCard); 		// if there are no sets close to the size
				} 
			}
		}
	}
		for(int setHandIndex = 0; setHandIndex < setHand.size(); setHandIndex++){
			if (possibleSets.size() < numSets){
				if (setHand.get(setHandIndex).size() == setSize){
				//	possibleSets.add(setHand.get(setHandIndex).get(0).getNumber());
					possibleSets.add(setHand.get(setHandIndex));
				}
			}
		}
		return possibleSets;
  }

}