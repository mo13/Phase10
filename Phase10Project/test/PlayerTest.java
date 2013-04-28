

import static org.junit.Assert.*;

import model.*;
import model.Deck.deckType;

import java.util.*;
import org.junit.Test;

import strategy.*;
import strategy.Strategy.strategyType;

public class PlayerTest {
	
	Player cortona = new Player("Cortona");
	Player chief = new Player("Chief");
	Player arbiter = new Player("Arbiter");
	Player drHalsey	= new Player("drHalsey");
	Player guiltySpark = new Player("guiltySpark");
	
	@Test
	// This test to see if the setStrategy and the getStartegy methods work. 
	public void testGetAndSetStrategy() {
		Strategy preventer = new Preventer();
		cortona.setStrategy(Strategy.strategyType.preventer);
		System.out.println(cortona.getStrategy());
		assertSame(cortona.getStrategy(), Strategy.strategyType.preventer);
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
	public void testCheckPhaseColor() {
		// check a color set
		//the order is red, blue, green, yellow
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
		chief.setPhaseNumber(8);
		chief.getPhaseInfo();
		chief.checkPhase();
		assertTrue(chief.checkPhase());
	}
		// check 2 sets with different sizes
	@Test
	public void testCheckPhasediffSets(){
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
		cortona.setPhaseNumber(9);
		cortona.getPhaseInfo();
		assertTrue(cortona.checkPhase());
	}
	
	@Test
	public void testCheckPhaseSetRun(){
		// check a set and a run
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		arbiter.hand.add(tempCard1);	arbiter.hand.add(tempCard2);
		arbiter.hand.add(tempCard3);	arbiter.hand.add(tempCard4);
		arbiter.hand.add(tempCard5);	arbiter.hand.add(tempCard6);
		arbiter.hand.add(tempCard7);	arbiter.hand.add(tempCard8);
		arbiter.hand.add(tempCardS);	arbiter.hand.add(tempCardW);
		arbiter.setPhaseNumber(2);
		arbiter.getPhaseInfo();
		assertTrue(arbiter.checkPhase());
	}
		
	@Test
	public void testCheckPhaseNormalSets(){
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		drHalsey.hand.add(tempCard1);	drHalsey.hand.add(tempCard2);
		drHalsey.hand.add(tempCard3);	drHalsey.hand.add(tempCard4);
		drHalsey.hand.add(tempCard5);	drHalsey.hand.add(tempCard6);
		drHalsey.hand.add(tempCard7);	drHalsey.hand.add(tempCard8);
		drHalsey.hand.add(tempCardS);	drHalsey.hand.add(tempCardW);
		drHalsey.hand.orderHand();
		// check 2 normal sets
		drHalsey.setPhaseNumber(1);
		drHalsey.getPhaseInfo();
		
		
		assertTrue(drHalsey.checkPhase());
	}
	
	@Test
	public void testCheckPhaseRun(){
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(7, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		guiltySpark.hand.add(tempCard1);	guiltySpark.hand.add(tempCard2);
		guiltySpark.hand.add(tempCard3);	guiltySpark.hand.add(tempCard4);
		guiltySpark.hand.add(tempCard5);	guiltySpark.hand.add(tempCard6);
		guiltySpark.hand.add(tempCard7);	guiltySpark.hand.add(tempCard8);
		guiltySpark.hand.add(tempCardS);	guiltySpark.hand.add(tempCardW);
		// check a run
		guiltySpark.setPhaseNumber(4);
		guiltySpark.getPhaseInfo();
		
		assertTrue(guiltySpark.checkPhase());
		
	}
	
	@Test 
	public void testPhaseOutColor(){
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
		chief.hand.add(tempCard1);	chief.hand.add(tempCard2);
		chief.hand.add(tempCard3);	chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);	chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);	chief.hand.add(tempCard8);
		chief.hand.add(tempCardS);	chief.hand.add(tempCardW);
		chief.setPhaseNumber(8);
		chief.getPhaseInfo();
		ArrayList<Card> done = chief.phaseOut();
		
		assertSame(done.size(), 7);
	}
	
	@Test 
	public void testPhaseOutDiffSets(){
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
		cortona.setPhaseNumber(9);
		cortona.getPhaseInfo();
		ArrayList<Card> done = cortona.phaseOut();
		assertSame(done.get(0).getNumber(), 1);
		assertSame(done.get(1).getNumber(), 1);
		assertSame(done.get(2).getNumber(), 1);
		assertSame(done.get(3).getNumber(), 1);
		assertSame(done.get(4).getNumber(), 0);
		assertSame(done.get(5).getNumber(), 3);
		assertSame(done.get(6).getNumber(), 3);
	}

