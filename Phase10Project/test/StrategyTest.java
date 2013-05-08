import static org.junit.Assert.*;

import org.junit.Test;

import strategy.*;
import strategy.Strategy.strategyType;
import model.*;

public class StrategyTest {

	@Test
	public void testAnalyzeHandForNormalSet(){
		Player cortona = new Player("cortona");
		cortona.setStrategy(strategyType.oldRecklessPlayer);
		Strategy oldReckless = new OldReckless();
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
		oldReckless.setPlayer(cortona);
		oldReckless.analyzeHand();
		assertSame(oldReckless.tempHand.get(0), tempCard1);
		assertSame(oldReckless.tempHand.get(1), tempCard8);
		assertSame(oldReckless.tempHand.get(2), tempCard4);
		assertSame(oldReckless.tempHand.get(3), tempCardS);
	}
	
	@Test
	public void testAnalyzeHandForColoredSet(){
		Player cortona = new Player("cortona");
		cortona.setStrategy(strategyType.oldRecklessPlayer);
		Strategy oldReckless = new OldReckless();
		Card tempCard1 = new Card(1, Card.cardColor.Yellow, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(3, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
		cortona.setPhaseNumber(8);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		oldReckless.setPlayer(cortona);
		oldReckless.analyzeHand();	
		assertSame(oldReckless.tempHand.get(0), tempCard2);
		assertSame(oldReckless.tempHand.get(1), tempCard5);
		assertSame(oldReckless.tempHand.get(2), tempCardS);
		
	}
	
	@Test
	public void testAnalyzeHandForRunAndSet(){
		Player cortona = new Player("cortona");
		cortona.setStrategy(strategyType.oldRecklessPlayer);
		Strategy oldReckless = new OldReckless();
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(3, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(7, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
		cortona.setPhaseNumber(2);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		oldReckless.setPlayer(cortona);
		oldReckless.analyzeHand();
		assertSame(oldReckless.tempHand.get(0), tempCard3);
		assertSame(oldReckless.tempHand.get(1), tempCard8);
		assertSame(oldReckless.tempHand.get(2), tempCard5);
		assertSame(oldReckless.tempHand.get(3), tempCardS);
	}

}
