package nl.klaverjassen.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import general.Card;

public class Main {

	public static void main(String[] args) {

		List<Player> players = new ArrayList<Player>();
		Player player = new Player("You \t   ");
		players.add(player);
		Player ai = new AIPlayer("AI player 1");
		players.add(ai);
		ai = new AIPlayer("AI player 2");
		players.add(ai);
		ai = new AIPlayer("AI player 3");
		players.add(ai);

		
		Game game = new Game(players);
		AIPlayer aiPlayer = (AIPlayer) players.get(2);
		game.rotatePlayers(aiPlayer);
		
//		for (Player p : players){
//			System.out.print(p.getName()+ ": \t");
//			Collections.sort(p.getHand(),new KlaverjasComparator(game.getTroef(), game.getVolgkleur()));
//			for (Card c: p.getHand()){
//				System.out.print(c+ "    \t");
//			}
//			System.out.println();
//		}
		
		while (!player.getHand().isEmpty()){
			System.out.println("Troef is "+ game.getTroef());
			System.out.println("Your hand:");
			for (Card c: player.getHand())
				System.out.print(c+ "    \t");
			System.out.println();
			
			game.playAI();
			player.setClickableCards(game);
			Scanner in = new Scanner(System.in);
			int cardIndex;
			do {
				System.out.print("Your turn: ");
				cardIndex = in.nextInt();
			} while (!player.getHand().get(cardIndex).isClickable());
			
			player.setPlayCard(game, player.getHand().get(cardIndex));
			game.rotatePlayers();
			game.playAI();
		
			
			game.setWinnerOfRound();
			
			System.out.println(game.setWinnerOfRound().getName() + " win(t) de slag");
			game.startNewRound();
		}
		


		
	}

}
