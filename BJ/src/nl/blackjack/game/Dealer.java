package nl.blackjack.game;

import java.util.ArrayList;
import java.util.List;

import general.Card;
import general.Kleur;

public class Dealer extends Player {
	
	private boolean visibleHand=false;

	public boolean hasVisibleHand() {
		return visibleHand;
	}

	public void setVisibleHand(boolean visibleHand) {
		this.visibleHand = visibleHand;
	}

	public Dealer(String name){
		super(name);
	}
	
	@Override
	public List<Card> getHand() {
		if (visibleHand)
			return super.getHand();
		List<Card> hand =new ArrayList<Card>();
		hand.add(super.getHand().get(0));
		hand.add(new Card(0, Kleur.SECRET));
		return hand;
	}


}
