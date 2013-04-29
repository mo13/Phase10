import static org.junit.Assert.*;
import org.junit.Test;
import strategy.*;
import model.*;
import model.Card.cardColor;

public class RecklessPlayerTest {
	Player cortona = new Player("cortona");
	@Test
	public void testSetPlayer() {
		RecklessPlayer r = new RecklessPlayer();
		assertSame(r.player, null);
		r.setPlayer(cortona);
		assertSame(r.player, cortona);
	}
	
	
	@Test
	public void testDiscard() {
	Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
	Card tempCard2 = new Card(10, Card.cardColor.Blue, Card.type.Normal);
	Card tempCard3 = new Card(12, Card.cardColor.Red, Card.type.Normal);	
	Card tempCard4 = new Card(11, Card.cardColor.Red, Card.type.Normal);
	Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
	Card tempCard6 = new Card(7, Card.cardColor.Red, Card.type.Normal);
	Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
	Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
	Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
	Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
	cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
	cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
	cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
	cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
	cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
	RecklessPlayer r = new RecklessPlayer();
	r.setPlayer(cortona);
	Card tempCard11 = r.discard();
	assertSame(tempCard11,tempCard1);
	Card tempCard12 = r.discard();
	assertSame(tempCard12,tempCard5);
	}
	
	@Test
	public void testDraw() {
		Deck drawPile = new Deck(Deck.deckType.DrawPile);
		drawPile.createDeck();
		Deck discardPile = new Deck(Deck.deckType.DiscardPile);
		Card tempCard1 = new Card(12, cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(8, cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(10, cardColor.Red, Card.type.Normal);
		discardPile.add(tempCard1);
		discardPile.add(tempCard2);
		discardPile.add(tempCard3);
		RecklessPlayer r = new RecklessPlayer();
		r.setPlayer(cortona);
		r.draw(drawPile,discardPile);
		assertSame(r.player.hand.get(0),tempCard3);
		r.draw(drawPile, discardPile);
		assertSame(r.player.hand.size(), 2);
		r.draw(drawPile, discardPile);
		assertSame(r.player.hand.size(),3);
		assertSame(discardPile.get(1), tempCard2);
	}

}
