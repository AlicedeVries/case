package general;

import java.util.ArrayList;

public class HalfDeck extends Deck {
		

	public HalfDeck(int lowCard){
		deck = new ArrayList<Card>();
		for (Kleur k: Kleur.values()){
			for (int i=lowCard; i<=13;i++)
				deck.add(new Card(i,k));
			deck.add(new Card(1,k));
		}
	}
	
}
