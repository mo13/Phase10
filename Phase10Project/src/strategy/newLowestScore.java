package strategy;

import model.*;
import java.util.*;

import strategy.Strategy.strategyType;

public class newLowestScore extends Strategy {

	

	public strategyType strat = strategyType.newLowestScore;
	@Override
	public Card discard(){
		analyzeHand();
		
		for(int i = 0; i < tempHand.size(); i++){
			if (tempHand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(player.hand.indexOf(tempHand.get(i)));
			}
		}
		if(tempHand.size()> 0){
			return player.hand.remove(player.hand.indexOf(tempHand.get(tempHand.size()-1)));
		}else{
			int i =  (int)(Math.random()*player.hand.size());
			return player.hand.remove(i);
		}
		
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


