package nl.klaverjassen.game;

import general.Card;
import general.Kleur;

public class AIPlayer extends Player {

	public AIPlayer(String name) {
		super(name);
	}
	
	public boolean isValidMove(Card card, Kleur volgKleur){
		if (hasVolgKleur(volgKleur))
			return card.getKleur() ==volgKleur;
		return true; //TODO: Moet introeven
	}

	public Card pickCard(Kleur volgkleur) {
		int x;
		do {
			x  = (int) (Math.random()*getHand().size());
		} while (!isValidMove(getHand().get(x),volgkleur));
		return getHand().get(x);
	}
}
