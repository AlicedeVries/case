package nl.poker.texasHoldEm.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import general.Card;
import general.CardComparator;
import general.Deck;
import general.HandHolder;
import general.Kleur;

public class Game  {
	
	private Deck deck;
	private HandHolder table = new Player("table");

	public Game(List<Player> players) {
		deck = new Deck();
		deck.shuffle();
		
		for (Player p : players){
			p.ask(deck);			
		}	
		for (Player p : players){
			p.ask(deck);			
		}
	}
	
	public void flop(){ 
		deck.draw();
		table.ask(deck);
		table.ask(deck);
		table.ask(deck);
	}
	
	public void turn(){
		deck.draw();
		table.ask(deck);
	}
	
	public void river(){
		deck.draw();
		table.ask(deck);
	}

	public Player winnerOfHand(Player p1, Player p2) {
		if(p1.getScore(table) > p2.getScore(table))
			return p1;
		if(p1.getScore(table) < p2.getScore(table))
			return p2;
		else
			return null;
	}	
	
	public boolean isItAdraw(Player p1, Player p2) {
		if(p1.getScore(table) == p2.getScore(table))
			return true;
		else
			return false;
	}
}