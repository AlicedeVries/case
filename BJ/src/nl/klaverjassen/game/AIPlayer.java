package nl.klaverjassen.game;

import general.Card;

public class AIPlayer extends Player {

	public AIPlayer(String name) {
		super(name);
	}
	
	public boolean isValidMove(Card card, Game game){
		if (hasVolgKleur(game.getVolgkleur()))
			return card.getKleur() == game.getVolgkleur();
		return true; //TODO: Moet introeven
	}
	
	public void makeMove(Game game){
		int x;
		do {
			x  = (int) (Math.random()*getHand().size());
		} while (!isValidMove(getHand().get(x),game));
		
		this.setPlayCard(getHand().get(x));
		System.out.println( getHand().get(x) );
	}
}