	@Test
	public void testPhaseOutSetRun(){
		// check a set and a run
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		arbiter.hand.add(tempCard1);	arbiter.hand.add(tempCard2);
		arbiter.hand.add(tempCard3);	arbiter.hand.add(tempCard4);
		arbiter.hand.add(tempCard5);	arbiter.hand.add(tempCard6);
		arbiter.hand.add(tempCard7);	arbiter.hand.add(tempCard8);
		arbiter.hand.add(tempCardS);	arbiter.hand.add(tempCardW);
		arbiter.setPhaseNumber(2);
		arbiter.getPhaseInfo();
		ArrayList<Card> done = arbiter.phaseOut();
		System.out.println(arbiter.hand);
		assertSame(done.get(0).getNumber(), 1);
		assertSame(done.get(1).getNumber(), 1);
		assertSame(done.get(2).getNumber(), 0);
		assertSame(done.get(3).getNumber(), 2);
		assertSame(done.get(4).getNumber(), 3);
		assertSame(done.get(5).getNumber(), 4);
		assertSame(done.get(6).getNumber(), 5);
	}
	
	@Test
	public void testPhaseNormalSets(){
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		drHalsey.hand.add(tempCard1);	drHalsey.hand.add(tempCard2);
		drHalsey.hand.add(tempCard3);	drHalsey.hand.add(tempCard4);
		drHalsey.hand.add(tempCard5);	drHalsey.hand.add(tempCard6);
		drHalsey.hand.add(tempCard7);	drHalsey.hand.add(tempCard8);
		drHalsey.hand.add(tempCardS);	drHalsey.hand.add(tempCardW);
		drHalsey.hand.orderHand();
		// check 2 normal sets
		drHalsey.setPhaseNumber(1);
		drHalsey.getPhaseInfo();
		ArrayList<Card> done = drHalsey.phaseOut();
		assertSame(done.get(0).getNumber(), 1);
		assertSame(done.get(1).getNumber(), 1);
		assertSame(done.get(2).getNumber(), 2);
		assertSame(done.get(3).getNumber(), 2);
		assertSame(done.get(4).getNumber(), 2);
		assertSame(done.get(5).getNumber(), 0);
	}
	@Test
	public void testPhaseOutRun(){
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(7, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		guiltySpark.hand.add(tempCard1);	guiltySpark.hand.add(tempCard2);
		guiltySpark.hand.add(tempCard3);	guiltySpark.hand.add(tempCard4);
		guiltySpark.hand.add(tempCard5);	guiltySpark.hand.add(tempCard6);
		guiltySpark.hand.add(tempCard7);	guiltySpark.hand.add(tempCard8);
		guiltySpark.hand.add(tempCardS);	guiltySpark.hand.add(tempCardW);
		// check a run
		guiltySpark.setPhaseNumber(4);
		guiltySpark.getPhaseInfo();
		guiltySpark.checkPhase();
		ArrayList<Card>done = guiltySpark.phaseOut();
		assertSame(done.get(0).getNumber(),1);
		assertSame(done.get(1).getNumber(),2);
		assertSame(done.get(2).getNumber(),3);
		assertSame(done.get(3).getNumber(),4);
		assertSame(done.get(4).getNumber(),0);
		assertSame(done.get(5).getNumber(),6);
		assertSame(done.get(6).getNumber(),7);
		
	}
	
	@Test
	public void testDiscard() {
		Player cortona = new Player("cortona");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
		cortona.setStrategy(strategyType.drunkPlayer);
		cortona.discard();
		assertSame(cortona.hand.size(),9);
		//
		cortona.hand.add(tempCard5);
		cortona.hand.orderHand();
		cortona.setStrategy(strategyType.preventer);
		System.out.println(cortona.hand.toString());
		Card tempCard69 = cortona.discard();
		assertSame(tempCard69,tempCard2);
		Card tempCard68 = cortona.discard();
		assertSame(tempCard68,tempCard5);
		//
	}
	
	@Test
	public void testDraw(){
		Player cortona = new Player("cortona");
		Deck drawPile = new Deck(deckType.DrawPile);
		drawPile.createDeck();
		
		Deck discardPile = new Deck(deckType.DiscardPile);
		for(int i=0; i < 10; i++){
			discardPile.add(drawPile.remove(  (int)(Math.random()*drawPile.size())  ));
		}
		
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		cortona.hand.add(tempCard1);	cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);	cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);	cortona.hand.add(tempCard6);
		cortona.hand.add(tempCard7);	cortona.hand.add(tempCard8);
		cortona.hand.add(tempCardS);	cortona.hand.add(tempCardW);
		cortona.setStrategy(strategyType.drunkPlayer);
		System.out.println(cortona.hand.size());
		cortona.draw(drawPile, discardPile);
		System.out.println(cortona.hand.size());
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