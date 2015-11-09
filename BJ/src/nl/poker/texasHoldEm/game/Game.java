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
		p.add(new Card(10, Kleur.KLAVEREN));
		p.add(new Card(1, Kleur.KLAVEREN));
		table.add(new Card(13, Kleur.KLAVEREN));
		table.add(new Card(12, Kleur.KLAVEREN));
		table.add(new Card(11, Kleur.KLAVEREN));
		p.printHand();
//		flop();
		turn();
		river();
		table.printHand();
		System.out.println(hasFlush(p));
		
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

	public boolean hasRoyalFlush(Player player){
		List<Card> clubsRoyalFlush = new ArrayList<Card>();
		List<Card> spadesRoyalFlush = new ArrayList<Card>();
		List<Card> diamondsRoyalFlush = new ArrayList<Card>();
		List<Card> heartsRoyalFlush = new ArrayList<Card>();
		
		clubsRoyalFlush.add(new Card(1, Kleur.KLAVEREN));
		clubsRoyalFlush.add(new Card(10, Kleur.KLAVEREN));
		clubsRoyalFlush.add(new Card(11, Kleur.KLAVEREN));
		clubsRoyalFlush.add(new Card(12, Kleur.KLAVEREN));
		clubsRoyalFlush.add(new Card(13, Kleur.KLAVEREN));
		
		spadesRoyalFlush.add(new Card(1, Kleur.SCHOPPEN));
		spadesRoyalFlush.add(new Card(10, Kleur.SCHOPPEN));
		spadesRoyalFlush.add(new Card(11, Kleur.SCHOPPEN));
		spadesRoyalFlush.add(new Card(12, Kleur.SCHOPPEN));
		spadesRoyalFlush.add(new Card(13, Kleur.SCHOPPEN));
		
		diamondsRoyalFlush.add(new Card(1, Kleur.RUITEN));
		diamondsRoyalFlush.add(new Card(10, Kleur.RUITEN));
		diamondsRoyalFlush.add(new Card(11, Kleur.RUITEN));
		diamondsRoyalFlush.add(new Card(12, Kleur.RUITEN));
		diamondsRoyalFlush.add(new Card(13, Kleur.RUITEN));
		
		heartsRoyalFlush.add(new Card(1, Kleur.HARTEN));
		heartsRoyalFlush.add(new Card(10, Kleur.HARTEN));
		heartsRoyalFlush.add(new Card(11, Kleur.HARTEN));
		heartsRoyalFlush.add(new Card(12, Kleur.HARTEN));
		heartsRoyalFlush.add(new Card(13, Kleur.HARTEN));
		
		
		
		List<Card> combine = table.getHand();
		combine.addAll(player.getHand());
		
		if (combine.containsAll(clubsRoyalFlush)) 
			return true;
		if (combine.containsAll(spadesRoyalFlush))
			return true;
		if (combine.containsAll(diamondsRoyalFlush))
			return true;
		if (combine.containsAll(heartsRoyalFlush))
			return true;
		
		return false;
	}
	
	public boolean hasFlush(Player p){
		List<Card> combine = table.getHand();
		combine.addAll(p.getHand());
		
		if (returnAllOneColor(Kleur.HARTEN,combine).size() >= 5)
			return true;
		
		if (returnAllOneColor(Kleur.KLAVEREN,combine).size() >= 5)
			return true;
		
		if (returnAllOneColor(Kleur.SCHOPPEN,combine).size() >= 5)
			return true;
		
		if (returnAllOneColor(Kleur.RUITEN,combine).size() >= 5)
			return true;
		
		return false;
	}
	
	public List<Card> returnAllOneColor(Kleur k, List<Card> hand){
		List<Card> kleur = new ArrayList<Card>();
		for (Card c : hand){
			if(c.getKleur() == k)
				kleur.add(c);
		}
		Collections.sort(kleur,new CardComparator());
		return kleur;		
	}
	
	
}