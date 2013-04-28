package strategy;

import model.Card;
import model.Deck;
import model.Player;
import model.Card.cardColor;
import model.Deck.deckType;


public class Preventer extends Strategy {
	
	public Player player;
	public strategyType strat = strategyType.preventer;
	
	public Preventer(){
		
	}
	
	public void setPlayer(Player p){
		player = p;
	}
	public strategyType getStrat() {
		return strat;
	}

	public Card discard(){
		for(int i = 0; i < player.hand.size(); i++){
			if(player.hand.get(i).getColor() == Card.cardColor.Red){
				return player.hand.remove(i);		
			} 
		}
			int i =  (int)(Math.random()*player.hand.size());
			return player.hand.remove(i);
		
	}
	public void draw(Deck drawPile, Deck discardPile){
		Card tempdiscardPile = discardPile.draw();
		if(tempdiscardPile.getColor() == cardColor.Red){
			player.hand.add(tempdiscardPile);
		} else{
			player.hand.draw(drawPile);
			discardPile.add(tempdiscardPile);
		}
			
	}
		
}