package general;
import java.util.Collections;
import java.util.List;


public abstract class Deck {
	
	protected List<Card> deck;
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card draw(){
		return deck.remove(0);
	}
	

}
