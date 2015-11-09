package general;

import java.util.Comparator;

public class Card {
	
	private int getal;
	private Kleur kleur;

	
	public Card(int getal, Kleur kleur)
	{
		this.getal = getal;
		this.kleur = kleur;		
	}
	
	@Override
	public boolean equals(Object o){
		Card c = (Card) o;
		if (this.kleur==c.kleur){
			if (this.getal==c.getal){
				return true;
			}
		}
		return false;
	}
	
	public Kleur getKleur(){
		return kleur;
	}
	
	public int getGetal() {
		return getal;
	}
	
	public int getWaarde() {
		if (getal == 1)
			return 11;
		if (getal > 10)
			return 10;
		return getal;
	}

	public String getImage() {
		if (this.kleur==Kleur.SECRET)
			return "/IMAGES/backWithYCP.svg";
		String img = "/IMAGES/";
		switch (this.kleur){
			case HARTEN:
				img = img.concat("h ");
				break;
			case SCHOPPEN:
				img = img.concat("s ");
				break;
			case KLAVEREN:
				img = img.concat("c ");
				break;
			default:
				img = img.concat("d ");
		}
		if(getal==1)
			return img+"(A).svg";
		if(getal==11)
			return img+"(J).svg"; 
		else if(getal==12)
			return img+"(Q).svg";
		else if(getal==13)
			return img+"(K).svg";
		else
			return img+"("+getal+").svg";
	}

	public String toString() {
		char kleur;
		switch (this.kleur){
			case HARTEN:
				kleur = '\u2665';
				break;
			case SCHOPPEN:
				kleur = '\u2660';
				break;
			case KLAVEREN:
				kleur= '\u2663';
				break;
			default:
				kleur = '\u2666';
		}
		
		if (getal==1)
			return  "Aas" + " " + kleur;
		else if(getal==11)
			return "Boer"+ " " + kleur;
		else if(getal==12)
			return "Vrouw"+ " " + kleur;
		else if(getal==13)
			return "Heer"+ " " + kleur;
		else
			return getal + " " + kleur;
	}
}


