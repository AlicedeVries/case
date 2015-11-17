package nl.poker.texasHoldEm.test;

import org.junit.Test;

import general.Card;
import general.Deck;
import general.FullDeck;
import general.HandHolder;
import general.Kleur;
import nl.poker.texasHoldEm.game.Player;

public class TestScores {

	private Deck deck;
	private HandHolder table = new Player("table");


//	@Test
//	public void testRoyalFlush() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(1, Kleur.KLAVEREN));
//		p.add(new Card(1, Kleur.RUITEN));
//		table.add(new Card(10, Kleur.KLAVEREN));
//		table.add(new Card(8, Kleur.KLAVEREN));
//		table.add(new Card(11, Kleur.KLAVEREN));
//		table.add(new Card(12, Kleur.KLAVEREN));
//		table.add(new Card(13, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==1000 ;
//	}
//
//	@Test
//	public void testStraightFlush() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(6, Kleur.KLAVEREN));
//		p.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(7, Kleur.KLAVEREN));
//		table.add(new Card(8, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.HARTEN));
//		table.add(new Card(5, Kleur.RUITEN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==909 ;
//	}
//	@Test
//	public void testFlush() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(6, Kleur.RUITEN));
//		p.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(3, Kleur.KLAVEREN));
//		table.add(new Card(8, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(11, Kleur.KLAVEREN));
//		table.add(new Card(4, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==611;
//	}
//	
//	@Test
//	public void testStraight() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(6, Kleur.RUITEN));
//		p.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(7, Kleur.HARTEN));
//		table.add(new Card(8, Kleur.SCHOPPEN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.RUITEN));
//		table.add(new Card(12, Kleur.SCHOPPEN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==509 ;
//	}
//	
//	@Test
//	public void testFourOfaKind() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(7, Kleur.KLAVEREN));
//		p.add(new Card(5, Kleur.RUITEN));
//		table.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.SCHOPPEN));
//		table.add(new Card(5, Kleur.HARTEN));
//		table.add(new Card(13, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==805 ;
//	}
//
//	@Test
//	public void testThreeOfaKind() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(5, Kleur.KLAVEREN));
//		p.add(new Card(5, Kleur.RUITEN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(6, Kleur.KLAVEREN));
//		table.add(new Card(12, Kleur.SCHOPPEN));
//		table.add(new Card(9, Kleur.HARTEN));
//		table.add(new Card(1, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==405 ;
//	}
	
	
	@Test
	public void testPair() {
		Player p = new Player("lennart");
		deck = new FullDeck();
		deck.shuffle();
		p.add(new Card(13, Kleur.RUITEN));
		p.add(new Card(1, Kleur.RUITEN));
		table.add(new Card(11, Kleur.SCHOPPEN));
		table.add(new Card(3, Kleur.KLAVEREN));
		table.add(new Card(4, Kleur.KLAVEREN));
		table.add(new Card(1, Kleur.KLAVEREN));
		table.add(new Card(9, Kleur.SCHOPPEN));
		//p.printHand();
		System.out.println(p.getScore(table));
		
		//table.printHand();
		assert p.getScore(table)==45454545;
	}
	
	
//	@Test
//	public void testFullHouse() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(7, Kleur.KLAVEREN));
//		p.add(new Card(7, Kleur.RUITEN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.SCHOPPEN));
//		table.add(new Card(13, Kleur.HARTEN));
//		table.add(new Card(11, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==707 ;
//	}
//	
//	
//	@Test
//	public void testTwoPair() {
//		Player p = new Player("lennart");
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(2, Kleur.KLAVEREN));
//		p.add(new Card(2, Kleur.RUITEN));
//		table.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.SCHOPPEN));
//		table.add(new Card(13, Kleur.HARTEN));
//		table.add(new Card(11, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==309 ;
//	}
//	
//	@Test
//	public void testHighCard() {
//		Player p = new Player("lennart");
////		p.add(new Card(12, Kleur.RUITEN));
////		p.add(new Card(6, Kleur.RUITEN));
////		System.out.println(p.getScorePreflop());
//		deck = new FullDeck();
//		deck.shuffle();
//		p.add(new Card(2, Kleur.KLAVEREN));
//		p.add(new Card(3, Kleur.RUITEN));
//		table.add(new Card(6, Kleur.KLAVEREN));
//		table.add(new Card(9, Kleur.KLAVEREN));
//		table.add(new Card(5, Kleur.SCHOPPEN));
//		table.add(new Card(1, Kleur.HARTEN));
//		table.add(new Card(11, Kleur.KLAVEREN));
//		p.printHand();
//		table.printHand();
//		assert p.getScore(table)==114 ;
//	}
	
}

