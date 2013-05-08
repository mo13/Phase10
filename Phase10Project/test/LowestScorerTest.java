import static org.junit.Assert.*;

import model.*;
import model.Card.cardColor;



import org.junit.Test;

import strategy.*;


public class LowestScorerTest {

	Player cortona = new Player("Cortona");
	@Test
	public void testConstructor() {
		
		OldLowestScore l = new OldLowestScore();
		assertSame(l.player, null);
		l.setPlayer(cortona);
		assertSame(l.player, cortona);
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
	OldLowestScore l  = new OldLowestScore();
	l.setPlayer(cortona);
	assertSame(10, l.player.hand.size());
	Card tempCard12 = l.discard();
	assertSame(tempCard12.getNumber(), 0);
	Card tempCard13 = l.discard();
	assertSame(tempCard13.getNumber(), 0);
	assertSame(8, l.player.hand.size());
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
		discardPile.add(tempCard4);
		discardPile.add(tempCard5);
		OldLowestScore l = new OldLowestScore();
		l.setPlayer(cortona);
		Card tempCard = discardPile.draw();
		l.draw(drawPile,tempCard);
		assertSame(tempCard5, cortona.hand.get(cortona.hand.size()-1));
	}

}

