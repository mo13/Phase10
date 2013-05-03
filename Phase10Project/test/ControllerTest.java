

import static org.junit.Assert.*;

import java.util.ArrayList;

import view.*;
import strategy.*;
import strategy.Strategy.strategyType;
import model.*;

import org.junit.Test;

import controller.Controller;


import strategy.*;
import view.*;

public class ControllerTest {
	
	
	
	
	Player cortona = new Player("Cortona");
	Player chief = new Player("Chief");
	Player johnson = new Player("Johnson");
	Player arbiter = new Player("Arbiter");
	
	
	@Test
	public void testDealCards(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller gameController = new Controller(model, view);
		Deck drawpile = new Deck(Deck.deckType.DrawPile);
		gameController.setPlayerOrder();
		gameController.dealCards();
		for(int i = 0; i < gameController.playerList.size(); i ++){
			assertEquals("10",gameController.playerList.get(i).getHandSize());
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
//		cortona = new Player("cortona");
//		chief = new Player("chief");
//		arbiter = new Player("arbiter");
//		johnson = new Player("johnson");
//		
//		ArrayList<Player> playerList = new ArrayList<Player>();
//		playerList.add(cortona);
//		playerList.add(chief);
//		playerList.add(johnson);
//		playerList.add(arbiter);
//		
//		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);
//		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);
//		Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);
//		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);
//		Card tempCard5 = new Card(0, Card.cardColor.Black, Card.type.Skip);
//		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);
//		
//		Card tempCard7 = new Card(12, Card.cardColor.Green, Card.type.Normal);
//		Card tempCard8 = new Card(0, Card.cardColor.Black, Card.type.Wild);
//		Card tempCard9 = new Card(6, Card.cardColor.Red, Card.type.Normal);
//		Card tempCard10 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
//		
//		cortona.hand.add(tempCard1);
//		cortona.hand.add(tempCard2);
//		cortona.hand.add(tempCard3);
//		cortona.hand.add(tempCard4);
//		cortona.hand.add(tempCard5);
//		cortona.hand.add(tempCard6);
//		cortona.hand.orderHand();
//		
//		chief.hand.add(tempCard7);
//		chief.hand.add(tempCard8);
//		chief.hand.add(tempCard9);
//		chief.hand.add(tempCard10);
//		chief.hand.orderHand();
//		
//		johnson.hand.add(tempCard2);
//		johnson.hand.add(tempCard4);
//		johnson.hand.add(tempCard6);
//		johnson.hand.add(tempCard8);
//		johnson.hand.add(tempCard10);
//		johnson.hand.orderHand();
//		gameController.scoreRound(playerList);
//		assertSame(55,chief.getScore() );
//		assertSame(45,cortona.getScore() );
//		assertSame(70,johnson.getScore());
//		assertSame(0,arbiter.getScore());
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
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		
		
		controller.setPlayerOrder();
		controller.dealCards();
		System.out.println(controller.drawPile.size());
		for(int i =0; i < 65; i++){
			controller.discardPile.add(controller.drawPile.remove((int)(Math.random()*controller.drawPile.size()-1)));
		}
		System.out.println(controller.drawPile.size());
		
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		controller.playerList.get(0).setStrategy(strategyType.preventer);
		controller.playerList.get(1).setStrategy(strategyType.randomPlayer);
		controller.playerList.get(2).setStrategy(strategyType.recklessPlayer);
		controller.playerList.get(3).setStrategy(strategyType.lowestScore);
		controller.drawCard(0);
		
		controller.drawCard(1);
		
		controller.drawCard(2);
		
		controller.drawCard(3);
		
	}
	@Test
	public void testemptyHand(){
		fail("Not yet implemented");
	}
	@Test
	public void testdraw(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.dealCards();
		
		for(int i =0; i < 25; i++){
			controller.discardPile.add(controller.drawPile.remove((int)(Math.random()*controller.drawPile.size()-1)));
		}
		
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		controller.playerList.get(0).setStrategy(strategyType.preventer);
		controller.playerList.get(0).setStrategy(strategyType.randomPlayer);
		controller.playerList.get(0).setStrategy(strategyType.recklessPlayer);
		controller.playerList.get(0).setStrategy(strategyType.lowestScore);
		int i = 0;
		// seeing when i have discard so when i do use it make sure not to get another one.
		while (i < 3){
			for(Integer t : playerList){
				controller.drawCard(t);
			}
			i++;
		}
		
		
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
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller gameController = new Controller(model, view);
		gameController.setPlayerOrder();
		gameController.setStrategy(0, strategyType.lowestScore);
		gameController.setStrategy(1, strategyType.recklessPlayer);
		gameController.setStrategy(2, strategyType.preventer);
		gameController.setStrategy(3, strategyType.randomPlayer);
		assertSame(gameController.playerList.get(0).getStrategy(), strategyType.lowestScore);
		assertSame(gameController.playerList.get(1).getStrategy(), strategyType.recklessPlayer);
		assertSame(gameController.playerList.get(2).getStrategy(), strategyType.preventer);
		assertSame(gameController.playerList.get(3).getStrategy(), strategyType.randomPlayer);
	}
	
	
	@Test
	public void testSetPlayerOrder() {
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller gameController = new Controller(model, view);
		gameController.setPlayerOrder();
		assertSame(gameController.playerList.get(3).getName(), "Cortona");
		assertSame(gameController.playerList.get(0).getName(), "Master Chief");
		assertSame(gameController.playerList.get(1).getName(), "Sgt. Johnson");
		assertSame(gameController.playerList.get(2).getName(), "The Arbiter");
		
	}
	

}