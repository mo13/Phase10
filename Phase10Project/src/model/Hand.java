package model;
import java.util.*;

import model.Card.cardColor;


public class Hand  {

  private ArrayList<Card> cardsInHand = new ArrayList<Card>();

  // preconditions: the deck has to have a card in it. 
  // postconditions: the players hand is incremented by 1. 
  public void draw(Deck deck){
	  try{
		  Card tempCard = deck.draw();
		  cardsInHand.add(tempCard);
	  } catch(IndexOutOfBoundsException e){
		  

//		  this is not done yet I need to come back and fix this code right here to refill.
//		  finish the controller first and then this should run
		  
	  }	  
  }
  
  public void add(Card c){
		 cardsInHand.add(c);
  }
  
  public int size(){
	  return cardsInHand.size();
  }
  
  public Card get(int ind){
	  return cardsInHand.get(ind);
  }
  
 
  public void discard(int index){
	  cardsInHand.remove(index);
  }

  
  public void orderHand(){
	  
	  ArrayList<Card> tempHand = new ArrayList<Card>();
	  ArrayList<Card> specialCards = new ArrayList<Card>();
	  Card minCard;
	  
	  for(Card c :cardsInHand){
		  if(c.getNumber() == 0 | c.getType() == Card.type.Skip	){
			  specialCards.add(c);
		  }
	  }

	  for(Card c : specialCards){
		  cardsInHand.remove(c);
	  }

	  while(!cardsInHand.isEmpty()){
		  tempHand.add(cardsInHand.remove(0));
	  }
	  
	 while (!tempHand.isEmpty()){
		 minCard = new Card(12, Card.cardColor.Red, Card.type.Normal);
		 for(Card c : tempHand){
			 if (c.getNumber() <= minCard.getNumber()){
				 minCard = c;
			 }
		 }
		 tempHand.remove(minCard);
		 cardsInHand.add(minCard);
	 }
	for(Card c: specialCards){
		cardsInHand.add(c);
	}
		 
  }
  
  
  
