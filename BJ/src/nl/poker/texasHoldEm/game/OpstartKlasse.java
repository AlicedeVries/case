//package nl.poker.texasHoldEm.game;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import general.Deck;
//import general.HandHolder;
//
//public class OpstartKlasse {
//
//	public static void main(String[] args) {
//		
//		System.out.println("Welkom bij online poker");
//		System.out.println("Wat is je naam?");
//		
//		Scanner input = new Scanner(System.in);
//		String playername = input.next();
//		
//		//creëer de spelers
//		Player speler1 = new Player(playername);
//		ComputerPlayer AIspeler = new ComputerPlayer("AIspeler");
//		
//		//creëer de lijst van spelers
//		List<Player> players = new ArrayList<Player>();
//		
//		//voeg de spelers toe aan de lijst
//		players.add(speler1);
//		players.add(AIspeler);
//		
//		//maak een game
//		Game game = new Game(players);
//		
//		//print jouw kaarten
//		speler1.printHand();
//		
//		//leg de flop op tafel
//		game.flop();
//		game.printBoard();
//		
//		//vraag wat je wil doen
//		System.out.println("check or bet?");
//		String actieFlop = input.next();
//		
//		if(actieFlop.equals("check"))
//			speler1.check();
//			game.check(speler1);
//		if(actieFlop.equals("bet")) {
//			speler1.bet();
//			game.bet(speler1);
//		}
//		
//		//nu gaat de AI speler een actie doen.
//		if(actieFlop.equals("check"))
//			AIspeler.actie(game.getTable(), false);
//			game.turn();
//		if(actieFlop.equals("bet"))
//			AIspeler.actie(game.getTable(), true);
//		
//		
//		//Hier volgt code indien er bet actie op de flop is
//		// code
//		// code
//		
//		//Nu de code voor als het tijd is voor de Turn
//		
//		//leg de turn op tafel
//		System.out.println("de Turn:");
//		game.printBoard();
//		
//		//vraag wat je wil doen
//		System.out.println("check or bet?");
//		String actieTurn = input.next();
//		
//		if(actieTurn.equals("check"))
//			speler1.check();
//			game.check(speler1);
//		if(actieTurn.equals("bet")) {
//			speler1.bet();
//			game.bet(speler1);
//		}
//		
//		//nu gaat de AI speler een actie doen.
//		if(actieTurn.equals("check"))
//			AIspeler.actie(game.getTable(), false);
//			game.river();
//		if(actieTurn.equals("bet"))
//			AIspeler.actie(game.getTable(), true);
//		
//		
//		//Hier volgt code indien er bet actie op de turn is
//		// code
//		// code
//		
//		//Nu de code voor als het tijd is voor de River
//		//leg de turn op tafel
//		System.out.println("de River:");
//		game.printBoard();
//		
//		//vraag wat je wil doen
//		System.out.println("check or bet?");
//		String actieRiver = input.next();
//		
//		if(actieRiver.equals("check"))
//			speler1.check();
//			game.check(speler1);
//		if(actieRiver.equals("bet")) {
//			speler1.bet();
//			game.bet(speler1);
//		}
//		
//
//		//nu gaat de AI speler een actie doen.
//		if(actieRiver.equals("check"))
//			AIspeler.actie(game.getTable(), false);
//		if(actieRiver.equals("bet"))
//			AIspeler.actie(game.getTable(), true);		
//		
//		
//		//Hier volgt code indien er bet actie op de river is
//		// code
//		// code
//		
//		//nu volgt de Showdown
//		
//		
//		System.out.println("dit is de winnaar van de hand:");
//		System.out.println(game.winnerOfHand(speler1, AIspeler));
//		
//		game.printBoard();
//		speler1.printHand();
//		AIspeler.printHand();
//		
//	}
//
//}
