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

	private int stack = 100;
	private boolean hasFolded = false;
	private boolean winner = false;
	private boolean visibleHand = true;
	private int smallOrBigBlind = 2;
	

	public Player(String name) {
		super(name);	
	}
	
	@Override
	public boolean askCard(Deck deck){
		getHand().add(deck.draw());
		return true;
	}
	
	public int getScore(HandHolder table){
		//combineer eigen hand en tafel
		ArrayList<Card> combine = new ArrayList<Card>();
		combine.addAll(table.getHand());
		combine.addAll(getHand());
		
		//sorteer deze lijst van hoog naar laag
		Collections.sort(combine,new CardComparator());
		

		if(hasRoyalFlush(combine) != -1)
			return 1000;
		
		if(hasStraightFlush(combine) != -1)
			return (900 + hasStraightFlush(combine));
		
		if(hasFourOfaKind(combine) != -1)
			return (800 + hasFourOfaKind(combine));
		
		if(hasFullHouse(combine) != -1)
			return (700 + hasFullHouse(combine));
		
		if(hasFlush(combine) != -1)
			return (600 + hasFlush(combine));
		
		if(hasStraight(combine) != -1)
			return (500 + hasStraight(combine));
		
		if(hasThreeOfaKind(combine) != -1)
			return (400 + hasThreeOfaKind(combine));
		
		if(hasTwoPair(combine) != -1)
			return (300 + hasTwoPair(combine));
		
		if(hasPair(combine) != -1)
			return (200 + hasPair(combine));
		
		else
			return 100 + hasHighCard(combine);
		
	}
	
	
	public int getScorePreflop() {
		
		
		ArrayList<Card> preflopHand = (ArrayList<Card>) getHand();
		int getal1 = preflopHand.get(0).getGetal();
		int getal2 = preflopHand.get(1).getGetal();
		
		//sorteer deze lijst van hoog naar laag
		Collections.sort(preflopHand,new CardComparator());
		
		int preflopHandScore = 0;
		
		//Geef aas de waarde 14
		if(preflopHand.get(0).getGetal() == 1)
			preflopHandScore += 13;
		if(preflopHand.get(1).getGetal() == 1)
			preflopHandScore += 13;
		
		// voeg waarde toe aan paren
		if(getal1 == getal2)
			preflopHandScore += 40;
		
		// voeg waarde toe aan suited
		if(preflopHand.get(0).getKleur().equals(preflopHand.get(1).getKleur()))
			preflopHandScore += 10;
		
		// voeg waarde toe aan connectors
		if((preflopHand.get(0).getGetal() -1) == preflopHand.get(1).getGetal())
			preflopHandScore += 7;	
		if((preflopHand.get(0).getGetal() -2) == preflopHand.get(1).getGetal())
			preflopHandScore += 5;
		if((preflopHand.get(0).getGetal() -3) == preflopHand.get(1).getGetal())
			preflopHandScore += 3;
		if(preflopHand.get(1).getGetal() == 1 && preflopHand.get(0).getGetal() == 13)
			preflopHandScore += 7;
			
		// geef preflopHandScore de waarde van de hoogte van de kaarten
		if(getal1 == getal2)
			preflopHandScore = preflopHandScore + preflopHand.get(0).getGetal();
		else
			preflopHandScore = preflopHandScore + preflopHand.get(0).getGetal() + preflopHand.get(1).getGetal();
		
		return preflopHandScore;
	}
	
	
	
	public void check(Game game){
		
	}
	
	public void bet(Game game){
		stack -= 5;	
		game.addToPot(5);
	}
	
	public void call(Game game){
		stack -= 5;	
		game.addToPot(5);
	}
	
	public void raise(Game game){
		stack -= 10;	
		game.addToPot(10);
	}
	
	public void fold(Game game){
		clearHand();
		hasFolded=true;
	}
	
	public void _2betPreflop(Game game){
		stack -= 8;	
		game.addToPot(8);
	}
	
	public void callSmallBlindPreflop(Game game){
		stack -= 3;	
		game.addToPot(3);
	}
	
	public void call2betPreflop(Game game){
		stack -= 5;
		game.addToPot(5);
	}
	
	public void _3betPreflop(Game game){
		stack -= 10;
		game.addToPot(10);
	}
	
	
	public void postBigBlind(Game game){	
		stack -= 5;	
		game.addToPot(5);
	}
	
	public void postSmallBlind(Game game){	
		stack -= 2;	
		game.addToPot(2);
	}
	
	
	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public boolean getHasFolded() {
		return hasFolded;
	}

	public void setHasFolded(boolean hasFolded) {
		this.hasFolded = hasFolded;
	}

	public int getStack(){
		return stack;
	}
	
	public void setStackBijWinstHand(int potsize){
		stack = stack + potsize;
		winner= true;
	}
	

	public int hasRoyalFlush(ArrayList<Card> combine){
		if(hasStraightFlush(combine) == 1)
			return 1;
		return -1;
	}
	
	public int hasFourOfaKind(ArrayList<Card> combine) {
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
	
	public int hasThreeOfaKind(ArrayList<Card> combine) {
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
	
	public int hasPair(ArrayList<Card> combine) {
		// loop door de lijst en zie of er 2 kaarten van dezelfde waarde inzitten
		for(int k = 0; k < combine.size()-1; k++) {
			if(combine.get(k).getGetal() == combine.get(k+1).getGetal()) {
				return combine.get(k).getGetal();
			}
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public int hasFullHouse(ArrayList<Card> combine) {
		int waardeTOK;
		int waardePair;
		
		waardeTOK = hasThreeOfaKind(combine);
		if(waardeTOK == -1)
			return -1;
		
		//remove three of a kind from copy of combine
		ArrayList<Card> copy = (ArrayList<Card>) combine.clone();
		for(int k = 0; k < copy.size(); k++) {
			if (copy.get(k).getGetal()==waardeTOK){ 
				copy.remove(k);
				copy.remove(k);
				copy.remove(k);
				break;
			}
		}
		
		waardePair = hasPair(copy);
		if(waardePair == -1)
			return -1;
		else
			return waardeTOK;
	}

	@SuppressWarnings("unchecked")
	public int hasTwoPair(ArrayList<Card> combine) {
		int waardePair;
		int waardePair2;
		
		waardePair = hasPair(combine);
		if(waardePair == -1)
			return -1;
		//remove three of a kind from copy of combine
		ArrayList<Card> copy = (ArrayList<Card>) combine.clone();
		for(int k = 0; k < copy.size(); k++) {
			if (copy.get(k).getGetal()==waardePair){ 
				copy.remove(k);
				copy.remove(k);
				break;
			}
		}
		
		waardePair2 = hasPair(copy);
		if(waardePair2 == -1)
			return -1;
		else{
			return waardePair;
		}

	}

	
	
	public int hasFlush(ArrayList<Card> combine){
		
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
	public ArrayList<Card> noDoublesButDoubleAces(ArrayList<Card> combine) {
		ArrayList<Card> doubleAces = (ArrayList<Card>) combine.clone();
		
		
		for (int i=doubleAces.size()-1; i> ( doubleAces.size() - combine.size() ) &&i >= 0;i--){
			if (doubleAces.get(i).getGetal()==1){
				doubleAces.add(0, doubleAces.get(i));
			}
		}

		//verwijder 'dubbele getallen' in combine		
		for(int i = 0; i < (doubleAces.size()-1); i++) {
			if (doubleAces.get(i).getGetal()==doubleAces.get(i+1).getGetal())
				doubleAces.remove(i+1);
		}
				
		return doubleAces;
		
	}
		

	public int hasStraight(ArrayList<Card> cards){
		ArrayList<Card> combine = noDoublesButDoubleAces(cards);

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
		
	
	
	public int hasStraightFlush(ArrayList<Card> combine){
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
	
	public int hasHighCard(ArrayList<Card> combine){
		int x = combine.size() - 1;
		//Indien er een Aas in het spel zit pak dan deze
		if(combine.get(x).getGetal() == 1)
			// + 13 om de Aas een waarde van 14 (de hoogste waarde) te geven
			return (combine.get(x).getGetal() + 13);
		//Als er geen Aas in het spel zit
		else
			return combine.get(0).getGetal();
		}
	
	public String toString(){
		return "" + super.name;
	}

	public boolean isVisibleHand() {
		return visibleHand;
	}

	public void setVisibleHand(boolean visibleHand) {
		this.visibleHand = visibleHand;
	}
	
	public void setSmallOrBigBlind(Game game){
		if(smallOrBigBlind % 2 == 0) {
			//wel deelbaar door 2, dan zijn we smallBlind
			postSmallBlind(game);
		}
		else {
			//niet deelbaar door 2, dan zijn we bigblind
			postBigBlind(game);
		}
		smallOrBigBlind++;
	}
	
	public boolean getDealer(){
		if(smallOrBigBlind % 2 != 0)
			return true;
		else
			return false;
	}

}
	
	