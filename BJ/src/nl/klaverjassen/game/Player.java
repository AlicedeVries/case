package nl.klaverjassen.game;

import java.util.ArrayList;
import java.util.List;

import general.Card;
import general.HandHolder;
import general.Kleur;

public class Player extends HandHolder {
	
	private Card playCard = null;
	private String team;
	private int teamScore = 0;
	private List<List<Card>> slagen = new ArrayList<List<Card>>();
	
	public List<List<Card>> getSlagen() {
		return slagen;
	}

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

	public void setPlayCard(Game game, Card card) {
		game.getSlag().add(card);
		playCard = card;			
		System.out.println(name+ " played "+ playCard);
	}	
	
	public void clearPlayCard(){
		playCard = null;
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

	public int getTeamScore() {
		return teamScore;
	}

	public void setTeamScore(int teamScore) {
		this.teamScore=teamScore;		
	}
}
