package nl.poker.texasHoldEm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Card;
import general.Deck;
import general.HandHolder;
import general.Kleur;
import nl.poker.texasHoldEm.game.Game;
import nl.poker.texasHoldEm.game.Player;

public class TestScores {

	private Deck deck;
	private HandHolder table = new Player("table");

	
	@Test
	public void testRF() {
		Player p = new Player("lennart");
		deck = new Deck();
		deck.shuffle();
		p.add(new Card(1, Kleur.KLAVEREN));
		p.add(new Card(1, Kleur.RUITEN));
		table.add(new Card(10, Kleur.KLAVEREN));
		table.add(new Card(8, Kleur.KLAVEREN));
		table.add(new Card(11, Kleur.KLAVEREN));
		table.add(new Card(12, Kleur.KLAVEREN));
		table.add(new Card(13, Kleur.KLAVEREN));
		p.printHand();
		table.printHand();
		p.getScore(table);
	}

	@Test
	public void testSF() {
		Player p = new Player("lennart");
		deck = new Deck();
		deck.shuffle();
		p.add(new Card(6, Kleur.KLAVEREN));
		p.add(new Card(9, Kleur.KLAVEREN));
		table.add(new Card(7, Kleur.KLAVEREN));
		table.add(new Card(8, Kleur.KLAVEREN));
		table.add(new Card(5, Kleur.KLAVEREN));
		table.add(new Card(5, Kleur.HARTEN));
		table.add(new Card(5, Kleur.RUITEN));
		p.printHand();
		table.printHand();
		p.getScore(table);
	}
	@Test
	public void testF() {
		Player p = new Player("lennart");
		deck = new Deck();
		deck.shuffle();
		p.add(new Card(6, Kleur.RUITEN));
		p.add(new Card(9, Kleur.KLAVEREN));
		table.add(new Card(3, Kleur.KLAVEREN));
		table.add(new Card(8, Kleur.KLAVEREN));
		table.add(new Card(5, Kleur.KLAVEREN));
		table.add(new Card(11, Kleur.KLAVEREN));
		table.add(new Card(4, Kleur.KLAVEREN));
		p.printHand();
		table.printHand();
		p.getScore(table);
	}
	
	@Test
	public void testS() {
		Player p = new Player("lennart");
		deck = new Deck();
		deck.shuffle();
		p.add(new Card(6, Kleur.RUITEN));
		p.add(new Card(9, Kleur.KLAVEREN));
		table.add(new Card(7, Kleur.HARTEN));
		table.add(new Card(8, Kleur.SCHOPPEN));
		table.add(new Card(5, Kleur.KLAVEREN));
		table.add(new Card(5, Kleur.RUITEN));
		table.add(new Card(12, Kleur.SCHOPPEN));
		p.printHand();
		table.printHand();
		p.getScore(table);
	}

}
