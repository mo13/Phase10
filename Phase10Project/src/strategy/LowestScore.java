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

	public void setPlayer(Player l){
		player = l;
	}
	public void discard(){
		player.hand.orderHand();
		player.hand.remove(0);
	}
	public void draw(Deck drawPile, Deck discardPile){

	Card tempDiscardPile = discardPile.draw();
	if(tempDiscardPile.getNumber() < 10){
		player.hand.draw(discardPile);
	}else{
		player.hand.draw(drawPile);
		discardPile.add(tempDiscardPile);
		}
	}
}
