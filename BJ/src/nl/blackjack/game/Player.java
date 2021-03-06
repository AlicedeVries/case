package nl.blackjack.game;

import general.Card;
import general.HandHolder;

public class Player extends HandHolder{

	private boolean stand = false;
	
	public Player(String name) {
		super(name);	
	}

	@Override
	public void clearHand(){
		super.clearHand();
		stand = false;
	}
	
	public int getWaarde(Card c){
		if (!c.isVisible())
			return 0;
		if (c.getGetal() == 1)
			return 11;
		if (c.getGetal() > 10)
			return 10;
		return c.getGetal();
	}
	
	public int getScore(){
		int sum=0;
		int azen=0;
		for (Card c: getHand()){
			sum+=getWaarde(c);
			if (c.getGetal()==1)
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

	public boolean hasValidScore (){
		return (getScore()<=21);
	}

	public boolean hasBlackjack (){
		return (getScore()==21 && getHand().size()==2);
	}
	
	public void stand() {
		stand = true;		
	}
	
	public boolean getStand(){
		return stand;
	}

	public void setSecondCardVisible(boolean visible) {
		getHand().get(1).setVisible(visible);
	}

	
}


