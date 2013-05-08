 package strategy;

import model.Card;
import model.Deck;
import model.Player;


public class OldReckless extends Strategy {
	public Player player;
	public strategyType strat = strategyType.oldRecklessPlayer;
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player p){
		Strategy.player = p;
		player = p;
	}
	@Override
	public Card discard(){
		for(int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(i);
			}
		}
		player.hand.orderHand();
		return player.hand.remove(0);
	}
	@Override
	public Boolean draw(Deck drawPile, Card discard){
		//Card tempDiscardPile = discardPile.draw();
		
		if (discard.getNumber() >= 8){
			player.hand.add(discard);
			return true;

		}else{
			player.hand.draw(drawPile);
			return false;
		}
	}
}