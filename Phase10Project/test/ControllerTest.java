

import static org.junit.Assert.*;

import view.*;
import strategy.*;
import model.*;

import org.junit.Test;


import strategy.*;
import view.*;

public class ControllerTest {
	
	Player cortona = new Player("Cortona");
	Player chief = new Player("Chief");
	Player johnson = new Player("Johnson");
	Player arbiter = new Player("Arbiter");

	@Test
	public void testsetPlayerArea(){
		fail("Not yet implemented");
	}
	@Test
	public void testPhaseTrack(){
		fail("Not yet implemented");
	}
	@Test
	public void testdisplayScore(){
		fail("Not yet implemented");
	}
	@Test
	public void scoreRound(){
		Hand hand = new Hand();
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard5 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);
		
		Card tempCard7 = new Card(12, Card.cardColor.Green, Card.type.Normal);
		Card tempCard8 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard9 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCard10 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		
		cortona.hand.add(tempCard1);
		cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);
		cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);
		cortona.hand.add(tempCard6);
		cortona.hand.orderHand();
		
		chief.hand.add(tempCard7);
		chief.hand.add(tempCard8);
		chief.hand.add(tempCard9);
		chief.hand.add(tempCard10);
		chief.hand.orderHand();
		
//		assertSame(cortona.hand.get(0), );
//		assertSame(chief.hand.get(1), );
		assertSame(johnson.hand.get(2), tempCard1);
		assertSame(arbiter.hand.get(3), tempCard2);
	}
	@Test
	public void testexitRound(){
		fail("Not yet implemented");
	}
	@Test
	public void testshowOrder(){
		fail("Not yet implemented");
	}
	@Test
	public void testresetOrder(){
		fail("Not yet implemented");
	}
	@Test
	public void testsetupBoard(){
		fail("Not yet implemented");
	}
	@Test
	public void testresetDrawPile(){
		fail("Not yet implemented");
	}
	@Test
	public void testemptyHand(){
		fail("Not yet implemented");
	}
	@Test
	public void testdraw(){
		fail("Not yet implemented");
	}
	@Test
	public void testplayPhase(){
		fail("Not yet implemented");
	}
	@Test
	public void testexitGame(){
		fail("Not yet implemented");
	}
	@Test
	public void testCheckPhase(){
		fail("Not yet implemented");
	}
	@Test 
	public void testhit(){
		fail("Not yet implemented");
	}
	@Test 
	public void testcheckHit(){
		fail("Not yet implemented");
	}
	@Test 
	public void testdiscard(){
		fail("Not yet implemented");
	}
	@Test 
	public void testturn(){
		fail("Not yet implemented");
	}
	@Test
	public void testfinishTurn(){
		fail("Not yet implemented");
	}
	@Test
	public void setStrategy(){
		fail("Not yet implemented");
	}
	@Test
	public void testSetPlayerOrder() {
		fail("Not yet implemented");
	}
	@Test
	public void testDealCards(){
		fail("Not yet implemented");
	}

}
