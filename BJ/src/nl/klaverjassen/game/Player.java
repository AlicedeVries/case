package nl.klaverjassen.game;

import general.HandHolder;

public class Player extends HandHolder {
	
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
	
	
	
	

}
