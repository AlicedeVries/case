package nl.klaverjassen.game;

import general.Card;
import general.HandHolder;
import general.Kleur;

public class Player extends HandHolder {
	
	private Card playCard = null;
	private String team;

	public Player(String name) {
		super(name);
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Card getPlayCard() {
		return playCard;
	}

	public void setPlayCard(Card playCard) {
		this.playCard = playCard;
	}	
	
	public boolean hasVolgKleur(Kleur volgKleur) {
		boolean hasVolgKleur = false;
		for ( Card c : getHand() )
			if (c.getKleur()==volgKleur)
				hasVolgKleur = true;
		return hasVolgKleur;
	}
	
	public void setClickableCards(Game game){
		boolean kanKleurVolgen = hasVolgKleur(game.getVolgkleur());
		for ( Card c : getHand() ){
			if (kanKleurVolgen && c.getKleur()==game.getVolgkleur())
				c.setClickable(true);
			else if (kanKleurVolgen && c.getKleur()!=game.getVolgkleur())
				c.setClickable(false);
			else
				c.setClickable(true);
		}
	}
}
