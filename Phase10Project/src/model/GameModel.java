package model;

import java.util.ArrayList;

import view.GameObserver;

public class GameModel {
	
	ArrayList gameObservers = new ArrayList();
	Deck drawPile = new Deck(Deck.deckType.DrawPile);
	Deck discardPile = new Deck(Deck.deckType.DiscardPile);
	/*
	 *Requirements for the game. 
	 *Deck: uses the Card class
	 *	 draw pile, discard pile
	 *Player: uses the Hand and Phase class
	 *	 chief, cortona, johnson, arbiter
	 *Each player also has a strategy. 
	 *
	 */
	
	public void registerObserver(GameObserver o) {
		gameObservers.add(o);
	}

	public void removeObserver(GameObserver o) {
		int i = gameObservers.indexOf(o);
		if(i >= 0){
			gameObservers.remove(i);
		}		
	}

	public void setPlayerOrder() {
		// TODO Auto-generated method stub
		
	}
	
	

	
}