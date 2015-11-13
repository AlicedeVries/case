package nl.poker.texasHoldEm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import general.Card;
import nl.poker.texasHoldEm.game.ComputerPlayer;
import nl.poker.texasHoldEm.game.Game;
import nl.poker.texasHoldEm.game.Player;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Call")
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
		ComputerPlayer computerPlayer = (ComputerPlayer) players.get(1);
		Player player1 = players.get(0);
		player1.call(game);
				
		if(game.getTableCards().size() == 0)
			game.flop();
		else if(game.getTableCards().size() == 3)
			game.turn();
		else if(game.getTableCards().size() == 4)
			game.river();
		else {
			if (game.isItAdraw(players.get(0), players.get(1))){
				players.get(0).setStackBijWinstHand(game.getPotSize()/2);
				players.get(1).setStackBijWinstHand(game.getPotSize()/2);
				context.getRequestDispatcher("/Poker/EndOfGame.jsp").forward(request, response); return;
			}
			Player winner = game.winnerOfHand(players.get(0), players.get(1));
			winner.setStackBijWinstHand(game.getPotSize());
			context.setAttribute("winner", winner) ;
			
			context.getRequestDispatcher("/Poker/EndOfGame.jsp").forward(request, response);
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
