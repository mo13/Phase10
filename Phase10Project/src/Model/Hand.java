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
  
  
  
  public ArrayList<Integer> checkSet(int numSets, int setSize){
	  int setsToGo = numSets;
	  ArrayList<Integer> integerList = new ArrayList<Integer>();
	  ArrayList<Integer> cardNumberList = new ArrayList<Integer>();
	  ArrayList<Integer> possibleSetNumbers = new ArrayList<Integer>();
	  // this will iterate through the original cards 
	  for(int originalCardIndex = 0; originalCardIndex < this.size(); originalCardIndex++){
		  int tempNum = this.get(originalCardIndex).getNumber();
		  if (this.get(originalCardIndex).getType() == Card.type.Normal){
			  if(cardNumberList.isEmpty()){
				  cardNumberList.add(this.get(originalCardIndex).getNumber());
				  integerList.add(1);
			  } else {
				  int currentIndexOf = cardNumberList.indexOf(this.get(originalCardIndex).getNumber());
				  if (currentIndexOf == -1){
					  cardNumberList.add(this.get(originalCardIndex).getNumber());
					  integerList.add(1);
				  } else {
					  int previousNumber = integerList.get(currentIndexOf);
					  integerList.set(currentIndexOf,(previousNumber +1));
				  }
			  }
		  } else if (this.get(originalCardIndex).getType() == Card.type.Wild){
			  for(int integerListIndex = 0; integerListIndex < integerList.size(); integerListIndex++){
				  if(setsToGo == 0){
					  break;
				  }
				  if(integerList.get(integerListIndex) == setSize){
					  setsToGo--;
					  
				  } else if (integerList.get(integerListIndex) == setSize -1){
					  int previousNumber = integerList.get(integerListIndex);
					  integerList.set(integerListIndex, previousNumber +1);
					  cardNumberList.add(99);
					  setsToGo--;
					  break;
				  } 
			  }
		  } else if(this.get(originalCardIndex).getType() == Card.type.Skip){
			  cardNumberList.add(this.get(originalCardIndex).getNumber());
			  integerList.add(999);
		  }
	  }
	  // create the difference between the set size and the number of cards we have
	  ArrayList<Integer> difference = new ArrayList<Integer>();
	  for(int i =0; i < integerList.size(); i++){
		  difference.add(setSize-integerList.get(i));
	  }
	  
	  for(int i = 0; i < difference.size(); i++){
		  int tempNum = numSets;
		  if (tempNum != 0){
			 if (difference.get(i) == setSize-setSize){
				 possibleSetNumbers.add(cardNumberList.get(i));
				 tempNum--;

			  } else if (difference.get(i) == setSize-(setSize-1) && difference.indexOf(0) == -1){
				  possibleSetNumbers.add(cardNumberList.get(i));
			 
			  } else if (difference.get(i) == setSize-(setSize-2) && (difference.indexOf(0) == -1 && difference.indexOf(1) == -1)){
				  possibleSetNumbers.add(cardNumberList.get(i));
			  }	else if (difference.get(i) == setSize-(setSize-3) && ((difference.indexOf(0) == -1 && difference.indexOf(1) == -1) && difference.indexOf(2) == -1)){
				  possibleSetNumbers.add(cardNumberList.get(i));
			  }
		   }
	  }
	  for(int i = 0; i < cardNumberList.size(); i++){
		  if (cardNumberList.get(i) == 99) {
			  possibleSetNumbers.add(99);
		  }
	  }

//	 Collections.sort(integerList);
//	 System.out.println(integerList);
		return possibleSetNumbers;
  }
  
  public ArrayList<Integer> checkRun(ArrayList<Integer> setNumbers, int runSize){
	  Hand tempHand = new Hand();
	  for(int i = 0; i < this.size(); i++){
		  if (this.get(i).getType() == Card.type.Wild ){
			  if (setNumbers.contains(99)){
			  tempHand.add(this.get(i));
			  }
		  } else if (this.get(i).getType() == Card.type.Normal){
			  if(this.get(i).getNumber() != setNumbers.get(0)){
			  
				  tempHand.add(this.get(i));
			  }
		  } else if (this.get(i).getType() == Card.type.Skip){
			  tempHand.add(this.get(i));
		  }
	  }
	  System.out.println(tempHand.toString());
	  int previousNumber = 0;
	  Boolean runCheck = false;
	  int tempRunSize = runSize;
	  ArrayList<Integer> possibleRun = new ArrayList<Integer>();
	  for(int runIndex = 0; runIndex < tempHand.size(); runIndex++){
		  if(tempRunSize != 0){
			  if(runIndex == tempHand.size()-1){
				  break;
			  } else if (runIndex == 0){
				  previousNumber = tempHand.get(runIndex).getNumber();
				  possibleRun.add(previousNumber);
				  tempRunSize--;
			  } else{
				  if(tempHand.get(runIndex).getNumber() -1 == previousNumber){
					  previousNumber = tempHand.get(runIndex).getNumber();
					  possibleRun.add(previousNumber);
					  tempRunSize--;
				  } else {
					  previousNumber = tempHand.get(runIndex).getNumber();
					  possibleRun = new ArrayList<Integer>();
					  tempRunSize = runSize;
				  }
			  }
		  }
	  }
	  System.out.println(possibleRun.toString());
	  return possibleRun;
  }
}