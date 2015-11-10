package nl.blackjack.game;

import general.Card;
import general.Deck;
import general.HandHolder;

public class Player extends HandHolder{

	private boolean stand;
	
	public Player(String name) {
		super(name);	
	}
	
	@Override
	public boolean ask(Deck deck){
		if (stand==true)
			return false;
		getHand().add(deck.draw());
		return true;
	}
	
	public void stand(){
		stand = true;
	}
	
	public int getScore(){
		int sum=0;
		int azen  = 0;
		for (Card c: getHand()){
			sum+=c.getWaarde();
			if (c.getWaarde()==11)
				azen++;
		}
		for (int i = azen; i>0; i--){
			if (sum>21)
				sum-=10;
			else
				return sum;
		}
		return sum;
	}
	
}

