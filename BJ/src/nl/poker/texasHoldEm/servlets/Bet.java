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
@WebServlet("/Poker/Bet")
public class Bet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Game game = (Game) context.getAttribute("Pokergame");
		
		List<Player> players = (List<Player>) context.getAttribute("Pokerplayers");
		ComputerPlayer computerPlayer = (ComputerPlayer) players.get(1);
		Player player1 = players.get(0);
		player1.bet(game);
		
		int potsizeVoorActie = game.getPotSize();
		computerPlayer.actie(game, true);
		int potsizeNaActie = game.getPotSize();
		
		//check of AI player raist
		if((potsizeVoorActie + 10) == potsizeNaActie) {
			request.setAttribute("msg", "AI Player raised 10");
			context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
			return;
		}
		
		//check of AI player fold
		if(potsizeVoorActie == potsizeNaActie) {
			request.setAttribute("msg", "AI Player folded. You won!");
			
			Player winner = game.winnerOfHand(players.get(0), players.get(1));
			winner.setStackBijWinstHand(game.getPotSize());
			context.setAttribute("winner", winner) ;
			request.setAttribute("gain", game.getPotSize());
			
			context.getRequestDispatcher("/Poker/EndOfGame.jsp").forward(request, response);
			return;
		}
		
		//als AI player callt, dan het volgende:		
		
		if(game.getTableCards().size() == 0) {
			request.setAttribute("msg", "AI Player called. Flop is dealt. Your turn.");
			game.flop();
		}
		else if(game.getTableCards().size() == 3) {
			request.setAttribute("msg", "AI Player called. Turn is dealt. Your turn.");
			game.turn();
		}
		else if(game.getTableCards().size() == 4) {
			request.setAttribute("msg", "AI Player called. River is dealt. Your turn.");
			game.river();
		}
		else {
			context.getRequestDispatcher("/Poker/End").forward(request, response);
			return;
		}
		context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
