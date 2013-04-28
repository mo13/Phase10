package strategy;

import model.*;


public class LowestScore extends Strategy {
	public Player player;
	public strategyType strat = strategyType.lowestScore;
	
	
	public LowestScore(){
		
	}
	
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player p){
		player = p;
	}
	public void discard(){
		player.hand.orderHand();
		player.hand.remove(0);
	}
	public void draw(Deck drawPile, Deck discardPile){
		int i = Card.getnumber();
		if (i < 10){
			player.hand.draw(discardPile);
		}else{
			player.hand.draw(drawPile);
		}
	}

}
}