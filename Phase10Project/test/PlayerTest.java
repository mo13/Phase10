

import static org.junit.Assert.*;

import org.junit.Test;
import Model.*;
import Strategy.*;

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
		cortona.getPhaseInfo();
		// Phase 1 is 2 sets of 3
		assertSame(cortona.numSets, 2);
		assertSame(cortona.setSize, 3);
		// Phase 2 is 1 set of 3 and 1 run of 4
		cortona.setPhaseNumber(2);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,1);
		assertSame(cortona.setSize,3);
		assertSame(cortona.runSize,4);
		// Phase 3 is 1 set of 4 and 1 run of 4 
		cortona.setPhaseNumber(3);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,1);
		assertSame(cortona.setSize,4);
		assertSame(cortona.runSize,4);
		// Phase 4 is 1 run of 7 
		cortona.setPhaseNumber(4);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,0);
		assertSame(cortona.runSize,7);
		// Phase 5 is 1 run of 8
		cortona.setPhaseNumber(5);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,0);
		assertSame(cortona.runSize,8);
		// Phase 6 is 1 run of 9
		cortona.setPhaseNumber(6);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,0);
		assertSame(cortona.setSize,0);
		assertSame(cortona.runSize,9);
		// Phase 7 is 2 sets of 4
		cortona.setPhaseNumber(7);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,2);
		assertSame(cortona.setSize,4);
		assertSame(cortona.runSize,0);
		// Phase 8 is 7 cards of one color
		cortona.setPhaseNumber(8);
		cortona.getPhaseInfo();
		assertSame(cortona.colorSets,1);
		assertSame(cortona.setSize,7);
		// Phase 9 is 1 set of 5 and 1 set of 2
		cortona.setPhaseNumber(9);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,2);
		assertSame(cortona.setSize,5);
		assertSame(cortona.secondSetSize,2);
		// Phase 10 is 1 set of 5 and 1 set of 3
		cortona.setPhaseNumber(10);
		cortona.getPhaseInfo();
		assertSame(cortona.numSets,2);
		assertSame(cortona.setSize,5);
		assertSame(cortona.secondSetSize,3);


		}

	@Test
	public void testPhaseOut() {
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
