

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
		cortona.drawCard(drawPile);
		cortona.hand.add(new Card(6, Card.cardColor.Red, Card.type.Normal));
		assertSame(cortona.hand.size(), 2 );
	}

	@Test
	public void testDiscardCard() {
		fail("Not yet implemented");
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
