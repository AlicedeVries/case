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

	
	public Game() {
		Player p = new Player("lennart");
		deck = new Deck();
		deck.shuffle();
		p.add(new Card(6, Kleur.RUITEN));
		p.add(new Card(9, Kleur.KLAVEREN));
		table.add(new Card(7, Kleur.KLAVEREN));
		table.add(new Card(8, Kleur.KLAVEREN));
		table.add(new Card(5, Kleur.KLAVEREN));
		p.printHand();
//		flop();
		turn();
		river();
		table.printHand();
		p.getScore(table);
	
		
	}

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

	
	
}