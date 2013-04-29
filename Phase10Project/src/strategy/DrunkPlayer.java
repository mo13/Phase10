package strategy;
import model.*;
public class DrunkPlayer extends Strategy {
	public Player player;
	public strategyType strat = strategyType.drunkPlayer;
	
	
	public DrunkPlayer(){
		
	}
	
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player p){
		player = p;
	}
	public Card discard(){
		int i =  (int)(Math.random()*player.hand.size());
		return player.hand.remove(i);
	}
	public void draw(Deck drawPile, Deck discardPile){
		int i = (int)(Math.random()*2);
		if (i == 1){
			player.hand.draw(drawPile);
			
		}else{
			player.hand.draw(discardPile);
		}
	}

}