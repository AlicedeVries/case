package nl.klaverjassen.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import general.Card;
import general.Deck;
import general.HalfDeck;
import general.Kleur;

public class Game {
	
	private Deck deck;
	private Kleur troef;
	private Player winnerOfRound;
	private List<Card> slag;
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
		players.get(0).setTeam("green");
		players.get(1).setTeam("blue");
		players.get(2).setTeam("green");
		players.get(3).setTeam("blue");
		this.players = players;
		startTime = System.currentTimeMillis();
		setRandomTroef();
		winnerOfRound=null;
		slag = new ArrayList<Card>();				
	}

	public Kleur getTroef() {
		return troef;
	}

	public Card getTroefCard() {
		return new Card(2,troef);
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
		if (slag.isEmpty())
			return null;
		return slag.get(0).getKleur();
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

	public void rotatePlayers(Player p){
		if (p==null)
			return;
		int rotation = 4-players.indexOf(p);
		Collections.rotate(players, rotation);
	}

	public void rotatePlayers(){
		Collections.rotate(players,3);
	}

	public void playAI(){
		while (players.get(0) instanceof AIPlayer && slag.size()<4){
			AIPlayer ai = (AIPlayer) players.get(0);
			Card c = ai.pickCard(getVolgkleur());
			ai.setPlayCard(this, c);
			rotatePlayers();
		}
	}

	public Player setWinnerOfRound() {
		Collections.sort(slag, new KlaverjasComparator(troef, getVolgkleur()));
		for (Player p : players)
			if (p.getPlayCard()==slag.get(0))
				winnerOfRound= p;
		winnerOfRound.getSlagen().add(slag);
		return winnerOfRound;
		
	}

	public Player getWinnerOfRound() {
		return winnerOfRound;
	}

	public List<Card> getSlag() {
		return slag;
	}
	
	public void startNewRound(){
		for (Player p : players){
			p.getHand().remove(p.getPlayCard());
			p.clearPlayCard();
		}
		rotatePlayers(winnerOfRound);
		System.out.println(players.get(0).getName());
		winnerOfRound=null;
		slag=new ArrayList<Card>();
	}

	public int cardValue(Card c){
		switch (c.getGetal()){
			case 11:
				if (c.getKleur()==troef)
					return 20;
				return 2;
			case 9:
				if (c.getKleur()==troef)
					return 14;
				return 0;
			case 1:
				return 11;
			case 10:
				return 10;
			case 13:
				return 4;
			case 12:
				return 3;
			default:
				return 0;
		}
	}
	
	public int getRoundScore() {
		int score=0;
		for (Card c : slag)
			score += cardValue(c);
		return score;		
	}
	
	public int getRoem(){
		return 0;
	}
	
	public void addScoreToWinningTeam(int score){
		for(Player p: players){
			if (winnerOfRound.getTeam()==p.getTeam())
				p.setTeamScore(p.getTeamScore()+score);
		}
	}

	public String getWinningTeam() {
		if (players.get(0).getTeamScore()>=players.get(1).getTeamScore())
			return players.get(0).getTeam();
		return players.get(1).getTeam();
	}


}
