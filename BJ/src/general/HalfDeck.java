package general;

public class HalfDeck extends Deck {
		
	public HalfDeck(int lowCard){
		for (Kleur k: Kleur.values()){
			for (int i=lowCard; i<13;i++)
				deck.add(new Card(i+1,k));
			deck.add(new Card(1,k));
		}
		

	}

}
