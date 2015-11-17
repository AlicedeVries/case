package nl.klaverjassen.game;

import java.util.Comparator;

import general.Card;
import general.Kleur;

public class KlaverjasComparator implements Comparator<Card>{
	
	public Kleur troef;
	public Kleur volgKleur;
	
	public KlaverjasComparator(Kleur troef, Kleur volgKleur) {
		this.troef = troef;
		this.volgKleur = volgKleur;
	}

	public int getWaarde(Card c){
		if (c.getKleur()!=troef)
			switch (c.getGetal()){
				case 10:
					return 14;
				case 1:
					return 15;
				default:
					return c.getGetal();
			}
		else 
			switch (c.getGetal()){
				case 10:
					return 14;
				case 1:
					return 15;
				case 9:
					return 16;
				case 11:
					return 17;
				default:
					return c.getGetal();
			}			
	}
	
	public int compare(Card c1, Card c2){
		if (c1.getKleur()==troef && c2.getKleur()==troef)
			return getWaarde(c2) - getWaarde(c1);
		if (c1.getKleur()==troef && c2.getKleur()!=troef)
			return -1;
		if (c1.getKleur()!=troef && c2.getKleur()==troef)
			return 1;		
		if (c1.getKleur()==volgKleur && c2.getKleur()==volgKleur)
			return getWaarde(c2) - getWaarde(c1);
		if (c1.getKleur()==volgKleur && c2.getKleur()!=volgKleur)
			return -1;
		if (c1.getKleur()!=volgKleur && c2.getKleur()==volgKleur)
			return 1;
		return getWaarde(c2) - getWaarde(c1);
	}
}

/*
Troef				Niet-troef
Boer (jas)	20		Aas	11
Negen (nel)	14		Tien	10
Aas	11				Heer	4
Tien	10			Vrouw	3
Heer	4			Boer	2
Vrouw	3			Negen	0
Acht	0			Acht	0
Zeven	0			Zeven	0
*/