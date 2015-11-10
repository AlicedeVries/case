package nl.poker.texasHoldEm.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import general.Card;
import general.CardComparator;
import general.Deck;
import general.HandHolder;
import general.Kleur;

public class Player extends HandHolder{

	private boolean stand;
	
	public Player(String name) {
		super(name);	
	}
	
	@Override
	public boolean ask(Deck deck){
		if (stand==true)
			return false;
		getHand().add(deck.draw());
		return true;
	}
	
	public void stand(){
		stand = true;
	}
	
	public boolean getScore(HandHolder table){
		//combineer eigen hand en tafel
		List<Card> combine = new ArrayList<Card>();
		combine.addAll(table.getHand());
		combine.addAll(getHand());
		
		//sorteer deze lijst van hoog naar laag
		Collections.sort(combine,new CardComparator());

		//azen als hoogste en laagste kaart in combine
		for (int i=combine.size()-1; i>=combine.size()-7 ;i--){
			if (combine.get(6).getGetal()==1){
				combine.add(0, combine.get(6));
				i--;
			}
		}
		
//		for (Card c : combine)
//			System.out.print(c+ " " );
		
		System.out.println("Has RoyalFlush:"+ (hasRoyalFlush(combine) != -1));
		System.out.println("Has StraightFlush:"+ (hasStraightFlush(combine) != -1));
		System.out.println("Has Flush:"+ (hasFlush(combine) != -1));
		System.out.println("Has Straight:"+ (hasStraight(combine) != -1));
		
		return true;
	}
	
	
	public int hasRoyalFlush(List<Card> combine){
		
		System.out.println("hier:"+hasStraightFlush(combine));
		if(hasStraightFlush(combine) == 1)
			return 1;
		return -1;
		
		
		
		
		
//		List<Card> clubsRoyalFlush = new ArrayList<Card>();
//		List<Card> spadesRoyalFlush = new ArrayList<Card>();
//		List<Card> diamondsRoyalFlush = new ArrayList<Card>();
//		List<Card> heartsRoyalFlush = new ArrayList<Card>();
//		
//		clubsRoyalFlush.add(new Card(1, Kleur.KLAVEREN));
//		clubsRoyalFlush.add(new Card(10, Kleur.KLAVEREN));
//		clubsRoyalFlush.add(new Card(11, Kleur.KLAVEREN));
//		clubsRoyalFlush.add(new Card(12, Kleur.KLAVEREN));
//		clubsRoyalFlush.add(new Card(13, Kleur.KLAVEREN));
//		
//		spadesRoyalFlush.add(new Card(1, Kleur.SCHOPPEN));
//		spadesRoyalFlush.add(new Card(10, Kleur.SCHOPPEN));
//		spadesRoyalFlush.add(new Card(11, Kleur.SCHOPPEN));
//		spadesRoyalFlush.add(new Card(12, Kleur.SCHOPPEN));
//		spadesRoyalFlush.add(new Card(13, Kleur.SCHOPPEN));
//		
//		diamondsRoyalFlush.add(new Card(1, Kleur.RUITEN));
//		diamondsRoyalFlush.add(new Card(10, Kleur.RUITEN));
//		diamondsRoyalFlush.add(new Card(11, Kleur.RUITEN));
//		diamondsRoyalFlush.add(new Card(12, Kleur.RUITEN));
//		diamondsRoyalFlush.add(new Card(13, Kleur.RUITEN));
//		
//		heartsRoyalFlush.add(new Card(1, Kleur.HARTEN));
//		heartsRoyalFlush.add(new Card(10, Kleur.HARTEN));
//		heartsRoyalFlush.add(new Card(11, Kleur.HARTEN));
//		heartsRoyalFlush.add(new Card(12, Kleur.HARTEN));
//		heartsRoyalFlush.add(new Card(13, Kleur.HARTEN));
//		
//		
//		if (combine.containsAll(clubsRoyalFlush)) 
//			return true;
//		if (combine.containsAll(spadesRoyalFlush))
//			return true;
//		if (combine.containsAll(diamondsRoyalFlush))
//			return true;
//		if (combine.containsAll(heartsRoyalFlush))
//			return true;
//		
//		return false;
	}
	
