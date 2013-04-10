package Model;
import java.util.*;

public class Hand extends ArrayList<Card> {

  public ArrayList<Card> CardsInHand;

        public Vector  myCard;
        
  public void draw(Deck deck){
	  Card tempCard = deck.draw();
	  this.add(tempCard);
  }

}