import static org.junit.Assert.*;

import model.*;

import org.junit.Test;

import strategy.*;
import strategy.Strategy.strategyType;
import java.util.*;


public class NewLowestScoreStrategy {

Player cortona = new Player("Cortona");
	
	
	@Test
	public void testDiscard() {
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(3, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(11, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(7, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(7, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
		Strategy newLowScore = new newLowestScore();
		cortona.setStrategy(strategyType.newLowestScore);
		newLowScore.setPlayer(cortona);
		cortona.setPhaseNumber(1);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		System.out.println(cortona.hand)	;
		newLowScore.discard();
		assertTrue(newLowScore.tempHand.indexOf(tempCard2)== -1);
		assertTrue(cortona.hand.indexOf(tempCard2)!= -1);
		newLowScore.discard();
		System.out.println(cortona.hand)	;
		assertTrue(newLowScore.tempHand.indexOf(tempCard2)== -1);
		assertTrue(cortona.hand.indexOf(tempCard2)!= -1);
		cortona.discard();
		System.out.println(cortona.hand)	;
		assertTrue(newLowScore.tempHand.indexOf(tempCard2)== -1);
		assertTrue(cortona.hand.indexOf(tempCard2)!= -1);
		
	}

	@Test
	public void testDraw() {
		Deck drawPile = new Deck(Deck.deckType.DrawPile);
		drawPile.createDeck();
		Deck discardPile = new Deck(Deck.deckType.DiscardPile);
		Card tempCard1 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(8, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, Card.cardColor.Red, Card.type.Normal);
		discardPile.add(tempCard1);
		discardPile.add(tempCard2);
		discardPile.add(tempCard3);
		
		Strategy p = new newRed();
		Card tempCard = discardPile.draw();
		p.setPlayer(cortona);
		p.draw(drawPile, tempCard);
		assertSame(p.player.hand.get(0),tempCard3);
		Card tempDiscard2 = discardPile.draw();
		p.draw(drawPile, tempDiscard2);
		assertSame(p.player.hand.size(), 2);
		Card tempDiscard3 = discardPile.draw();
		p.draw(drawPile, tempDiscard3);
		assertSame(p.player.hand.size(),3);
	}

	@Test
	public void testAnalyzeHand() {
		Player cortona = new Player("cortona");
		cortona.setStrategy(strategyType.newRed);
		Strategy newRed = new newRed();
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(3, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(11, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(7, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(7, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);

		cortona.setPhaseNumber(1);

		cortona.getPhaseInfo();
		cortona.checkPhase();
		newRed.setPlayer(cortona);
		newRed.analyzeHand();
		assertSame(newRed.tempHand.get(0), tempCard1);
		assertSame(newRed.tempHand.get(1), tempCard8);
		assertSame(newRed.tempHand.get(2), tempCard4);
		assertSame(newRed.tempHand.get(3), tempCardS);
	}

}
