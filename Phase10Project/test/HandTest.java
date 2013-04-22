import static org.junit.Assert.*;
import java.util.*;

import model.*;

import org.junit.Test;

public class HandTest {
	
	Player cortona = new Player("Cortona");
	Deck drawPile = new Deck(Deck.deckType.DrawPile);
	
	
	@Test
	public void testGoodDraw() {
		drawPile.createDeck();
		cortona.hand.draw(drawPile);
		assertSame(drawPile.size(), 107);
		assertSame(cortona.hand.size(), 1);
			
		}
	
	@Test
	public void testBadDraw() {
		drawPile.createDeck();
		for(int i = 0; i < 107; i++){
			drawPile.draw();
		}
		assertSame(drawPile.size(), 1);
		cortona.hand.draw(drawPile);
		cortona.hand.draw(drawPile);
		assertSame(cortona.hand.size(), 1);
		
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
		
	}
	// with a wild in it
	@Test
	public void testCheckSet(){
		Player chief = new Player("Chief");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard6 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCardW);
		chief.hand.orderHand();
		ArrayList<Integer> possibleSets = new ArrayList<Integer>();
		possibleSets = chief.hand.checkSet(2,3);

		assertSame(possibleSets.get(0),1);
		assertSame(possibleSets.get(1),3);
		assertSame(possibleSets.get(2),99);
	}
	@Test
	public void testCheckSetNoWild(){
		Player chief = new Player("Chief");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard6 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card tempCard9 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCard9);
		chief.hand.orderHand();
		ArrayList<Integer> possibleSets = new ArrayList<Integer>();
		possibleSets = chief.hand.checkSet(2,3);

		assertSame(possibleSets.get(0),1);
		assertSame(possibleSets.get(1),3);
		//assertSame(possibleSets.get(2),99);
	}
	@Test
	public void testRunCheckEasy(){
		Player chief = new Player("Chief");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard6 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(5, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCard9 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		//Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCard9);
		chief.hand.orderHand();
		ArrayList<Integer> possibleSets = new ArrayList<Integer>();
		possibleSets = chief.hand.checkSet(1,3);
		ArrayList<Integer> possibleRun = new ArrayList<Integer>();
		possibleRun = chief.hand.checkRun(possibleSets, 4);
		
 
		assertSame(possibleRun.get(0),2);
		assertSame(possibleRun.get(1),3);
		assertSame(possibleRun.get(2),4);
		assertSame(possibleRun.get(3),5);
	}
	
	@Test
	public void testRunWildCheck(){
		Player chief = new Player("Chief");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(9 , Card.cardColor.Red, Card.type.Normal);	
		Card tempCard6 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(5, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCardW);
		chief.hand.orderHand();
		ArrayList<Integer> possibleSets = new ArrayList<Integer>();
		possibleSets = chief.hand.checkSet(1,3);
		ArrayList<Integer> possibleRun = new ArrayList<Integer>();
		possibleRun = chief.hand.checkRun(possibleSets, 4);
		

		assertSame(possibleRun.get(0),2);
		assertSame(possibleRun.get(1),99);
		assertSame(possibleRun.get(2),4);
		assertSame(possibleRun.get(3),5);
	}
	
	@Test
	public void testCheckColorSet(){
		Player chief = new Player("Chief");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard5 = new Card(9 , Card.cardColor.Blue, Card.type.Normal);	
		Card tempCard6 = new Card(4, Card.cardColor.Green, Card.type.Normal);
		Card tempCard7 = new Card(5, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCardW);
		chief.hand.orderHand();
		ArrayList<Integer> possibleColor = new ArrayList<Integer>();
		possibleColor = chief.hand.checkColor();
		assertSame(possibleColor.get(0), 4);
		assertSame(possibleColor.get(1), 2);
		assertSame(possibleColor.get(2), 2);
		assertSame(possibleColor.get(3), 1);

		
	}
}
