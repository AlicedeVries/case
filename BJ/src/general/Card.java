package general;

public class Card {
	
	private int getal;
	private Kleur kleur;
	private boolean visible;
	private boolean clickable;

	
	public Card(int getal, Kleur kleur)
	{
		this.getal = getal;
		this.kleur = kleur;		
		this.visible = true;
		this.clickable=true;
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
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public String getImage() {
		if (!visible)
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
			return img+"(J1).svg"; 
		else if(getal==12)
			return img+"(Q1).svg";
		else if(getal==13)
			return img+"(K1).svg";
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


