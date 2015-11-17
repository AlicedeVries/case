package nl.klaverjassen.game;

import java.util.Collections;
import java.util.List;

import general.Deck;
import general.HalfDeck;
import general.Kleur;

public class Game {
	
	private Deck deck;
	private Kleur troef;
	private Kleur volgkleur;
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
			this.troef = Kleur.SCHOPPEN;
		else 
			this.troef = Kleur.KLAVEREN;
	}
	
	public Kleur getVolgkleur() {
		return volgkleur;
	}

	public void setVolgkleur(Kleur volgkleur) {
		this.volgkleur = volgkleur;
	}

	public void setRandomVolgkleur(Kleur volgkleur) {
		if (Math.random()<0.25)
			this.troef = Kleur.HARTEN;
		else if (Math.random()<0.5)
			this.troef = Kleur.RUITEN;
		else if (Math.random()<0.75)
			this.troef = Kleur.SCHOPPEN;
		else 
			this.troef = Kleur.KLAVEREN;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public boolean isFinished() {
		return false;
	}
	
	public boolean getRoundFinished() {
		return roundFinished;
	}

	public void setRoundFinished(boolean roundFinished) {
		this.roundFinished = roundFinished;
	}

	public void rotatePlayers(Player p){
		int rotation = 4-players.indexOf(p);
		System.out.println("location before "+players.indexOf(p));
		Collections.rotate(players, rotation);
		System.out.println("location after" + players.indexOf(p));
	}


}
