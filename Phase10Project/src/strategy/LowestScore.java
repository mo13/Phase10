package strategy;

import model.*;


public class LowestScore extends Strategy {
	public Player player;
	public strategyType strat = strategyType.lowestScore;
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player p){
		player = p;
	}
	public Card discard(){
		player.hand.orderHand();
		return player.hand.remove(player.hand.size());
	}
	public void draw(Deck drawPile, Deck discardPile){
		Card tempDiscardPile = discardPile.draw();
		if (tempDiscardPile.getNumber() < 10){
			player.hand.draw(discardPile);
		}else{
			player.hand.draw(drawPile);
			discardPile.add(tempDiscardPile);
		}
	}

}
