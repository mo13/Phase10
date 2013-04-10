package Model;
import java.awt.Color;
import java.util.ArrayList;




public class Deck extends ArrayList<Card> {
	private ArrayList<Card.cardColor> colors = new ArrayList<Card.cardColor>();	
	private deckType type;
	public enum deckType{
		DrawPile, DiscardPile
	}
	
	public Deck(deckType type){
		this.type = type;
	}
	
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
	
	public Card draw(){
		int index;
		if(this.type == deckType.DrawPile){
			index = (int)Math.round(Math.random()*(this.size()-1));
		} else {
			index = this.size()-1;
			
		}
		
		Card tempCard = this.get(index);
		this.remove(index);
		
		return tempCard;
	}
	
	public Card draw(int index){
		return this.remove(index);
	}
	

	
	public void shuffle(){
		int mid = this.size()/2;
		for(int begin = 0; begin<mid; begin++){
//			for(int end = mid; end <this.size(); end++){
//				
//			}
			int num1 = begin;
			int num2 = mid + begin-1;
			this.swap(num1, num2);
		}
	}
	
	public void swap(int index1, int index2){
		Card tempCard1 = this.remove(index1);
		Card tempCard2 = this.remove(index2);
		this.add(index2, tempCard1);
		this.add(index1, tempCard2);		
	}
	
}
