package nl.blackjack.game;

import java.util.List;

import general.Card;
import general.Deck;

public class Game {
	
	private Deck deck;
	private Dealer dealer;
	private List<Player> players;
	public long startTime;
	public long endTime = -1;

	public Game(List<Player> players) {
		this.players=players;
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
		startTime = System.currentTimeMillis();
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
	
	public boolean getHasFinished (){
		return endTime != -1;
	}
	
	public boolean isFinished(){
		for (Player p: players){
			if (!p.getStand()){
				System.out.println(p.getName());
				return false;
			}
		}
		if (!dealer.hasVisibleHand())
			playDealer();
		endTime = System.currentTimeMillis();
		return true;
	}
}

