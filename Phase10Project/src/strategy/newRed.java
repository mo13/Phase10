package strategy;

import model.Card;
import model.Deck;
import model.Player;
import model.Card.cardColor;
import java.util.*;

public class newRed extends Strategy{
	
	
	public Player player;
	public strategyType strat = strategyType.newRed;
	
	
	public void setPlayer(Player p){
		Strategy.player = p; 
		player = p;
	}
	public strategyType getStrat() {
		return strat;
	}

	public Card discard(){
		analyzeHand();
		for(int i = 0; i < tempHand.size(); i++){
			if (tempHand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(player.hand.indexOf(tempHand.get(i)));
			}
		}
		for(int i = 0; i < tempHand.size(); i++){
			if(tempHand.get(i).getColor() != Card.cardColor.Red){
				return player.hand.remove(player.hand.indexOf(tempHand.get(i)));		
			} 
		}
		if(tempHand.size()> 0){
			int i =  (int)(Math.random()*tempHand.size());
			return player.hand.remove(player.hand.indexOf(tempHand.get(i)));
		}else{
			int i =  (int)(Math.random()*player.hand.size());
			return player.hand.remove(i);
		}
	}
	public Boolean draw(Deck drawPile, Card tempdiscardPile){
		if(tempdiscardPile.getColor() == cardColor.Red){
			player.hand.add(tempdiscardPile);
			return true;
		} else{
			player.hand.draw(drawPile);
			return false;
		}
			
	}
}
