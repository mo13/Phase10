import static org.junit.Assert.*;

import model.*;
import model.Card.cardColor;



import org.junit.Test;

import strategy.*;


public class LowestScorerTest {

	Player cortona = new Player("Cortona");
	@Test
	public void testConstructor() {
		
		LowestScore l = new LowestScore();
		assertSame(l.player, null);
		l.setPlayer(cortona);
		assertSame(l.player, cortona);
		System.out.println(l.strat);
	}
	
	@Test
	public void testDiscard() {
	Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
	Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
	Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);	
	Card tempCard4 = new Card(1, Card.cardColor.Yellow, Card.type.Normal);
	Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
	Card tempCard6 = new Card(7, Card.cardColor.Green, Card.type.Normal);
	Card tempCard7 = new Card(4, Card.cardColor.Blue, Card.type.Normal);	
	Card tempCard8 = new Card(6, Card.cardColor.Yellow, Card.type.Normal);
	Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
	Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
	cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
	cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
	cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
	cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
	cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
	LowestScore l  = new LowestScore();
	l.setPlayer(cortona);
	assertSame(l.player.hand.size(),10);
	l.discard();
	assertSame(l.player.hand.get(0), tempCard1);
	l.discard();
	
	assertSame(l.player.hand.size(),8);
	}
	
	@Test
	public void testDraw() {
		Deck drawPile = new Deck(Deck.deckType.DrawPile);
		drawPile.createDeck();
		Deck discardPile = new Deck(Deck.deckType.DiscardPile);
		Card tempCard1 = new Card(6, cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(8, cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(11, cardColor.Red, Card.type.Normal);
		Card tempCard4 = new Card(2, cardColor.Green, Card.type.Normal);
		Card tempCard5 = new Card(4, cardColor.Red, Card.type. Normal);
		discardPile.add(tempCard1);
		discardPile.add(tempCard2);
		discardPile.add(tempCard3);
		drawPile.add(tempCard4);
		drawPile.add(tempCard5);
		LowestScore l = new LowestScore();
		l.setPlayer(cortona);
		l.draw(drawPile,discardPile);
		assertSame(l.player.hand.get(0),tempCard1);
		System.out.println(l.player.hand);
	}

}

