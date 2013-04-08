import java.awt.Color;
import java.util.ArrayList;




public class Deck extends ArrayList<Card> {
	private ArrayList<Card.cardColor> colors = new ArrayList<Card.cardColor>();	
	
	
//	public enum cardColor{
//		Red,Blue,Green,Yellow,Black
//	}
	
	public void createDeck(){
		colors.add(Card.cardColor.Red);
		colors.add(Card.cardColor.Blue);
		colors.add(Card.cardColor.Green);
		colors.add(Card.cardColor.Yellow);
		for(int i = 0; i < colors.size(); i++){
			for(int num = 0; num <= 11; num ++){
				Card numCards = new Card((num % 12)+1, colors.get(i),  Card.type.Normal);
				Card numCardstwo = new Card((num % 12)+1, colors.get(i),  Card.type.Normal);
				this.add(numCards);
				this.add(numCardstwo);
			}
		}
		for(int i = 0; i < 8; i++){
			Card wildCards = new Card(0,Card.cardColor.Black, Card.type.Wild);
			this.add(wildCards);
		}
		for(int i = 0; i < 4; i++){
			Card skipCards = new Card(0, Card.cardColor.Black, Card.type.Skip);
			this.add(skipCards);
		}
	}
	
	public String toString(){
		 StringBuilder result = new StringBuilder();
		 String NEW_LINE = System.getProperty("line.separator");

		for(int i = 0; i < this.size(); i++){
			result.append(this.get(i).toString());
			result.append(NEW_LINE);
		}
		return result.toString();
	}
}
