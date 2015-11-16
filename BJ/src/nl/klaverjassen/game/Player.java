package nl.klaverjassen.game;

import general.Card;
import general.HandHolder;

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
	

}
