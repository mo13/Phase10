

import static org.junit.Assert.*;

import java.util.ArrayList;

import view.*;
import strategy.*;
import model.*;

import org.junit.Test;

import controller.Controller;


import strategy.*;
import view.*;

public class ControllerTest {
	
	Controller gameController = new Controller();
	
	
	
	Player cortona = new Player("Cortona");
	Player chief = new Player("Chief");
	Player johnson = new Player("Johnson");
	Player arbiter = new Player("Arbiter");
	
	
	@Test
	public void testDealCards(){
		Deck drawpile = new Deck(Deck.deckType.DrawPile);
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		gameController.dealCards(playerList);
		for(int i = 0; i < playerList.size(); i ++){
			assertSame(10,playerList.get(i).hand.size());
		}
		
	}

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
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		
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
		
		johnson.hand.add(tempCard2);
		johnson.hand.add(tempCard4);
		johnson.hand.add(tempCard6);
		johnson.hand.add(tempCard8);
		johnson.hand.add(tempCard10);
		johnson.hand.orderHand();
		gameController.scoreRound(playerList);
		
		assertSame(65,cortona.getScore() );
		assertSame(45,chief.getScore() );
		assertSame(70,johnson.getScore());
		assertSame(0,arbiter.getScore());
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
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard5 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);
		Card tempCard7 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		
		
		cortona.hand.add(tempCard1);
		cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);
		cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);
		cortona.hand.add(tempCard6);
		cortona.hand.orderHand();
		
		chief.hand.add(tempCard7);
		chief.hand.orderHand();
		
		assertSame(6,cortona.hand.size() );
		assertSame(1,chief.hand.size() );
		assertSame(0,johnson.hand.size());
		assertSame(0,arbiter.hand.size());
		
		
	}
	@Test
	public void testdraw(){
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(4, Card.cardColor.Green, Card.type.Normal);
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard5 = new Card(3, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);
		Card tempCard7 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard8 = new Card(11, Card.cardColor.Red, Card.type.Normal);
		Card tempCard9 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard10 = new Card(9, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard11 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard12 = new Card(9, Card.cardColor.Red, Card.type.Normal);
		
		
		cortona.hand.add(tempCard1);
		cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);
		cortona.hand.orderHand();
		
		
		chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);
		chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);
		chief.hand.orderHand();
		
		johnson.hand.add(tempCard8);
		johnson.hand.add(tempCard9);
		johnson.hand.add(tempCard10);
		johnson.hand.orderHand();
		
		arbiter.hand.add(tempCard11);
		arbiter.hand.add(tempCard12);
		arbiter.hand.orderHand();
		
		
		assertSame(4,cortona.hand.size() );
		assertSame(5,chief.hand.size() );
		assertSame(4,johnson.hand.size());
		assertSame(3,arbiter.hand.size());
		
	}
	@Test
	public void testplayPhase(){
		fail("Not yet implemented");
		
	}
	@Test
	public void testexitGame(){
		fail(" Not Yet Implemented" );
	}
	@Test
	public void testCheckPhase(){
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
	}
	@Test 
	public void testhit(){
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
	}
	@Test 
	public void testcheckHit(){
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
	}
	@Test 
	public void testdiscard(){
		
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(4, Card.cardColor.Green, Card.type.Normal);
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard5 = new Card(3, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);
		Card tempCard7 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard8 = new Card(11, Card.cardColor.Red, Card.type.Normal);
		Card tempCard9 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard10 = new Card(9, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard11 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard12 = new Card(9, Card.cardColor.Red, Card.type.Normal);
		
		
		cortona.hand.add(tempCard1);
		cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);
		cortona.hand.orderHand();
		
		
		chief.hand.add(tempCard4);
		chief.hand.add(tempCard5);
		chief.hand.add(tempCard6);
		chief.hand.add(tempCard7);
		chief.hand.orderHand();
		
		johnson.hand.add(tempCard8);
		johnson.hand.add(tempCard9);
		johnson.hand.add(tempCard10);
		johnson.hand.orderHand();
		
		arbiter.hand.add(tempCard11);
		arbiter.hand.add(tempCard12);
		arbiter.hand.orderHand();
		
		
		assertSame(2,cortona.hand.size() );
		assertSame(3,chief.hand.size() );
		assertSame(2,johnson.hand.size());
		assertSame(1,arbiter.hand.size());
	}
	
	@Test
	public void testcheckRound(){
		
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
		
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard5 = new Card(0, Card.cardColor.Black, Card.type.Skip);
		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);
		Card tempCard7 = new Card(0, Card.cardColor.Black, Card.type.Wild);
		
		
		cortona.hand.add(tempCard1);
		cortona.hand.add(tempCard2);
		cortona.hand.add(tempCard3);
		cortona.hand.add(tempCard4);
		cortona.hand.add(tempCard5);
		cortona.hand.add(tempCard6);
		cortona.hand.orderHand();
		
		chief.hand.add(tempCard7);
		chief.hand.orderHand();
		
		assertSame(6,cortona.hand.size() );
		assertSame(1,chief.hand.size() );
		assertSame(0,johnson.hand.size());
		assertSame(0,arbiter.hand.size());
		
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
		cortona = new Player("cortona");
		chief = new Player("chief");
		johnson = new Player("johnson");
		arbiter = new Player("arbiter");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(cortona);
		playerList.add(chief);
		playerList.add(johnson);
		playerList.add(arbiter);
	}
	@Test
	public void testSetPlayerOrder() {
		ArrayList<Player> playerList = gameController.setPlayerOrder(cortona, johnson, chief, arbiter);
		assertSame(playerList.get(0), cortona);
		assertSame(playerList.get(1), johnson);
		assertSame(playerList.get(2), chief);
		assertSame(playerList.get(3), arbiter);
	}
	

}
