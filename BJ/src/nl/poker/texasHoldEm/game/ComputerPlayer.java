package nl.poker.texasHoldEm.game;

import general.HandHolder;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
		setVisibleHand(false);
	}
	
	public void actie(Game game, boolean facingBetOrRaise){
		HandHolder table = game.getTable();
		if(!facingBetOrRaise){
			int a = (int)(Math.random()*100);
			int b = (int)((Math.random()*100)+150);
			int bluff = (int)(Math.random()*100);
			System.out.println("player bluff yoloness: " + bluff + " bluff getal: " + a + " handscore moet hoger zijn dan: " + b + " handscore is: " + (getScore(table)));
			if((getScore(table) > b) || bluff > a) {
				bet(game);
				}
		} else
			check(game);
		if(facingBetOrRaise)
			if(getScore(table) < 200){
				System.out.println("ai player folded");
				fold(game);
			}
			else if(getScore(table) > 200 || getScore(table) < 400)
				call(game);
			else {
				raise(game);
			}
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
//		System.out.println("AI player bet �5");
//	}
//	public void call(Game game){
//		super.call(game);	
//		System.out.println("AI player callt �5");
//	}
//	
//	public void raise(Game game){
//		super.raise(game);	
//		System.out.println("AI player raist �10");
//	}
//	
//	public void fold(Game game){
//		System.out.println("AI player folds");
//	}
	
	
}
