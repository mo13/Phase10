import static org.junit.Assert.*;

import model.Card;

import org.junit.Test;


public class testCard {
	

	protected void setUp(){
		Card goodCard = new Card(4, Card.cardColor.Red, Card.type.Normal);
		Card badCard = new Card(13, Card.cardColor.Blue, Card.type.Normal);
	}
	
	@Test
	public void testGoodCard() {
		Card card = new Card(4, Card.cardColor.Red, Card.type.Normal);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadCard() {
		Card card = new Card(13, Card.cardColor.Red , Card.type.Normal);
	}
	
	@Test
	public void testToString() {
		Card card = new Card(12, Card.cardColor.Red, Card.type.Normal);
		String ans = "12 Red Normal";
		System.out.println(card.toString());
		assertEquals(card.toString(), ans);
	}
	
	@Test 
	public void testGetNumber(){
		Card card = new Card(4, Card.cardColor.Red, Card.type.Normal);
		assertEquals(4,card.getNumber());
	}
	
	@Test 
	public void testGetType(){
		Card card = new Card(4, Card.cardColor.Red, Card.type.Wild);
		assertEquals(Card.type.Wild, card.getType());
	}
	@Test 
	public void testGetColor(){
		Card card = new Card(4, Card.cardColor.Red, Card.type.Normal);
		assertEquals(Card.cardColor.Red, card.getColor());
	}


}

