package nl.poker.texasHoldEm.game;

import java.util.List;

import general.Card;
import general.Deck;
import general.FullDeck;
import general.HandHolder;
import general.Kleur;

public class Game  {
	
	private Deck deck;
	private Card bovensteKaart;
	private HandHolder table = new Player("table");
	private int potSize = 0;

	public Game(List<Player> players) {
		deck = new FullDeck();
		deck.shuffle();
		
		for (Player p : players){
			p.askCard(deck);			
		}	
		for (Player p : players){
			p.askCard(deck);			
		}
		bovensteKaart = new Card(0, Kleur.HARTEN);
		bovensteKaart.setVisible(false);
	}
	
	public void flop(){ 
		deck.draw();
		table.askCard(deck);
		table.askCard(deck);
		table.askCard(deck);
	}
	
	public void turn(){
		deck.draw();
		table.askCard(deck);
	}
	
	public void river(){
		deck.draw();
		table.askCard(deck);
	}

	public Player winnerOfHand(Player p1, Player p2) {
		if (p1.getHasFolded())
			return p2;
		if (p2.getHasFolded())
			return p1;
		if(p1.getScore(table) > p2.getScore(table))
			return p1;
		if(p1.getScore(table) < p2.getScore(table))
			return p2;
		return null;
	}	
	
	public boolean isItAdraw(Player p1, Player p2) {
		if(p1.getScore(table) == p2.getScore(table))
			return true;
		else
			return false;
	}
	
	public HandHolder getTable() {
		return table;
	}
	
	public List<Card> getTableCards() {
		return table.getHand();
	}
	
	public Card getBovensteKaart() {
		return bovensteKaart;
	}
	
	public int getPotSize() {
		return potSize;
	}
	
	public void addToPot(int chips){
		potSize+=chips;
	}

	public void printBoard(){
		System.out.println();
		table.printHand();
	}
	
}