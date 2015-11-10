package general;

import java.util.ArrayList;
import java.util.List;

public abstract class HandHolder {

	private String name;
	private List<Card> hand = new ArrayList<Card>();
	
	public HandHolder(String name) {
		this.name = name;	
	}
	
	public boolean ask(Deck deck){
		hand.add(deck.draw());
		return true;
	}
		
	public boolean add(Card c){
		hand.add(c);
		return true;
	}
	
	public String getName() {
		return name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void clearHand(){
		hand.clear();
	}
	
	public void printHand(){
		System.out.println(name+":");
		for (Card c : hand)
			System.out.println(c);
	}
}


