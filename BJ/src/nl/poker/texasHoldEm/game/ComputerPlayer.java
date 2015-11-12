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
			else
				check();
		if(facingBetOrRaise)
			if(getScore(table) < 200)
				fold();
			else if(getScore(table) > 200 || getScore(table) < 400)
				call();
			else
				raise();
	}
	
	public void setFacingBetOrRaiseTrue() {
		facingBetOrRaise = true;
	}
	
	public void check(){
		System.out.println("AI player checkt");
	}
	
	public void bet(){
		stack -= 5;	
		System.out.println("AI player bet €5");
	}
	
	public void call(){
		stack -= 5;
		System.out.println("AI player callt €5");
	}
	
	public void raise(){
		stack -= 10;
		System.out.println("AI player raist €10");
	}
	
	public void fold(){
		System.out.println("AI player folds");
	}
	
	
}
