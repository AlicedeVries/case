package nl.blackjack.game;

import java.util.ArrayList;
import java.util.List;

import general.Card;
import general.Deck;

public class Game {
	
	private Deck deck;
	private Dealer dealer;

	public Game(List<Player> players) {
		dealer = new Dealer("Deler");
		deck = new Deck();
		deck.shuffle();
		
		for (Player p : players){
			p.ask(deck);			
		}
		dealer.ask(deck);		
		for (Player p : players){
			p.ask(deck);			
		}
		dealer.ask(deck);
	}
	
	public List<Card> getDealerHand(){
		return dealer.getHand();
	}

	public void playDealer(){
		dealer.setVisibleHand(true);
		while (dealer.getScore()<17)
			dealer.ask(deck);
	}

	public void dealerHandVisible(boolean visible){
		dealer.setVisibleHand(visible);
	}

	public void drawCard(Player p){
		p.ask(deck);
	}
		
	public boolean hasValidScore (Player p){
		return (p.getScore()<=21);
	}

	public boolean hasBlackjack (Player p){
		return (p.getScore()==21);
	}

	public boolean dealerHasBlackjack (){
		return (dealer.getScore()==21 && dealer.getHand().size()==2);
	}

	public boolean dealerHasValidScore (){
		return (dealer.getScore()<=21);
	}
	
	public boolean dealerBeatsPlayer(Player p) {
		return ( p.getScore()<dealer.getScore() );
	}

	public boolean dealerPlayerDraw(Player p) {
		return ( p.getScore()==dealer.getScore() );
	}
}
