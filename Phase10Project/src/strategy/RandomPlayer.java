package strategy;
import model.*;
public class RandomPlayer extends Strategy {
	public Player player;
	public strategyType strat = strategyType.randomPlayer;
	
	
	
	public strategyType getStrat() {
		return strat;
	}

	public void setPlayer(Player p){
		player = p;
	}
	@Override
	public Card discard(){
		for(int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).getType() == Card.type.Skip){
				return player.hand.remove(i);
			}
		}
		int i =  (int)(Math.random()*player.hand.size());
		return player.hand.remove(i);
	}
	@Override
	public Boolean draw(Deck drawPile, Card discard){
		int i = (int)(Math.random()*10);

		if (i >5){
			player.hand.draw(drawPile);
			return false;
			
		}else{
			player.hand.add(discard);
			return true;
		}
	}


}