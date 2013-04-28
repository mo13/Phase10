import static org.junit.Assert.*;

import java.util.ArrayList;

import model.*;

import org.junit.Test;




public class PhaseTest {

	
	
	@Test
	public void goodTestCheckNumSet() {
		ArrayList<Card> goodSet = new ArrayList<Card>();
		Card card1 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(4, Card.cardColor.Yellow, Card.type.Normal);
		goodSet.add(card1);
		goodSet.add(card2);
		goodSet.add(card3);
		//System.out.println(goodSet.get(0).getNumber());
		//System.out.println(goodSet.size());
		
		assertSame(Phase.checkNumSet(goodSet,3),true);
		
	}
	
	@Test
	public void WildTestCheckNumSet() {
		ArrayList<Card> goodSet = new ArrayList<Card>();
		Card card1 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(4, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(5, Card.cardColor.Yellow, Card.type.Wild);
		goodSet.add(card1);
		goodSet.add(card2);
		goodSet.add(card3);
		//System.out.println(goodSet.get(0).getNumber());
		//System.out.println(goodSet.size());
		
		assertSame(Phase.checkNumSet(goodSet,3),true);
		
	}
	
	
	
	@Test
	public void badTestCheckNumSet() {
		ArrayList<Card> badSet = new ArrayList<Card>();
		Card card1 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(5, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(4, Card.cardColor.Yellow, Card.type.Normal);
		badSet.add(card1);
		badSet.add(card2);
		badSet.add(card3);
		assertSame(Phase.checkNumSet(badSet,3),false);
		
	}
	
	@Test
	public void goodTestCheckColorSet() {
		ArrayList<Card> goodSet = new ArrayList<Card>();
		Card card1 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(5, Card.cardColor.Red, Card.type.Normal);
		Card card3 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		goodSet.add(card1);
		goodSet.add(card2);
		goodSet.add(card3);
		assertSame(Phase.checkColorSet(goodSet,3),true);	
	}
	
	@Test
	public void WildTestCheckColorSet() {
		ArrayList<Card> goodSet = new ArrayList<Card>();
		Card card1 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card3 = new Card(5, Card.cardColor.Yellow, Card.type.Wild);
		goodSet.add(card1);
		goodSet.add(card2);
		goodSet.add(card3);
		//System.out.println(goodSet.get(0).getNumber());
		//System.out.println(goodSet.size());
		
		assertSame(Phase.checkNumSet(goodSet,3),true);
		
	}
	
	@Test
	public void badTestCheckColorSet() {
		ArrayList<Card> badSet = new ArrayList<Card>();
		Card card1 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(5, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(4, Card.cardColor.Red, Card.type.Normal);
		badSet.add(card1);
		badSet.add(card2);
		badSet.add(card3);
		assertSame(Phase.checkColorSet(badSet,3),false);
		
	}

	@Test
	public void wildTestCheckRun() {
		ArrayList<Card> goodRun = new ArrayList<Card>();
		Card card1 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(6, Card.cardColor.Red, Card.type.Wild);
		Card card4 = new Card(4, Card.cardColor.Green, Card.type.Normal);
		Card card5 = new Card(5, Card.cardColor.Yellow, Card.type.Normal);
		goodRun.add(card1);
		goodRun.add(card2);
		goodRun.add(card3);
		goodRun.add(card4);
		goodRun.add(card5);
		assertSame(Phase.checkRun(goodRun,goodRun.size()),true);
	}
	
	
	@Test
	public void goodTestCheckRun() {
		ArrayList<Card> goodRun = new ArrayList<Card>();
		Card card1 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(3, Card.cardColor.Red, Card.type.Normal);
		Card card4 = new Card(4, Card.cardColor.Green, Card.type.Normal);
		Card card5 = new Card(5, Card.cardColor.Yellow, Card.type.Normal);
		goodRun.add(card1);
		goodRun.add(card2);
		goodRun.add(card3);
		goodRun.add(card4);
		goodRun.add(card5);
		assertSame(Phase.checkRun(goodRun,goodRun.size()),true);
	}
	@Test
	public void badTestCheckRun(){
		ArrayList<Card> badRun = new ArrayList<Card>();
		Card card1 = new Card(1, Card.cardColor.Red, Card.type.Normal);
		Card card2 = new Card(2, Card.cardColor.Blue, Card.type.Normal);
		Card card3 = new Card(6, Card.cardColor.Red, Card.type.Normal);
		Card card4 = new Card(4, Card.cardColor.Green, Card.type.Normal);
		Card card5 = new Card(5, Card.cardColor.Yellow, Card.type.Normal);
		badRun.add(card1);
		badRun.add(card2);
		badRun.add(card3);
		badRun.add(card4);
		badRun.add(card5);
		assertSame(Phase.checkRun(badRun,badRun.size()),false);
	}

}