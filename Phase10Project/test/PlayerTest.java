

import static org.junit.Assert.*;

import model.*;

import org.junit.Test;

import strategy.*;

public class PlayerTest {
	
	Player cortona = new Player("Cortona");
	
	@Test
	// This test to see if the setStrategy and the getStartegy methods work. 
	public void testGetAndSetStrategy() {
		Strategy preventer = new Preventer();
		assertSame(cortona.getStrategy(), null);
		cortona.setStrategy(preventer);
		assertSame(cortona.getStrategy(), preventer);
	}



	@Test
	public void testGetandSetPhaseNumber() {
		assertSame(cortona.getPhaseNumber(),1);
		cortona.setPhaseNumber(2);
		assertSame(cortona.getPhaseNumber(),2);
	}



	@Test
	public void testGetandSetScore() {
		assertSame(cortona.getScore(), 0);
		cortona.setScore(25);
		assertSame(cortona.getScore(), 25);
	}


	@Test
	public void testGetName() {
		assertSame(cortona.getName(), "Cortona");
	}

	@Test
	public void testDrawCard() {
		Deck drawPile = new Deck(Deck.deckType.DrawPile);
		drawPile.createDeck();
		System.out.println(drawPile.size());
		System.out.println(drawPile.draw());
		System.out.println(drawPile.size());
		cortona.hand.draw(drawPile);
		Card card1 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		cortona.hand.add(card1);
		assertSame(cortona.hand.size(), 2 );
		assertEquals(cortona.hand.get(1),card1);
	}
//
//	@Test
//	public void testDiscardCard() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testGetPhaseInfo(){
		// Set to phase1 initially
		cortona.setPhaseNumber(1);
		cortona.getPhaseInfo();
		// Phase 1 is 2 sets of 3
		assertSame(cortona.numSets, 2);
		assertSame(cortona.setSize, 3);
		assertSame(cortona.runSize, 0);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0); //This is only used for phase 9 and 10
		// Phase 2 is 1 set of 3 and 1 run of 4
		cortona.setPhaseNumber(2);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,1);
		assertSame(cortona.setSize,3);
		assertSame(cortona.runSize,4);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0);
		// Phase 3 is 1 set of 4 and 1 run of 4 
		cortona.setPhaseNumber(3);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,1);
		assertSame(cortona.setSize,4);
		assertSame(cortona.runSize,4);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0);
		// Phase 4 is 1 run of 7 
		cortona.setPhaseNumber(4);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,0);
		assertSame(cortona.runSize,7);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0);
		// Phase 5 is 1 run of 8
		cortona.setPhaseNumber(5);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,0);
		assertSame(cortona.runSize,8);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0);
		// Phase 6 is 1 run of 9
		cortona.setPhaseNumber(6);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,0);
		assertSame(cortona.runSize,9);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0);
		// Phase 7 is 2 sets of 4
		cortona.setPhaseNumber(7);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,2);
		assertSame(cortona.setSize,4);
		assertSame(cortona.runSize,0);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.secondSetSize, 0);
		// Phase 8 is 7 cards of one color
		cortona.setPhaseNumber(8);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,7);
		assertSame(cortona.colorSets,1);
		assertSame(cortona.runSize,0);
		assertSame(cortona.secondSetSize, 0);
		
		// Phase 9 is 1 set of 5 and 1 set of 2
		cortona.setPhaseNumber(9);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,2);
		assertSame(cortona.setSize,5);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.runSize,0);
		assertSame(cortona.secondSetSize,2);
		// Phase 10 is 1 set of 5 and 1 set of 3
		cortona.setPhaseNumber(10);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,2);
		assertSame(cortona.setSize,5);
		assertSame(cortona.colorSets,0);
		assertSame(cortona.runSize,0);
		assertSame(cortona.secondSetSize,3);


		}

	@Test
	public void testCheckPhase() {
		// check a color set
		Player chief = new Player("Chief");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
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
		cortona.setPhaseNumber(8);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		// check 2 sets with different sizes
		
		tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		tempCard6 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCardW);
		cortona.setPhaseNumber(9);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		// check a set and a run
		cortona.setPhaseNumber(2);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		// check 2 normal sets
		cortona.setPhaseNumber(1);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		// check a run
		cortona.setPhaseNumber(4);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		
	}
	
	@Test 
	public void testPhaseOut(){
		fail("Not yet implemented");
	}

	@Test
	public void testHit() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoTurn() {
		fail("Not yet implemented");
	}

}
