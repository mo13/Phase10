package strategy;

import model.*;


public class OldLowestScore extends Strategy {
	public Player player;
	public strategyType strat = strategyType.oldLowestScore;
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player l){
		player = l;
	}
	@Override
	public Card discard(){
		player.hand.orderHand();
		for(int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(i);
			}
		}
		return player.hand.remove(player.hand.size()-1);
	}
	@Override
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
