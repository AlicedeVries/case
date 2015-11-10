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
		ArrayList<Card> combine = new ArrayList<Card>();
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
		System.out.println("Has 4 of a kind:"+ (hasFourOfaKind(combine) != -1));
		System.out.println("Has 3 of a kind:" + (hasThreeOfaKind(combine) != -1));
		System.out.println("Has pair:" + (hasPair(combine) != -1));
		
		return true;
	}
	
	
	public int hasRoyalFlush(List<Card> combine){
		
		System.out.println("hier:"+hasStraightFlush(combine));
		if(hasStraightFlush(combine) == 1)
			return 1;
		return -1;
		

	}
	
	public int hasFourOfaKind(List<Card> combine) {
		int teller = 1;
		int getWaarde = 0;
		// loop door de lijst en zie of er 4 kaarten van dezelfde waarde inzitten
		for(int k = 0; k < combine.size()-1; k++) {
			if(combine.get(k).getGetal() == combine.get(k+1).getGetal()) {
				teller ++;
				getWaarde = combine.get(k).getGetal();
			}
			else
				teller = 1;
			if(teller == 4)
				return getWaarde;
			}
		return -1;
	}
	
	public int hasThreeOfaKind(List<Card> combine) {
		int teller = 1;
		int getWaarde = 0;
		// loop door de lijst en zie of er 3 kaarten van dezelfde waarde inzitten
		for(int k = 0; k < combine.size()-1; k++) {
			if(combine.get(k).getGetal() == combine.get(k+1).getGetal()) {
				teller ++;
				getWaarde = combine.get(k).getGetal();
			}
			else
				teller = 1;
			if(teller == 3)
				return getWaarde;
			}
		return -1;
	}
	
	public int hasPair(List<Card> combine) {
		int teller = 1;
		int getWaarde = 0;
		// loop door de lijst en zie of er 2 kaarten van dezelfde waarde inzitten
		for(int k = 0; k < combine.size()-1; k++) {
			if(combine.get(k).getGetal() == combine.get(k+1).getGetal()) {
				return getWaarde;
			}
		}
		return -1;
	}
	
//	public int hasTwoPair(List<Card> combine) {
//		
//		if(hasThreeOfaKind(combine) == -1)
//		
//		
//		
//		
//	
//		
//		
//		return -1;
//	}
	

	
	
	
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
	
	
	@SuppressWarnings("unchecked")
	public int hasStraight(ArrayList<Card> cards){
		ArrayList<Card> combine = (ArrayList<Card>) cards.clone();
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
		ArrayList<Card> harten = returnAllOneColor(Kleur.HARTEN,combine);
		if(hasStraight(harten) != -1)
			return hasStraight(harten);
		
		ArrayList<Card> klaveren = returnAllOneColor(Kleur.KLAVEREN,combine);
		if(hasStraight(klaveren) != -1)
			return hasStraight(klaveren);

		ArrayList<Card> schoppen = returnAllOneColor(Kleur.SCHOPPEN,combine);
		if(hasStraight(schoppen) != -1)
			return hasStraight(schoppen);
		
		ArrayList<Card> ruiten = returnAllOneColor(Kleur.RUITEN,combine);
		if(hasStraight(ruiten) != -1)
			return hasStraight(ruiten);
		
		return -1;
		
	}
	
	public ArrayList<Card> returnAllOneColor(Kleur k, List<Card> hand){
		ArrayList<Card> kleur = new ArrayList<Card>();
		for (Card c : hand){
			if(c.getKleur() == k)
				kleur.add(c);
		}
		return kleur;		
	}

	
}

