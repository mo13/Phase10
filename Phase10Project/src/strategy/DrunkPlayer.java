package strategy;
import model.*;
public class DrunkPlayer extends Strategy {
	public Player player;
	public DrunkPlayer(Player p){
		this.player = p;
	}
	public void discard(){
		int i =  (int)(Math.random()*player.hand.size());
		player.hand.remove(i);
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