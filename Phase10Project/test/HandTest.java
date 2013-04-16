import static org.junit.Assert.*;

import org.junit.Test;
import Model.*;

public class HandTest {
	
	Player cortona = new Player("Cortona");
	Deck drawPile = new Deck(Deck.deckType.DrawPile);
	@Test 
	public void testHandCreate(){
		fail("Not yet implemented");
	}
	@Test
	public void testGoodDraw() {
		drawPile.createDeck();
		cortona.hand.draw(drawPile);
		assertSame(drawPile.size(), 107);
		assertSame(cortona.hand.size(), 1);
			
		}
	
	@Test
	public void testBadDraw() {
//		drawPile.createDeck();
//		cortona.hand.draw(drawPile);
//		assertSame(drawPile.size(), 107);
//		assertSame(cortona.hand.size(), 1);
		fail("Not yet implemented.");
		}

	@Test
	public void testDiscard() {
		drawPile.createDeck();
		cortona.hand.draw(drawPile);
		cortona.hand.draw(drawPile);
		cortona.hand.draw(drawPile);
		// the size of the drawPile decreased by three because of the draw methods. 
		assertSame(drawPile.size(),105);
		// the players hand grew by three because of the three draws.
		assertSame(cortona.hand.size(), 3);
		cortona.hand.discard(1);
		// 
		assertSame(cortona.hand.size(),2);
		
	}
	
	@Test 
	public void testOrderHand(){
		Hand hand = new Hand();
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(8, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard5 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard6 = new Card(1, Card.cardColor.Green, Card.type.Normal);
		hand.add(tempCard1);
		hand.add(tempCard2);
		hand.add(tempCard3);
		hand.add(tempCard4);
		hand.add(tempCard5);
		hand.add(tempCard6);
		hand.orderHand();
		assertSame(hand.get(0), tempCard3);
		assertSame(hand.get(1), tempCard6);
		assertSame(hand.get(2), tempCard1);
		assertSame(hand.get(3), tempCard2);
		//assertSame(hand.get(3), tempCard4);
		//assertSame(hand.get(4), tempCard5);
	}
}
