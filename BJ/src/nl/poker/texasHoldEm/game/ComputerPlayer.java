package nl.poker.texasHoldEm.game;

import general.HandHolder;

public class ComputerPlayer extends Player {
	
	private int smallOrBigBlind = 1;

	public ComputerPlayer(String name) {
		super(name);
		setVisibleHand(false);
	}
	
	public void actie(Game game, boolean facingBetOrRaise){
		//preflop actie	
		HandHolder table = game.getTable();
		if(game.getTableCards().size() == 0) {
			//kijk eerst of de AI speler dealer is of niet. In geval van Dealer:
			if(getDealer()) {
				//kijk nu of de AI speler een bet facet of niet. In geval van niet facing bet:
				if(!facingBetOrRaise){
					int bluffPreflop = (int)(Math.random()*100);
					if((getScorePreflop() > 19) || bluffPreflop > 92)
						_2betPreflop(game);
					else
						callSmallBlindPreflop(game);
				}
				//In geval van facing bet:
				if(facingBetOrRaise){
					if(getScorePreflop() < 20)
						fold(game);
					else
						call(game);
					//eventueel nog implementeren: 4bet
//					else
//						raise(game);
				}
			}
			//Als de AI speler niet de dealer is (en dus de big blind):
			if(!getDealer()) {
				//kijk nu of de AI speler een bet facet of niet. In geval van wel facing bet:
				if(facingBetOrRaise){
					if(getScorePreflop() < 19)
//						System.out.println("komt ie hier?");
						fold(game);
					else //if(getScorePreflop() >= 19 && getScorePreflop() < 28)
						call2betPreflop(game);
					//eventueel nog implementeren: 3bet
//					else
//						_3betPreflop(game);
				}
				//in geval van niet facing bet:
				if(!facingBetOrRaise){
					check(game);
				}
			}	
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
	
	@Override
	public void setSmallOrBigBlind(Game game){
		if(smallOrBigBlind % 2 == 0) {
			//wel deelbaar door 2, dan zijn we smallBlind
			postSmallBlind(game);
		}
		else {
			//niet deelbaar door 2, dan zijn we bigblind
			postBigBlind(game);
		}
		smallOrBigBlind++;
	}
	
	@Override
	public boolean getDealer(){
		if(smallOrBigBlind % 2 != 0)
			return true;
		else
			return false;
	}

}