  public ArrayList<Integer> checkSet(int numSets, int setSize){
	  int setsToGo = numSets;
	  ArrayList<Integer> integerList = new ArrayList<Integer>();
	  ArrayList<Integer> cardNumberList = new ArrayList<Integer>();
	  ArrayList<Integer> possibleSetNumbers = new ArrayList<Integer>();
	  // this will iterate through the original cards 
	  for(int originalCardIndex = 0; originalCardIndex < cardsInHand.size(); originalCardIndex++){
		  int tempNum = cardsInHand.get(originalCardIndex).getNumber();
		  if (cardsInHand.get(originalCardIndex).getType() == Card.type.Normal){
			  if(cardNumberList.isEmpty()){
				  cardNumberList.add(cardsInHand.get(originalCardIndex).getNumber());
				  integerList.add(1);
			  } else {
				  int currentIndexOf = cardNumberList.indexOf(cardsInHand.get(originalCardIndex).getNumber());
				  if (currentIndexOf == -1){
					  cardNumberList.add(cardsInHand.get(originalCardIndex).getNumber());
					  integerList.add(1);
				  } else {
					  int previousNumber = integerList.get(currentIndexOf);
					  integerList.set(currentIndexOf,(previousNumber +1));
				  }
			  }
		  } else if (cardsInHand.get(originalCardIndex).getType() == Card.type.Wild){
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
		  } else if(cardsInHand.get(originalCardIndex).getType() == Card.type.Skip){
			  cardNumberList.add(cardsInHand.get(originalCardIndex).getNumber());
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
				 // possibleSetNumbers.add(cardNumberList.get(i));
			 
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
  
  public Card remove(int i){
	  return cardsInHand.remove(i);
  }
  
 
  public ArrayList<Integer> checkRun(ArrayList<Integer> setNumbers, int runSize){
	  Hand tempHand = new Hand();
	  for(int i = 0; i < cardsInHand.size(); i++){
		  if (cardsInHand.get(i).getType() == Card.type.Wild ){
			  if (!setNumbers.contains(99)){
				  tempHand.add(cardsInHand.get(i));
			  }
		  } else if (cardsInHand.get(i).getType() == Card.type.Normal){
			  if(cardsInHand.get(i).getNumber() != setNumbers.get(0)){
			  
				  tempHand.add(cardsInHand.get(i));
			  }
		  } else if (cardsInHand.get(i).getType() == Card.type.Skip){
			  tempHand.add(cardsInHand.get(i));
		  }
	  }

	  int previousNumber = 0;
	  int tempRunSize = runSize;
	  int wildCount = 0;
	  for(int i = 0; i < tempHand.size(); i++){
		  if( tempHand.get(i).getType() == Card.type.Wild){
			  wildCount++;
		  }
	  }
	  ArrayList<Integer> possibleRun = new ArrayList<Integer>();
	  for(int runIndex = 0; runIndex < tempHand.size(); runIndex++){
		  if(tempRunSize != 0){
				  if (runIndex == 0){
				  previousNumber = tempHand.get(runIndex).getNumber();
				  possibleRun.add(previousNumber);
				  tempRunSize--;
			  } else{
				  if(tempHand.get(runIndex).getNumber() == previousNumber+1){
					  previousNumber = tempHand.get(runIndex).getNumber();
					  possibleRun.add(previousNumber);
					  tempRunSize--;
				  } else if (tempHand.get(runIndex).getNumber() == previousNumber){
					  // left blank intentionally
				  } else if (tempHand.get(runIndex).getType() == Card.type.Wild | wildCount > 0){ 
					  if(wildCount > 0){
						  previousNumber = previousNumber + 1;
						  possibleRun.add(99);
						  wildCount--;
						  tempRunSize--;
						  runIndex--;;
					  }
				  } else if (tempHand.get(runIndex).getType() == Card.type.Skip){
					  //left blank intentionally
				  }
				  else {
					  previousNumber = tempHand.get(runIndex).getNumber();
					  possibleRun = new ArrayList<Integer>();
					  tempRunSize = runSize;
					  }
			  }
		  }
	  }
	  return possibleRun;
  }
  
  public ArrayList<Integer> checkRun (int runSize){
	  int previousNumber = 0;
	  int tempRunSize = runSize;
	  int wildCount = 0;
	  for(int i = 0; i < cardsInHand.size(); i++){
		  if( cardsInHand.get(i).getType() == Card.type.Wild){
			  wildCount++;
		  }
	  }
	  ArrayList<Integer> possibleRun = new ArrayList<Integer>();
	  for(int runIndex = 0; runIndex < cardsInHand.size(); runIndex++){
		  if(tempRunSize != 0){
				  if (runIndex == 0){
				  previousNumber = cardsInHand.get(runIndex).getNumber();
				  possibleRun.add(previousNumber);
				  tempRunSize--;
			  } else{
				  if(cardsInHand.get(runIndex).getNumber() == previousNumber+1){
					  previousNumber = cardsInHand.get(runIndex).getNumber();
					  possibleRun.add(previousNumber);
					  tempRunSize--;
				  } else if (cardsInHand.get(runIndex).getNumber() == previousNumber){
					  // left blank intentionally
				  } else if (cardsInHand.get(runIndex).getType() == Card.type.Wild | wildCount > 0){ 
					  if(wildCount > 0){
						  previousNumber = previousNumber + 1;
						  possibleRun.add(99);
						  wildCount--;
						  tempRunSize--;
						  runIndex--;;
					  }
				  } else if (cardsInHand.get(runIndex).getType() == Card.type.Skip){
					  //left blank intentionally
				  }
				  else {
					  previousNumber = cardsInHand.get(runIndex).getNumber();
					  possibleRun = new ArrayList<Integer>();
					  tempRunSize = runSize;
					  }
			  }
		  }
	  }
	  return possibleRun;
  }
  
  public ArrayList<Integer> checkColor(){
	  int maxIndex = 0;
	  int maxNumber =0;
	  ArrayList<Card.cardColor> colorArray = new ArrayList<Card.cardColor>();
	  colorArray.add(Card.cardColor.Red);
	  colorArray.add(Card.cardColor.Blue);
	  colorArray.add(Card.cardColor.Green);
	  colorArray.add(Card.cardColor.Yellow);
	 
	  ArrayList<Integer> numberArray = new ArrayList<Integer>();
	  for (int i = 0; i < colorArray.size(); i++){
		  int tempNumber = 0;
		  for(int j = 0; j < cardsInHand.size(); j++){
			  if(cardsInHand.get(j).getColor() == colorArray.get(i)){
				  tempNumber++;
			  } 
			   
		  }
		  numberArray.add(i, tempNumber);
	  }
	  for(int j = 0; j < cardsInHand.size(); j++){
		  if(cardsInHand.get(j).getType() == Card.type.Wild){
			  for(int numberIndex = 0; numberIndex < numberArray.size(); numberIndex++){
				  if(numberArray.get(numberIndex) >= maxNumber){
					  maxIndex = numberIndex;
					  maxNumber = numberArray.get(numberIndex);
				  }
			  }
			  numberArray.set(maxIndex, maxNumber +1);
		  }
	  }
	  return numberArray;
  }
  
  public String toString(){
		 StringBuilder result = new StringBuilder();
		 String NEW_LINE = System.getProperty("line.separator");

		for(int i = 0; i < this.size(); i++){
			result.append(this.get(i).toString());
			result.append(NEW_LINE);
		}
		return result.toString();
	}
}