package general;

import java.util.ArrayList;

public class FullDeck extends Deck{
	
	public FullDeck() {
		deck = new ArrayList<Card>();
		for (Kleur k: Kleur.values())
			for (int i=0; i<13;i++)
				deck.add(new Card(i+1,k));
	}
	
	public FullDeck(int numberOfCopies) {
		deck = new ArrayList<Card>();
		for (int j=0; j<numberOfCopies;j++)
			for (Kleur k: Kleur.values())
				for (int i=0; i<13;i++)
					deck.add(new Card(i+1,k));
	}



}
