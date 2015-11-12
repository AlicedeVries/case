package general;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
	
	private List<Card> deck = new ArrayList<Card>();
	
	public Deck() {
		createFullDeck();				
	}

	public Deck(int numberOfDecks){
		for (int i=0; i<numberOfDecks; i++)
			createFullDeck();
	}
	
	private void createFullDeck() {
		for (int i=0; i<13;i++){
			deck.add(new Card(i+1,Kleur.HARTEN)); 
		}
		for (int i=0; i<13;i++){
			deck.add(new Card(i+1,Kleur.SCHOPPEN)); 
		}
		for (int i=0; i<13;i++){
			deck.add(new Card(i+1,Kleur.RUITEN)); 
		}		
		for (int i=0; i<13;i++){
			deck.add(new Card(i+1,Kleur.KLAVEREN)); 
		}
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
