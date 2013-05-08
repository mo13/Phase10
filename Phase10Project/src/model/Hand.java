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
  
  public int indexOf(Card c){
	  return cardsInHand.indexOf(c);
  }
  
  public void add(int i, Card c){
	  cardsInHand.add(i,c);
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
	  ArrayList<Card> tempHand = new ArrayList<Card>();
	  tempHand.addAll(cardsInHand);
	  int setsToGo = numSets;
	  int numWilds = 0;
	  ArrayList<Integer> integerList = new ArrayList<Integer>();
	  ArrayList<Integer> cardNumList = new ArrayList<Integer>();
	  ArrayList<Integer> possibleSetNumbers = new ArrayList<Integer>();
	  // this will iterate through the original cards 
	  for(int oci = 0; oci < tempHand.size(); oci++){
		  int tempNum = tempHand.get(oci).getNumber();
		  if(tempHand.get(oci).getType() == Card.type.Normal){		// if the card is normal
			  if(cardNumList.indexOf(tempNum) == -1){	// if the card isn't in the list already.
				  cardNumList.add(tempNum);
				  integerList.add(cardNumList.size()-1, 1);
			  } else{											// the card is in the list already.
				  int tempIndex = cardNumList.indexOf(tempNum);
				  int previousNumber = integerList.get(tempIndex);
				  integerList.set(tempIndex, (previousNumber +1));
			  }
		  } else if (tempHand.get(oci).getType() == Card.type.Wild){
			  numWilds++;
			  tempHand.remove(oci);
			  oci--;
		  } else if (tempHand.get(oci).getType() == Card.type.Skip){
			  tempHand.remove(oci);
			  oci--;
		  }
	  }
	  
	  for(int i = 0; i < integerList.size(); i++){
		  if(setSize - integerList.get(i) ==1 & numWilds>0	){
			  integerList.set(i, setSize);
			  integerList.add(99);
			  numWilds--;
		  }
	  }
	  
	  for(int i = 0; i < integerList.size(); i++){
		  if(integerList.get(i) - setSize >= 0 & integerList.get(i)- setSize <= 50){
			  possibleSetNumbers.add(cardNumList.get(i));
			  setsToGo--;
		  }
		  if(integerList.get(i)==99){
			  possibleSetNumbers.add(99);
		  }
	  }
		return possibleSetNumbers;
  }
  
  public Card remove(int i){
	  return cardsInHand.remove(i);
  }
  
 
  public ArrayList<Integer> checkRun(ArrayList<Integer> setNumbers, int setSize, int runSize){
	  ArrayList<Card> tempHand = new ArrayList<Card>();
	  tempHand.addAll(cardsInHand);
	  int setCardsRemoved = 0;
//	  System.out.println("The number of sets " + setNumbers);
//	  System.out.println("The size of the sets "+ setSize);
//	  System.out.println("The size of the run is "+ runSize);
//	  System.out.println(tempHand);
	  
	  // remove the cards that are being used for the sets. 
	  for(int i = 0; i < setNumbers.size(); i++){
		  for(int c = 0; c< tempHand.size(); c++){
			  if (tempHand.get(c).getNumber() == setNumbers.get(i)){
				  if(setCardsRemoved == setSize){
					  break;
				  }else{
					  tempHand.remove(c);
					  c--;
					  setCardsRemoved++;
				  }
			  } else if (tempHand.get(c).getType() == Card.type.Wild & setNumbers.get(i)==99){
				  tempHand.remove(c);
				  c--;
			  }
		  }
	  }
//	  System.out.println("after getting rid of "+ setNumbers + " here is the hand \n"+ tempHand);
	  // remove the wilds and skips keeping count of the wilds that are left in the hand.
	  int wildCount = 0;
	  for(int i = 0; i < tempHand.size(); i ++){
		  if (tempHand.get(i).getType() == Card.type.Wild){
			  wildCount++;
			  tempHand.remove(i);
			  i--;
		  } else if(tempHand.get(i).getType() == Card.type.Skip){
			  tempHand.remove(i);
			  i--;
		  }
	  }
//	  System.out.println("The wild count is " + wildCount);
//	  System.out.println("after getting rid of wilds and skips here is the hand \n"+ tempHand);

	  int previousNumber = 0;
	  int tempRunSize = runSize;


	  ArrayList<Integer> possibleRun = new ArrayList<Integer>();
//	  System.out.println("######################## inside the check run ");
	  for(int runIndex = 0; runIndex < tempHand.size(); runIndex++){
//		  System.out.println("The card number is" +tempHand.get(runIndex).getNumber());
//		  System.out.println("The previous number is " + previousNumber);
		  if(possibleRun.size() == runSize){
			  return possibleRun;
		  } else if(tempRunSize != 0){
			  if (runIndex == 0){
			  previousNumber = tempHand.get(runIndex).getNumber();
			  possibleRun.add(previousNumber);
			  tempRunSize--;
			  }  else{
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
					  possibleRun.add(previousNumber);
					  tempRunSize = runSize;
					  }
			  }
		  } else if (tempRunSize ==0){
			  break;
		  } 
//		  System.out.println("the possibe run is "+ possibleRun);
	  }
//	  System.out.println("At the end the possible run is  "+possibleRun);
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
	  ArrayList<Integer> temporaryRun = new ArrayList<Integer>();
	  int oldRun = 0;
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
					  if(possibleRun.size() > oldRun){
						  oldRun = possibleRun.size();
						  temporaryRun= possibleRun;
					  }
					  possibleRun = new ArrayList<Integer>();
					  tempRunSize = runSize;
					  }
			  }
		  }
	  }
	  if (temporaryRun.size() > possibleRun.size()){
		  return temporaryRun;
	  }else{
	  return possibleRun;
	  }
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
			result.append(this.get(i).toString() + " ");
			result.append(NEW_LINE);
			
		}
		return result.toString();
	}
}