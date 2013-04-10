package Model;
import java.util.*;

import Model.Card.cardColor;

public class Hand extends ArrayList<Card> {

  public ArrayList<Card> CardsInHand;

        
  public void draw(Deck deck){
	  Card tempCard = deck.draw();
	  this.add(tempCard);
  }
  
  
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

}