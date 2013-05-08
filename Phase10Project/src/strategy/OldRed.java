package strategy;

import model.Card;
import model.Deck;
import model.Player;
import model.Card.cardColor;
import model.Deck.deckType;


public class OldRed extends Strategy {
	
	public Player player;
	public strategyType strat = strategyType.oldRed;
	
	
	public void setPlayer(Player p){
		player = p;
	}
	public strategyType getStrat() {
		return strat;
	}
	@Override
	public Card discard(){
		for(int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(i);
			}
		}
		for(int i = 0; i < player.hand.size(); i++){
			if(player.hand.get(i).getColor() != Card.cardColor.Red){
				return player.hand.remove(i);		
			} 
		}
			int i =  (int)(Math.random()*player.hand.size());
			return player.hand.remove(i);
	}
	@Override
	public Boolean draw(Deck drawPile, Card tempdiscardPile){
		if(tempdiscardPile.getColor() == cardColor.Red){
			player.hand.add(tempdiscardPile);
			return true;
		} else{
			player.hand.draw(drawPile);
			return false;
		}
			
	}
		
}