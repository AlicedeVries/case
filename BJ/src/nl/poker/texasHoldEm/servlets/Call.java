package nl.poker.texasHoldEm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.poker.texasHoldEm.game.ComputerPlayer;
import nl.poker.texasHoldEm.game.Game;
import nl.poker.texasHoldEm.game.Player;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Poker/Call")
public class Call extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Call() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Game game = (Game) context.getAttribute("game");
		
		List<Player> players = (List<Player>) context.getAttribute("players");
		Player player1 = players.get(0);
		ComputerPlayer computerPlayer = (ComputerPlayer) players.get(1);
		
		if(game.getTableCards().size() == 0)
			player1.callSmallBlindPreflop(game);
		else
			player1.call(game);
		
		//de flop
		if(game.getTableCards().size() == 0) {
			game.flop();
			//Als de humanplayer de dealer is, mag de computerplayer na de human call meteen een actie doen:
			if(computerPlayer.getDealer() != true) {
				int potsizeVoorActie = game.getPotSize();
				computerPlayer.actie(game, false);
				int potsizeNaActie = game.getPotSize();
				//Als de actie van de computerplayer een bet is gaan we naar Raise.jsp, is het een check gaan we naar Game.jsp
				if(potsizeNaActie == potsizeVoorActie){
					request.setAttribute("msg", "AI Player checks flop");
					context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
					return;
				}
				if(potsizeNaActie != potsizeVoorActie){
					request.setAttribute("msg", "AI Player bets flop");
					context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
					return;
				}	
			}
			//Als de computerplayer de dealer is, mag de humanplayer de eerste actie doen na een human call:
			if(computerPlayer.getDealer() == true) {
				request.setAttribute("msg", "Flop dealt. You are first to act. Check or bet?");
				context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
				return;
			}
			
		}
		//de turn
		else if(game.getTableCards().size() == 3) {
			game.turn();
			//Als de humanplayer de dealer is, mag de computerplayer na de human call meteen een actie doen:
			if(computerPlayer.getDealer() != true) {
				int potsizeVoorActie = game.getPotSize();
				computerPlayer.actie(game, false);
				int potsizeNaActie = game.getPotSize();
				//Als de actie van de computerplayer een bet is gaan we naar Raise.jsp, is het een check gaan we naar Game.jsp
				if(potsizeNaActie == potsizeVoorActie){
					request.setAttribute("msg", "AI Player checks turn");
					context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
					return;
				}
				if(potsizeNaActie != potsizeVoorActie){
					request.setAttribute("msg", "AI Player bets turn");
					context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
					return;
				}
			}
			//Als de computerplayer de dealer is, mag de humanplayer de eerste actie doen na een human call:
			if(computerPlayer.getDealer() == true) {
				request.setAttribute("msg", "Turn dealt. You are first to act. Check or bet?");
				context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
				return;
			}
		}
			
		//de river
		else if(game.getTableCards().size() == 4) {
			System.out.println("CODE uit Call.java");
			game.river();
			//Als de humanplayer de dealer is, mag de computerplayer na de human call meteen een actie doen:
			if(computerPlayer.getDealer() != true) {
				int potsizeVoorActie = game.getPotSize();
				computerPlayer.actie(game, false);
				int potsizeNaActie = game.getPotSize();
				//Als de actie van de computerplayer een bet is gaan we naar Raise.jsp, is het een check gaan we naar Game.jsp
				if(potsizeNaActie == potsizeVoorActie){
					request.setAttribute("msg", "AI Player checks river");
					context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
					return;
				}
				if(potsizeNaActie != potsizeVoorActie){
					request.setAttribute("msg", "AI Player bets river");
					context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
					return;
				}	
			}
			//Als de computerplayer de dealer is, mag de humanplayer de eerste actie doen na een human call:
			if(computerPlayer.getDealer() == true) {
				request.setAttribute("msg", "River dealt. You are first to act. Check or bet?");
				context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
				return;
			}
		}	
		//na de river
		else {
			context.getRequestDispatcher("/Poker/End").forward(request, response);
			return;
		}
		context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
