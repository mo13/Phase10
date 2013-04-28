import static org.junit.Assert.*;

import org.junit.Test;


import model.*;
import model.Card.cardColor;
import strategy.*;
public class PreventerStrategy {
	Player cortona = new Player("Cortona");
	@Test
	public void testConstructor() {
		
		Preventer p = new Preventer();
		assertSame(p.player, null);
		p.setPlayer(cortona);
		assertSame(p.player, cortona);
		System.out.println(p.strat);
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
	Preventer p  = new Preventer();
	p.setPlayer(cortona);
	assertSame(p.player.hand.size(),10);
	Card tempCard = p.discard();
	assertSame(p.player.hand.get(0), tempCard1);
	p.discard();
	assertSame(p.player.hand.size(),8);
	}
	
	@Test
	public void testDraw() {
		Deck drawPile = new Deck(Deck.deckType.DrawPile);
		drawPile.createDeck();
		Deck discardPile = new Deck(Deck.deckType.DiscardPile);
		Card tempCard1 = new Card(6, cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(8, cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, cardColor.Red, Card.type.Normal);
		discardPile.add(tempCard1);
		discardPile.add(tempCard2);
		discardPile.add(tempCard3);
		Preventer p = new Preventer();
		p.setPlayer(cortona);
		p.draw(drawPile,discardPile);
		assertSame(p.player.hand.get(0),tempCard3);
		p.draw(drawPile, discardPile);
		assertSame(p.player.hand.size(), 2);
		p.draw(drawPile, discardPile);
		assertSame(p.player.hand.size(),3);
		System.out.println(p.player.hand);
	}

}
