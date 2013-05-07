import static org.junit.Assert.*;

import model.Card;
import model.Deck;

import org.junit.Test;



public class testDeck {
	Deck drawPile = new Deck(Deck.deckType.DrawPile);
	Deck discardPile = new Deck(Deck.deckType.DiscardPile);
	@Test
	public void test() {
		drawPile.createDeck();
		int redCards = 0;
		int blueCards = 0;
		int greenCards = 0;
		int yellowCards = 0;
		int wildCards = 0;
		int skipCards = 0;

		
		for(Card c : drawPile){
			if( c.getType()== Card.type.Normal){
				if (c.getColor() == Card.cardColor.Red){
					redCards++;
				} else if(c.getColor() == Card.cardColor.Blue){
					blueCards++;
				} else if(c.getColor() == Card.cardColor.Green){
					greenCards++;
				} else if(c.getColor() == Card.cardColor.Yellow){
					yellowCards++;
				}
				
			} else if (c.getType() == Card.type.Wild){
				wildCards++;
			} else if (c.getType()== Card.type.Skip){
				skipCards++;
			}
		}
		int sum = skipCards + wildCards + redCards + blueCards + greenCards +yellowCards;
		assertEquals(sum,108);
		assertEquals(redCards,24);
		assertEquals(blueCards ,24);
		assertEquals(greenCards,24);
		assertEquals(yellowCards,24);
		assertEquals(wildCards,8);
		assertEquals(skipCards,4);
		
	}
	
	
	@Test 
	public void testDrawFromDrawPile(){
		drawPile.createDeck();
		drawPile.draw();
		assertEquals(drawPile.size(), 107);
	}
	
	@Test 
	public void testDrawFromDiscard(){
		discardPile.add(new Card(9, Card.cardColor.Red, Card.type.Normal));
		Card checkCard = new Card(1, Card.cardColor.Green, Card.type.Normal);
		discardPile.add(checkCard);
		assertSame(discardPile.draw(),checkCard);
		
	}
	@Test 
	public void testSwap(){
		drawPile.createDeck();
		Card firstCard = drawPile.get(58);
		Card secondCard = drawPile.get(4);	
		drawPile.swap(58, 4);
		assertEquals(firstCard,drawPile.get(4));
		assertEquals(secondCard, drawPile.get(58));
	}
	
	@Test
	public void testShuffle(){
		drawPile.createDeck();
		Card checkCard1 = drawPile.get(1);
		Card checkCard2 = drawPile.get(48);
		Card checkCard3 = drawPile.get(106);
		drawPile.shuffle();
		assertTrue(checkCard1 != drawPile.get(1));
		assertTrue(checkCard2 != drawPile.get(48));
		assertTrue(checkCard3 != drawPile.get(107));
	}
	

}