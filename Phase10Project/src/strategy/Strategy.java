package strategy;
import model.*;

import java.util.*;
public abstract class Strategy {
	
	public static Player player;
	public strategyType strat = strategyType.oldRecklessPlayer;
	public ArrayList<Card> tempHand = new ArrayList<Card>();
	
	public strategyType getStrat() {
		return strat;
	}
	
	public  void setPlayer(Player p){
		player = p;
	}
	public enum strategyType{
		randomPlayer, oldRecklessPlayer, oldRed, oldLowestScore,
					  newRecklessPlayer, newRed, newLowestScore
	}
	
	public abstract Card discard();
	public abstract Boolean draw(Deck drawPile, Card discard);
	 
	public void analyzeHand(){
		tempHand.removeAll(tempHand);
		for(int c = 0; c < player.hand.size(); c++){
			tempHand.add(player.hand.get(c));
		}
		player.checkPhase();
		 for(int i = 0; i < tempHand.size(); i++){
			 if(!player.getPossiblePhasedOutSet().isEmpty()){
					 ArrayList<Integer> tempSets = player.getPossiblePhasedOutSet();
					 for(int j = 0; j< tempSets.size(); j++){
						 if(i == -1){
							 i = 0;
						 }
						 
						 if(tempHand.get(i).getNumber() == tempSets.get(j)){
							 tempHand.remove(i);
							 i--;
						 }
					 }
				 }
			 if(!player.getPossiblePhasedOutRun().isEmpty()){
				 ArrayList<Integer> tempRun = player.getPossiblePhasedOutRun();
				 for(int j = 0; j< tempRun.size(); j++){
					 if(i == -1){
						 i = 0;
					 }
					 if(tempHand.get(i).getNumber() == tempRun.get(j)){
						 tempHand.remove(i);
						 i--;
					 }
				 }
			 }
			 
			 if(!player.getPossiblePhaseColor().isEmpty()){
				 ArrayList<Card.cardColor> tempColors = player.getPossiblePhaseColor();
				 for(int j = 0; j< tempColors.size(); j++){
				
					 if(i == -1){
						 i = 0;
					 }
					 if(tempHand.get(i).getColor() == tempColors.get(j)){
						 tempHand.remove(i);
						 i--;
					 }
				 }
			 }
			 if (tempHand.get(i).getType() == Card.type.Wild){
				 if(i == -1){
					 i = 0;
				 }
				 tempHand.remove(i);
				 i--;
			 }
		 }
	}

  
}