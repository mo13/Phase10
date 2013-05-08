package strategy;

import strategy.Strategy.strategyType;
import model.Card;
import model.Deck;

public class newReckless extends Strategy {
	

	public strategyType strat = strategyType.newRecklessPlayer;
	
	
	@Override
	public Card discard(){
		player.hand.orderHand();
		analyzeHand();
		for(int i = 0; i < tempHand.size(); i++){
			if (tempHand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(player.hand.indexOf(tempHand.get(i)));
			}
		}
		if(tempHand.size()> 0){
			return player.hand.remove(player.hand.indexOf(tempHand.get(0)));
		}else{
			int i =  (int)(Math.random()*player.hand.size());
			return player.hand.remove(i);
		}
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
