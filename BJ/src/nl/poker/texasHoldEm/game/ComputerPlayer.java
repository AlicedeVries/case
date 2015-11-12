package nl.poker.texasHoldEm.game;

import general.HandHolder;

public class ComputerPlayer extends Player {

	private boolean facingBetOrRaise;
	
	public ComputerPlayer(String name) {
		super(name);
	}
	
	public void actie(Game game, boolean facingBetOrRaise){
		HandHolder table = game.getTable();
		if(!facingBetOrRaise)
			if(getScore(table) > 200)
				bet(game);
			else
				check(game);
		if(facingBetOrRaise)
			if(getScore(table) < 200)
				fold(game);
			else if(getScore(table) > 200 || getScore(table) < 400)
				call(game);
			else
				raise(game);
	}
	
//	public void setFacingBetOrRaiseTrue() {
//		facingBetOrRaise = true;
//	}
//	
//	public void check(Game game){
//		System.out.println("AI player checkt");
//	}
//	
//	public void bet(Game game){
//		super.bet(game);	
//		System.out.println("AI player bet €5");
//	}
//	public void call(Game game){
//		super.call(game);	
//		System.out.println("AI player callt €5");
//	}
//	
//	public void raise(Game game){
//		super.raise(game);	
//		System.out.println("AI player raist €10");
//	}
//	
//	public void fold(Game game){
//		System.out.println("AI player folds");
//	}
	
	
}
