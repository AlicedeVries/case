package general;

import java.util.ArrayList;
import java.util.List;

public abstract class HandHolder {

	protected String name;
	private List<Card> hand = new ArrayList<Card>();
	
	public HandHolder(String name) {
		this.name = name;	
	}
	
	public boolean askCard(Deck deck){
		hand.add(deck.draw());
		return true;
	}
		
	public boolean add(Card c){
		hand.add(c);
		return true;
	}
	
	public boolean remove(Card c){
		hand.remove(c);
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


