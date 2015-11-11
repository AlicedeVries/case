package nl.poker.texasHoldEm.game;

import general.HandHolder;

public class ComputerPlayer extends Player {

	private boolean facingBetOrRaise;
	private double stack = 100;
	
	public ComputerPlayer(String name) {
		super(name);
	}
	
	public void actie(HandHolder table, boolean facingBetOrRaise){
		if(!facingBetOrRaise)
			if(getScore(table) > 200)
				bet();
		if(facingBetOrRaise)
			if(getScore(table) < 200)
				fold();
			else if(getScore(table) > 200 || getScore(table) < 400)
				call();
			else
				raise();
	}	
}