	public int hasFlush(List<Card> combine){
		
		if (returnAllOneColor(Kleur.HARTEN,combine).size() >= 5)
			return returnAllOneColor(Kleur.HARTEN,combine).get(0).getGetal();
		
		if (returnAllOneColor(Kleur.KLAVEREN,combine).size() >= 5)
			return returnAllOneColor(Kleur.KLAVEREN,combine).get(0).getGetal();
		
		if (returnAllOneColor(Kleur.SCHOPPEN,combine).size() >= 5)
			return returnAllOneColor(Kleur.SCHOPPEN,combine).get(0).getGetal();
		
		if (returnAllOneColor(Kleur.RUITEN,combine).size() >= 5)
			return returnAllOneColor(Kleur.RUITEN,combine).get(0).getGetal();
		
		return -1;
	}
	
	
	public int hasStraight(List<Card> combine){
		//verwijder 'dubbele getallen' in combine
		for(int i = 0; i < (combine.size()-1); i++) {
			if (combine.get(i).getGetal()==combine.get(i+1).getGetal())
				combine.remove(i+1);
		}
		
		int maxIndex = combine.size()-1;

		
		//j is de startkaart, en maxIndex - 4 zodat we dan de startkaart + 4 kaarten hebben die niet verder gaan waardoor de laatste kaart binnen de indexOutOfbound blijft
		for(int j = 0; j <= (maxIndex - 4); j++) {
			//default is dat straight waar is, en we gaan het proberen tegenspreken
			boolean straight= true;
			
			//j is de kaart waar we naar gaan kijken. We gaan 4 keer kijken naar of er sprake is van een opeenvolgend paar (want dan is sprake van Straight)
			for(int k = j; k < (j+4) ; k++) {
								
				//we checken eerst of er sprake is van Aas Heer opeenvolgend. Zoja spring naar volgende iteratie van loop
				if (combine.get(k).getGetal()==1 &&combine.get(k+1).getGetal()==13 ) {continue;}
				
				//we halen het getal van kaart j, en we halen het getal van kaart j+1. We checken of deze opeenvolgend zijn.
				else if( combine.get(k).getGetal()-1 != combine.get(k+1).getGetal() ) {
					//als deze berekening niet 0 is, dan zijn de kaarten niet opeenvolgend en is de straight false
					straight=false;
					break;
				}
			}
			if (straight)
				return combine.get(0).getGetal();
		}
		return -1;
	}
		
	
	
	public int hasStraightFlush(List<Card> combine){
		//dit kan korter, maar nog ff zo voor lennarts duidelijkheid
		List<Card> harten = returnAllOneColor(Kleur.HARTEN,combine);
		if(hasStraight(harten) != -1)
			return hasStraight(harten);
		
		List<Card> klaveren = returnAllOneColor(Kleur.KLAVEREN,combine);
		if(hasStraight(klaveren) != -1)
			return hasStraight(klaveren);

		List<Card> schoppen = returnAllOneColor(Kleur.SCHOPPEN,combine);
		if(hasStraight(schoppen) != -1)
			return hasStraight(schoppen);
		
		List<Card> ruiten = returnAllOneColor(Kleur.RUITEN,combine);
		if(hasStraight(ruiten) != -1)
			return hasStraight(ruiten);
		
		return -1;
		
	}
	
	public List<Card> returnAllOneColor(Kleur k, List<Card> hand){
		List<Card> kleur = new ArrayList<Card>();
		for (Card c : hand){
			if(c.getKleur() == k)
				kleur.add(c);
		}
		return kleur;		
	}

	
}


