package general;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
	
	protected List<Card> deck = new ArrayList<Card>();
	
	public Deck() {
		createFullDeck();				
	}

	public Deck(int numberOfDecks){
		for (int i=0; i<numberOfDecks; i++)
			createFullDeck();
	}
	
	private void createFullDeck() {
		for (Kleur k: Kleur.values())
			for (int i=0; i<13;i++)
				deck.add(new Card(i+1,k));
	}
	
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	
	public Card draw()
	{
		return deck.remove(0);
	}
	

}
