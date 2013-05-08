

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
	public void testDoGame(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.doGame();
	}

	@Test
	public void scoreRound(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		System.out.println("The original discard pile" + controller.getTopDiscard());
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		
		Card tempCard1 = new Card(5, Card.cardColor.Red, Card.type.Normal);   //5
		Card tempCard2 = new Card(11, Card.cardColor.Blue, Card.type.Normal);   //10
		Card tempCard3 = new Card(1, Card.cardColor.Green, Card.type.Normal);   //5
		Card tempCard4 = new Card(0, Card.cardColor.Black, Card.type.Wild);     // 25
		Card tempCard5 = new Card(0, Card.cardColor.Black, Card.type.Skip);    // 15
		Card tempCard6 = new Card(9, Card.cardColor.Green, Card.type.Normal);     //5
		
		Card tempCard7 = new Card(12, Card.cardColor.Green, Card.type.Normal);    //10
		Card tempCard8 = new Card(0, Card.cardColor.Black, Card.type.Wild);     //25
		Card tempCard9 = new Card(6, Card.cardColor.Red, Card.type.Normal);     //5
		Card tempCard10 = new Card(2, Card.cardColor.Blue, Card.type.Normal);     //5
		
		controller.playerList.get(0).hand.add(tempCard7);
		controller.playerList.get(0).hand.add(tempCard8);
		controller.playerList.get(0).hand.add(tempCard9);
		controller.playerList.get(0).hand.add(tempCard10);
		controller.playerList.get(0).hand.orderHand();
		
		controller.playerList.get(1).hand.add(tempCard2);
		controller.playerList.get(1).hand.add(tempCard4);
		controller.playerList.get(1).hand.add(tempCard6);
		controller.playerList.get(1).hand.add(tempCard8);
		controller.playerList.get(1).hand.add(tempCard10);
		controller.playerList.get(1).hand.orderHand();
		
		controller.playerList.get(3).hand.add(tempCard1);
		controller.playerList.get(3).hand.add(tempCard2);
		controller.playerList.get(3).hand.add(tempCard3);
		controller.playerList.get(3).hand.add(tempCard4);
		controller.playerList.get(3).hand.add(tempCard5);
		controller.playerList.get(3).hand.add(tempCard6);
		controller.playerList.get(3).hand.orderHand();
		
		controller.roundDone=true;
		controller.scoreRound();
		assertSame(45,controller.playerList.get(0).getScore() );
		assertSame(65,controller.playerList.get(3).getScore() );
		assertSame(70,controller.playerList.get(1).getScore());
		assertSame(0,arbiter.getScore());
	}

	@Test
	public void testresetDrawPile(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.dealCards();
		for(int i =0; i < 65; i++){
			controller.discardPile.add(controller.drawPile.remove((int)(Math.random()*controller.drawPile.size()-1)));
		}
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		controller.playerList.get(0).setStrategy(strategyType.oldRed);
		controller.playerList.get(1).setStrategy(strategyType.randomPlayer);
		controller.playerList.get(2).setStrategy(strategyType.oldRecklessPlayer);
		controller.playerList.get(3).setStrategy(strategyType.oldLowestScore);
		controller.drawCard(0);
		controller.drawCard(1);
		controller.drawCard(2);
		controller.drawCard(3);
	}
	@Test
	public void testemptyHand(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.dealCards();
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		for(Integer i : playerList){
			assertSame(controller.playerList.get(i).hand.size(), 10	);
		}
		controller.emptyHands();
		for(Integer i : playerList){
			assertSame(controller.playerList.get(i).hand.size(), 0	);
		}
		
	}
	@Test
	public void testdraw(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.dealCards();
		System.out.println("The original discard pile" + controller.getTopDiscard());
		for(int i =0; i < 25; i++){
			controller.discardPile.add(controller.drawPile.remove((int)(Math.random()*controller.drawPile.size()-1)));
		}
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		controller.playerList.get(0).setStrategy(strategyType.oldRed);
		controller.playerList.get(1).setStrategy(strategyType.randomPlayer);
		controller.playerList.get(2).setStrategy(strategyType.oldRecklessPlayer);
		controller.playerList.get(3).setStrategy(strategyType.oldLowestScore);
		int i = 0;	
		while (i < 3){
			for(Integer t : playerList){
				controller.drawCard(t);
				System.out.println("after the draw card part" + controller.getTopDiscard());
				controller.discard(t);
				System.out.println("after the discard part" + controller.getTopDiscard());
			}
			i++;
		}
	}

	@Test
	public void testCheckPhase(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.dealCards();
		controller.drawPile.shuffle();

		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		controller.playerList.get(0).setStrategy(strategyType.oldRed);
		controller.playerList.get(1).setStrategy(strategyType.randomPlayer);
		controller.playerList.get(2).setStrategy(strategyType.oldRecklessPlayer);
		controller.playerList.get(3).setStrategy(strategyType.oldLowestScore);
		controller.playerList.get(0).setPhaseNumber(3);
		controller.playerList.get(1).setPhaseNumber(4);
		controller.playerList.get(2).setPhaseNumber(8);
		controller.playerList.get(3).setPhaseNumber(10);
		int t = 1;
		boolean stop = false;
		while(t < 200){
			for(int i = 0; i < playerList.size(); i++){
				
				controller.playerList.get(i).getPhaseInfo();
				
				controller.drawCard(i);
				if(controller.phaseOut(i)){
					System.out.println("The phase number is " + controller.playerList.get(i).getPhaseNumber());
					System.out.println("The player is " + controller.playerList.get(i).getName());
					System.out.println("The number of sets "+controller.playerList.get(i).numSets);
					System.out.println("The size of the sets "+controller.playerList.get(i).setSize);
					System.out.println("The size of the run "+ controller.playerList.get(i).runSize);
					System.out.println("This player can phase out ###############################################" + controller.playerList.get(i).getName());
					
					System.out.println(controller.playerList.get(i).phasedOutStuffToString());
					
				}
				//System.out.println(controller.playerList.get(i).hand);
				controller.discard(i);
			}
			
		t++;
		}
		for(Integer i : playerList){
			controller.playerList.get(i).hand.size()	;
		}
	}
	@Test 
	public void testhit(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.drawPile.shuffle();
		
		for(int i = 0; i < 4; i++){
			System.out.println(controller.playerList.get(i).getName());
			System.out.println(controller.playerList.get(i).hand.size());
		}
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
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
		controller.playerList.get(0).hand.add(tempCard1);	controller.playerList.get(0).hand.add(tempCard2);
		controller.playerList.get(0).hand.add(tempCard3);	controller.playerList.get(0).hand.add(tempCard4);
		controller.playerList.get(0).hand.add(tempCard5);	controller.playerList.get(0).hand.add(tempCard6);
		controller.playerList.get(0).hand.add(tempCard7);	controller.playerList.get(0).hand.add(tempCard8);
		controller.playerList.get(0).hand.add(tempCardS);	controller.playerList.get(0).hand.add(tempCardW);
		controller.playerList.get(0).setPhaseNumber(8);
		controller.playerList.get(0).getPhaseInfo();
		controller.playerList.get(0).checkPhase();
		controller.playerList.get(0).phaseOut();
		
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
		controller.playerList.get(1).hand.add(tempCard11);	controller.playerList.get(1).hand.add(tempCard12);
		controller.playerList.get(1).hand.add(tempCard13);	controller.playerList.get(1).hand.add(tempCard14);
		controller.playerList.get(1).hand.add(tempCard15);	controller.playerList.get(1).hand.add(tempCard16);
		controller.playerList.get(1).hand.add(tempCard17);	controller.playerList.get(1).hand.add(tempCard18);
		controller.playerList.get(1).hand.add(tempCard1S);	controller.playerList.get(1).hand.add(tempCard1W);
		controller.playerList.get(1).setPhaseNumber(2);
		controller.playerList.get(1).getPhaseInfo();
		controller.playerList.get(1).checkPhase();
		controller.playerList.get(1).phaseOut();
		
		Card tempCard24 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard25 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard26 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard27 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card tempCard22 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard2W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		Card tempCard23 = new Card(9, Card.cardColor.Yellow, Card.type.Normal);	
		Card tempCard21 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard28 = new Card(3, Card.cardColor.Yellow, Card.type.Normal);
		Card tempCard2S = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		controller.playerList.get(2).hand.add(tempCard21);	controller.playerList.get(2).hand.add(tempCard22);
		controller.playerList.get(2).hand.add(tempCard23);	controller.playerList.get(2).hand.add(tempCard24);
		controller.playerList.get(2).hand.add(tempCard25);	controller.playerList.get(2).hand.add(tempCard26);
		controller.playerList.get(2).hand.add(tempCard27);	controller.playerList.get(2).hand.add(tempCard28);
		controller.playerList.get(2).hand.add(tempCard2S);	controller.playerList.get(2).hand.add(tempCard2W);
		controller.playerList.get(2).setPhaseNumber(1);
		controller.playerList.get(2).getPhaseInfo();
		controller.playerList.get(2).checkPhase();
		controller.playerList.get(2).phaseOut();
		
		Card tempCard31 = new Card(1, Card.cardColor.Red, Card.type.Normal); 
		Card tempCard32 = new Card(1, Card.cardColor.Blue, Card.type.Normal);
		Card tempCard33 = new Card(3, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard34 = new Card(2, Card.cardColor.Red, Card.type.Normal);
		Card tempCard35 = new Card(2, Card.cardColor.Green, Card.type.Normal);	
		Card tempCard36 = new Card(7, Card.cardColor.Red, Card.type.Normal);
		Card tempCard37 = new Card(4, Card.cardColor.Red, Card.type.Normal);	
		Card tempCard38 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card tempCard3S = new Card(0, Card.cardColor.Black, Card.type.Skip);	
		Card tempCard3W = new Card(0, Card.cardColor.Black, Card.type.Wild);
		controller.playerList.get(3).hand.add(tempCard31);	controller.playerList.get(3).hand.add(tempCard32);
		controller.playerList.get(3).hand.add(tempCard33);	controller.playerList.get(3).hand.add(tempCard34);
		controller.playerList.get(3).hand.add(tempCard35);	controller.playerList.get(3).hand.add(tempCard36);
		controller.playerList.get(3).hand.add(tempCard37);	controller.playerList.get(3).hand.add(tempCard38);
		controller.playerList.get(3).hand.add(tempCard3S);	controller.playerList.get(3).hand.add(tempCard3W);
		// check a run
		controller.playerList.get(3).setPhaseNumber(4);
		controller.playerList.get(3).getPhaseInfo();
		controller.playerList.get(3).checkPhase();
		controller.playerList.get(3).phaseOut();
		
		System.out.println(controller.playerList.get(0).hand);
		for(Integer i : playerList){
			System.out.println(controller.playerList.get(i).getName());
			System.out.println(controller.playerList.get(i).hand.size());
			controller.hit(i);
			System.out.println(controller.playerList.get(i).hand.size());
		}
		
	}
	@Test 
	public void testdiscard(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller controller = new Controller(model, view);
		controller.setPlayerOrder();
		controller.dealCards();
		controller.drawPile.shuffle();

		ArrayList<Integer> playerList = new ArrayList<Integer>();
		playerList.add(0);
		playerList.add(1);
		playerList.add(2);
		playerList.add(3);
		controller.playerList.get(0).setStrategy(strategyType.oldRed);
		controller.playerList.get(1).setStrategy(strategyType.randomPlayer);
		controller.playerList.get(2).setStrategy(strategyType.oldRecklessPlayer);
		controller.playerList.get(3).setStrategy(strategyType.oldLowestScore);
		assertSame(controller.playerList.get(0).hand.size(), 10);
		controller.drawCard(0);
		assertSame(controller.playerList.get(0).hand.size(), 11);
		controller.discard(0);
		assertSame(controller.playerList.get(0).hand.size(), 10);
	}

	@Test
	public void setStrategy(){
		GameModel model = new GameModel();
		GameObserver view = new Gui(model);
		Controller gameController = new Controller(model, view);
		gameController.setPlayerOrder();
		gameController.setStrategy(0, strategyType.oldLowestScore);
		gameController.setStrategy(1, strategyType.oldRecklessPlayer);
		gameController.setStrategy(2, strategyType.oldRed);
		gameController.setStrategy(3, strategyType.randomPlayer);
		assertSame(gameController.playerList.get(0).getStrategy(), strategyType.oldLowestScore);
		assertSame(gameController.playerList.get(1).getStrategy(), strategyType.oldRecklessPlayer);
		assertSame(gameController.playerList.get(2).getStrategy(), strategyType.oldRed);
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