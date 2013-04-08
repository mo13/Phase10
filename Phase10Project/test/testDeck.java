import static org.junit.Assert.*;

import org.junit.Test;


public class testDeck {
	Deck deck = new Deck();
	@Test
	public void test() {
		deck.createDeck();
		System.out.print(deck.toString());
		for(Card c : deck){
			if( c.getType()== Card.type.Normal){
				
			} else if (c.getType() == Card.type.Wild){
				
			} else if (c.getType()== Card.type.Skip){
				
			}
		}
	}

}
