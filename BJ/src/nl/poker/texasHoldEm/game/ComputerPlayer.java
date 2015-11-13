package nl.poker.texasHoldEm.game;

import general.HandHolder;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
		setVisibleHand(false);
	}
	
	public void actie(Game game, boolean facingBetOrRaise){
		//preflop actie	
		HandHolder table = game.getTable();
		if(game.getTableCards().size() == 0) {
			if(!facingBetOrRaise){
				int bluff = (int)(Math.random()*100);
				if((getScore(table) > 108) || bluff > 75)
					bet(game);			
			} else
				check(game);
			if(facingBetOrRaise)
				if(getScore(table) < 108)
					fold(game);
				else if(getScore(table) > 108 || getScore(table) < 11)
					call(game);
				else
					raise(game);
		}
		
		//postflop actie
		if(game.getTableCards().size() > 0) {

			if(!facingBetOrRaise){
				int bluff = (int)(Math.random()*100);
				if((getScore(table) > 200) || bluff > 75)
					bet(game);
			} else
				check(game);
			if(facingBetOrRaise)
				if(getScore(table) < 200)
					fold(game);
				else if(getScore(table) > 200 || getScore(table) < 400)
					call(game);
				else
					raise(game);
		}
	}
}
