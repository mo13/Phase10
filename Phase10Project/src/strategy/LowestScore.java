package strategy;

import model.*;


public class LowestScore extends Strategy {
	public Player player;
	public strategyType strat = strategyType.lowestScore;
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player l){
		player = l;
	}
	public Card discard(){
		player.hand.orderHand();
		return player.hand.remove(player.hand.size()-1);
	}
	public Boolean draw(Deck drawPile, Card tempDiscardPile){

	if(tempDiscardPile.getNumber() < 10){
		player.hand.add(tempDiscardPile);
		return true;
	}else{
		player.hand.draw(drawPile);
		return false;
		}
	}
}
