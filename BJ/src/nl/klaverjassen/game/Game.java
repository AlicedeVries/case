package nl.klaverjassen.game;

import java.util.List;

import general.Deck;
import general.HalfDeck;
import general.Kleur;
import nl.blackjack.game.Player;

public class Game {
	
	private Deck deck;
	private Kleur troef;
	
	public Game(List<Player> players){
		deck = new HalfDeck(7);
		deck.shuffle();
		
		for (int i=0; i<8; i++)
			for (Player p: players)
				p.askCard(deck);	
	}

	public Kleur getTroef() {
		return troef;
	}

	public void setTroef(Kleur troef) {
		this.troef = troef;
	}
	
	public void setRandomTroef() {
		if (Math.random()<0.25)
			this.troef = Kleur.HARTEN;
		else if (Math.random()<0.5)
			this.troef = Kleur.RUITEN;
		else if (Math.random()<0.75)
			this.troef = Kleur.KLAVEREN;
		else 
			this.troef = Kleur.SCHOPPEN;
	}


}
