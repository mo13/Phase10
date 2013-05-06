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
		for(int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(i);
			}
		}
		player.hand.orderHand();
		return player.hand.remove(0);
	}
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