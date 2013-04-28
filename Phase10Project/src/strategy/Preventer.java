package strategy;

import strategy.Strategy.strategyType;
import model.Deck;
import model.Player;


public class Preventer extends Strategy {
	
	public Player player;
	public strategyType strat = strategyType.preventer;
	
	public Preventer(){
		
	}
	
	public void setPlayer(Player p){
		player = p;
	}
	public strategyType getStrat() {
		return strat;
	}

	public void discard(){
		
	}
	public void draw(Deck drawPile, Deck discardPile){
		
	}
		
}