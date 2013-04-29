package strategy;

import model.Card;
import model.Deck;
import model.Player;


public class RecklessPlayer extends Strategy {
	public Player player;
	public strategyType strat = strategyType.recklessPlayer;
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player p){
		player = p;
	}
	public Card discard(){
		player.hand.orderHand();
		return player.hand.remove(0);
	}
	public void draw(Deck drawPile, Deck discardPile){
		Card tempDiscardPile = discardPile.draw();
		if (tempDiscardPile.getNumber() >= 10){
			player.hand.add(tempDiscardPile);

		}else{
			player.hand.draw(drawPile);
			discardPile.add(tempDiscardPile);
		}
	}
}