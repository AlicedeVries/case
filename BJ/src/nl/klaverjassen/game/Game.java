package nl.klaverjassen.game;

import java.util.Collections;
import java.util.List;

import general.Deck;
import general.HalfDeck;
import general.Kleur;

public class Game {
	
	private Deck deck;
	private Kleur troef;
	private List<Player> players;
	private boolean roundFinished;
	
	public long startTime;
	public long endTime;
	
	public Game(List<Player> players){
		deck = new HalfDeck(7);
		deck.shuffle();
		
		for (int i=0; i<8; i++)
			for (Player p: players)
				p.askCard(deck);	
		players.get(0).setTeam("red");
		players.get(1).setTeam("blue");
		players.get(2).setTeam("red");
		players.get(3).setTeam("blue");
		this.players = players;
		startTime = System.currentTimeMillis();
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
	
	public List<Player> getPlayers() {
		return players;
	}

	public boolean isFinished() {
		return false;
	}
	
	public boolean isRoundFinished() {
		return roundFinished;
	}

	public void setRoundFinished(boolean roundFinished) {
		this.roundFinished = roundFinished;
	}

	public void rotatePlayers(Player p){
		int rotation = players.indexOf(p);
		Collections.rotate(players, rotation);
	}


}
