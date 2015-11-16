package nl.blackjack.game;

import java.util.List;

import general.Deck;
import general.FullDeck;

public class Game {
	
	private Deck deck;
	private Player dealer;
	private List<Player> players;
	public long startTime;
	public long endTime;

	public Game(List<Player> players) {
		this.players=players;
		dealer = new Player("Dealer");

		deck = new FullDeck(6);
		deck.shuffle();
		
		for (Player p : players){
			p.clearHand();			
			p.askCard(deck);			
		}
		dealer.askCard(deck);		
		for (Player p : players){
			p.askCard(deck);			
		}
		dealer.askCard(deck);
		dealer.setSecondCardVisible(false);
		
		startTime = System.currentTimeMillis();
	}
	
	public Player getDealer(){
		return dealer;
	}

	public void playDealer(){
		if (!dealer.getStand()){
			dealer.stand();
			dealer.setSecondCardVisible(true);
			while (dealer.getScore()<17)
				dealer.askCard(deck);			
			endTime = System.currentTimeMillis();
		}
	}

	public void drawCard(Player p){
		p.askCard(deck);
	}

	public boolean dealerBeatsPlayer(Player p) {
		return ( p.getScore()<dealer.getScore() );
	}

	public boolean dealerDrawsPlayer(Player p) {
		return ( p.getScore()==dealer.getScore() );
	}
	
	//checkt als finished player is finished
	public boolean isFinished(){
		for (Player p: players){
			if (!p.getStand())
				return false;
		}
		return true;
	}

	public List<Player> getPlayers() {
		return players;
	}
}

