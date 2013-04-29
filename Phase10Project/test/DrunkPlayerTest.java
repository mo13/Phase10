import static org.junit.Assert.*;

import strategy.*;

import org.junit.Test;
import model.*;
public class DrunkPlayerTest {
	Player cortona = new Player("Cortona");
	
	@Test
	public void testSetPlayer() {
		DrunkPlayer d = new DrunkPlayer();
		assertSame(d.player, null);
		d.setPlayer(cortona);
		assertSame(d.player, cortona);
		System.out.println(d.strat);
	}
	
	
	@Test
	public void testDiscard() {
	Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
	Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
	Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
	Card tempCard4 = new Card(1, Card.cardColor.Red, Card.type.Normal);
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
	DrunkPlayer d = new DrunkPlayer();
	d.setPlayer(cortona);
	assertSame(d.player.hand.size(),10);
	d.discard();
	assertSame(d.player.hand.size(),9);
	d.discard();
	assertSame(d.player.hand.size(),8);
	}
	
	@Test
	public void testDraw() {
		Deck drawPile = new Deck(Deck.deckType.DrawPile);
		drawPile.createDeck();
		Deck discardPile = new Deck(Deck.deckType.DiscardPile);
		for(int i= 0; i< 10; i++){
			discardPile.add(drawPile.remove((int)(Math.random()*drawPile.size())));
		}
		DrunkPlayer d = new DrunkPlayer();
		d.setPlayer(cortona);
		d.draw(drawPile,discardPile);
		assertSame(d.player.hand.size(),1);
		d.draw(drawPile, discardPile);
		assertSame(d.player.hand.size(), 2);
		d.draw(drawPile, discardPile);
		assertSame(d.player.hand.size(),3);
		System.out.println(d.player.hand);
	}

}