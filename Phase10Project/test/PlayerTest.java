

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
		Card tempCard6 = new Card(3 , Card.cardColor.Red, Card.type.Normal);
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
		Card tempCard2 = new Card(8, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(6, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(7, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(9, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(10, Card.cardColor.Red, Card.type.Normal);
		Card tempCard7 = new Card(10, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard8 = new Card(10, Card.cardColor.Red, Card.type.Normal);
		Card tempCardS = new Card(11, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard9 = new Card(12, Card.cardColor.Blue, Card.type.Normal);
		Card tempCardW = new Card(0, Card.cardColor.Black, Card.type.Wild);
		arbiter.hand.add(tempCard1);	arbiter.hand.add(tempCard2);
		arbiter.hand.add(tempCard3);	arbiter.hand.add(tempCard4);
		arbiter.hand.add(tempCard5);	arbiter.hand.add(tempCard6);
		arbiter.hand.add(tempCard7);	arbiter.hand.add(tempCard8);
		arbiter.hand.add(tempCardS);	arbiter.hand.add(tempCardW);
		arbiter.hand.add(tempCard9);
		arbiter.hand.orderHand();
		arbiter.setPhaseNumber(3);
		arbiter.getPhaseInfo();
		System.out.println(arbiter.numSets);
		System.out.println(arbiter.setSize);
		System.out.println(arbiter.runSize);
		
		assertTrue(arbiter.checkPhase());
	}
		
	@Test
	public void testCheckPhaseNormalSets(){
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(2, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard6 = new Card(4, Card.cardColor.Red, Card.type.Normal);
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
		chief.checkPhase();
		chief.phaseOut();
		
		assertSame(chief.getPhasedOutColoredSet().size(), 7);
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
		cortona.checkPhase();
		cortona.phaseOut();
		assertSame(cortona.getPhasedOutSet().get(0).getNumber(), 1);
		assertSame(cortona.getPhasedOutSet().get(1).getNumber(), 1);
		assertSame(cortona.getPhasedOutSet().get(2).getNumber(), 1);
		assertSame(cortona.getPhasedOutSet().get(3).getNumber(), 1);
		assertSame(cortona.getPhasedOutSet().get(4).getNumber(), 0);
		assertSame(cortona.getPhasedOutSecondSet().get(0).getNumber(), 3);
		assertSame(cortona.getPhasedOutSecondSet().get(1).getNumber(), 3);
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
		arbiter.checkPhase();
		arbiter.phaseOut();
		assertSame(arbiter.getPhasedOutSet().get(0).getNumber(), 1);
		assertSame(arbiter.getPhasedOutSet().get(1).getNumber(), 1);
		assertSame(arbiter.getPhasedOutSet().get(2).getNumber(), 0);
		assertSame(arbiter.getPhasedOutRun().get(0).getNumber(), 2);
		assertSame(arbiter.getPhasedOutRun().get(1).getNumber(), 3);
		assertSame(arbiter.getPhasedOutRun().get(2).getNumber(), 4);
		assertSame(arbiter.getPhasedOutRun().get(3).getNumber(), 5);
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
		drHalsey.checkPhase();
		drHalsey.phaseOut();
		System.out.println(drHalsey.getPhasedOutSet());
		assertSame(drHalsey.getPhasedOutSet().get(0).getNumber(), 1);
		assertSame(drHalsey.getPhasedOutSet().get(1).getNumber(), 1);
		assertSame(drHalsey.getPhasedOutSet().get(2).getNumber(), 0);
		assertSame(drHalsey.getPhasedOutSecondSet().get(0).getNumber(), 2);
		assertSame(drHalsey.getPhasedOutSecondSet().get(1).getNumber(), 2);
		assertSame(drHalsey.getPhasedOutSecondSet().get(2).getNumber(), 2);
		
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
		guiltySpark.phaseOut();
		assertSame(guiltySpark.getPhasedOutRun().get(0).getNumber(),1);
		assertSame(guiltySpark.getPhasedOutRun().get(1).getNumber(),2);
		assertSame(guiltySpark.getPhasedOutRun().get(2).getNumber(),3);
		assertSame(guiltySpark.getPhasedOutRun().get(3).getNumber(),4);
		assertSame(guiltySpark.getPhasedOutRun().get(4).getNumber(),0);
		assertSame(guiltySpark.getPhasedOutRun().get(5).getNumber(),6);
		assertSame(guiltySpark.getPhasedOutRun().get(6).getNumber(),7);
		
	}
	
	@Test
	public void testDiscardDrunk() {
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
		cortona.setStrategy(strategyType.randomPlayer);
		cortona.discard();
		assertSame(cortona.hand.size(),9);
		//
		
		//
	}
	
	@Test
	public void testDiscardPrevent(){
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
		cortona.hand.orderHand();
		cortona.setStrategy(strategyType.preventer);
		Card tempCard69 = cortona.discard();
		assertSame(tempCard69,tempCardS);
		Card tempCard68 = cortona.discard();
		assertSame(tempCard68,tempCard2);
	}
	
	@Test
	public void testDiscardRecklessPlayer(){
		Player cortona = new Player("cortona");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(8, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(10, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(9, Card.cardColor.Green, Card.type.Normal);	
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
		cortona.hand.orderHand();
		cortona.setStrategy(strategyType.recklessPlayer);
		Card tempCard69 = cortona.discard();
		assertSame(tempCard69,tempCardS);
		Card tempCard68 = cortona.discard();
		assertSame(tempCard68,tempCard1);
	}
	
	@Test
	public void testDiscardLowestScorePlayer(){
		Player cortona = new Player("cortona");
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(8, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(10, Card.cardColor.Red, Card.type.Normal);
		Card tempCard5 = new Card(9, Card.cardColor.Green, Card.type.Normal);	
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
		cortona.hand.orderHand();
		cortona.setStrategy(strategyType.lowestScore);
		Card tempCard69 = cortona.discard();
		assertSame(tempCard69,tempCardS);
		Card tempCard68 = cortona.discard();
		assertSame(tempCard68,tempCardW);
	}
	
	
	@Test
	public void testDrawDrunk(){
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
		cortona.setStrategy(strategyType.randomPlayer);
		Card tempDiscard = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),11);
		Card tempDiscard2 = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard2)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),12);
	}
	
	@Test
	public void testDrawLowestScore(){
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
		cortona.setStrategy(strategyType.lowestScore);
		Card tempDiscard = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),11);
		Card tempDiscard2 = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard2)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),12);
	}
	
	@Test
	public void testDrawPrevent(){
		Player cortona = new Player("cortona");
		Deck drawPile = new Deck(deckType.DrawPile);
		drawPile.createDeck();
		
		Deck discardPile = new Deck(deckType.DiscardPile);
		for(int i=0; i < 10; i++){
			discardPile.add(drawPile.remove(  (int)(Math.random()*drawPile.size())  ));
		}
		discardPile.add(new Card(1, Card.cardColor.Red, Card.type.Normal));
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
		cortona.setStrategy(strategyType.preventer);
		Card tempDiscard = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),11);
		Card tempDiscard2 = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard2)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),12);
	}
	
	
	@Test
	public void testDrawReckless(){
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
		cortona.setStrategy(strategyType.recklessPlayer);
		Card tempDiscard = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),11);
		Card tempDiscard2 = discardPile.remove(discardPile.size()-1);
		if(cortona.draw(drawPile,tempDiscard2)){
			System.out.println("Discard pile");
		}
		assertSame(cortona.hand.size(),12);
	}
	
	@Test
	public void testHitColoredSet() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(chief);
		players.add(arbiter);
		players.add(cortona);
		players.add(drHalsey);
		
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
		chief.checkPhase();
		chief.phaseOut();
		
		Card tempCard11 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard12 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard13 = new Card(9, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard14 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard15 = new Card(3, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard16 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard17 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard18 = new Card(6, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard1S = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCard1W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		arbiter.hand.add(tempCard11);	arbiter.hand.add(tempCard12);
		arbiter.hand.add(tempCard13);	arbiter.hand.add(tempCard14);
		arbiter.hand.add(tempCard15);	arbiter.hand.add(tempCard16);
		arbiter.hand.add(tempCard17);	arbiter.hand.add(tempCard18);
		arbiter.hand.add(tempCard1S);	arbiter.hand.add(tempCard1W);
		arbiter.setPhaseNumber(2);
		arbiter.getPhaseInfo();
		arbiter.checkPhase();
		arbiter.phaseOut();
		assertSame(arbiter.hand.size(),3);
		arbiter.hit(players);
		assertSame(arbiter.hand.size(),1);
		
	}
	
	@Test
	public void testHitDiffSets() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(chief);
		players.add(arbiter);
		players.add(cortona);
		players.add(drHalsey);
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
		cortona.checkPhase();
		cortona.phaseOut();
		Card tempCard14 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard15 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard16 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard17 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard12 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard1W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard13 = new Card(9, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard11 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard18 = new Card(3, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard1S = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		arbiter.hand.add(tempCard11);	arbiter.hand.add(tempCard12);
		arbiter.hand.add(tempCard13);	arbiter.hand.add(tempCard14);
		arbiter.hand.add(tempCard15);	arbiter.hand.add(tempCard16);
		arbiter.hand.add(tempCard17);	arbiter.hand.add(tempCard18);
		arbiter.hand.add(tempCard1S);	arbiter.hand.add(tempCard1W);
		arbiter.setPhaseNumber(1);
		arbiter.getPhaseInfo();
		arbiter.checkPhase();
		arbiter.phaseOut();
		assertSame(arbiter.hand.size(),4);
		arbiter.hit(players);
		System.out.println(arbiter.hand);
		assertSame(arbiter.hand.size(),2);
		
	}
	
	@Test
	public void testHitRun() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(chief);
		players.add(arbiter);
		players.add(cortona);
		players.add(drHalsey);
		
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
		drHalsey.hand.add(tempCard1);	drHalsey.hand.add(tempCard2);
		drHalsey.hand.add(tempCard3);	drHalsey.hand.add(tempCard4);
		drHalsey.hand.add(tempCard5);	drHalsey.hand.add(tempCard6);
		drHalsey.hand.add(tempCard7);	drHalsey.hand.add(tempCard8);
		drHalsey.hand.add(tempCardS);	drHalsey.hand.add(tempCardW);
		// check a run
		drHalsey.setPhaseNumber(4);
		drHalsey.getPhaseInfo();
		drHalsey.checkPhase();
		drHalsey.phaseOut();
		

		Card tempCard14 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard15 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard16 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard17 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard12 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard1W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		
		
		Card tempCard13 = new Card(9, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard11 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard18 = new Card(8, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard1S = new Card(0, Card.cardColor.Black, Card.type.Skip);	

		cortona.hand.add(tempCard11);	cortona.hand.add(tempCard12);
		cortona.hand.add(tempCard13);	cortona.hand.add(tempCard14);
		cortona.hand.add(tempCard15);	cortona.hand.add(tempCard16);
		cortona.hand.add(tempCard17);	cortona.hand.add(tempCard18);
		cortona.hand.add(tempCard1S);	cortona.hand.add(tempCard1W);
		cortona.setPhaseNumber(1);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		cortona.phaseOut();
		assertSame(cortona.hand.size(),4);
		cortona.hit(players);
		System.out.println(cortona.hand);
		assertSame(cortona.hand.size(),2);
		
	}
	
	@Test
	public void testHitRunSet() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(chief);
		players.add(arbiter);
		players.add(cortona);
		players.add(drHalsey);
		
		Card tempCard1 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard2 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(9, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard4 = new Card(9, Card.cardColor.Red, Card.type.Normal);
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
		arbiter.checkPhase();
		arbiter.phaseOut();
		

		Card tempCard14 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard15 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard16 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard17 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard12 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard1W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		
		
		Card tempCard13 = new Card(9, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard11 = new Card(7, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard18 = new Card(8, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard1S = new Card(0, Card.cardColor.Black, Card.type.Skip);	

		cortona.hand.add(tempCard11);	cortona.hand.add(tempCard12);
		cortona.hand.add(tempCard13);	cortona.hand.add(tempCard14);
		cortona.hand.add(tempCard15);	cortona.hand.add(tempCard16);
		cortona.hand.add(tempCard17);	cortona.hand.add(tempCard18);
		cortona.hand.add(tempCard1S);	cortona.hand.add(tempCard1W);
		cortona.setPhaseNumber(1);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		cortona.phaseOut();
		System.out.println(cortona.getPhasedOut());
		assertSame(cortona.hand.size(),4);
		cortona.hit(players);
		System.out.println(cortona.hand);
		assertSame(cortona.hand.size(),1);
		
	}
	
	@Test
	public void testHitNormSet() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(chief);
		players.add(arbiter);
		players.add(cortona);
		players.add(drHalsey);
		
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
		drHalsey.checkPhase();
		drHalsey.phaseOut();
		Card tempCard14 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard15 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard16 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard17 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard12 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard1W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		
		
		Card tempCard13 = new Card(1, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard11 = new Card(2, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard18 = new Card(8, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard1S = new Card(0, Card.cardColor.Black, Card.type.Skip);

		cortona.hand.add(tempCard11);	cortona.hand.add(tempCard12);
		cortona.hand.add(tempCard13);	cortona.hand.add(tempCard14);
		cortona.hand.add(tempCard15);	cortona.hand.add(tempCard16);
		cortona.hand.add(tempCard17);	cortona.hand.add(tempCard18);
		cortona.hand.add(tempCard1S);	cortona.hand.add(tempCard1W);
		cortona.setPhaseNumber(1);
		cortona.getPhaseInfo();
		cortona.checkPhase();
		System.out.println(cortona.hand);
		cortona.phaseOut();
		System.out.println(cortona.hand);
		assertSame(cortona.hand.size(),3);
		cortona.hit(players);
		System.out.println(cortona.hand);
		assertSame(cortona.hand.size(),2);
		
	}

	

}