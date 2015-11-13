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
 * Servlet implementation class End
 */
@WebServlet("/End")
public class End extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public End() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Game game = (Game) context.getAttribute("game");
	
		@SuppressWarnings("unchecked")
		List<Player> players = (List<Player>) context.getAttribute("players");
		
		if (game.isItAdraw(players.get(0), players.get(1))){
			players.get(0).setStackBijWinstHand(game.getPotSize()/2);
			players.get(1).setStackBijWinstHand(game.getPotSize()/2);
			request.setAttribute("gain", game.getPotSize()/2);
		}
		else{
			Player winner = game.winnerOfHand(players.get(0), players.get(1));
			winner.setStackBijWinstHand(game.getPotSize());
			request.setAttribute("gain", game.getPotSize());
		}
		
		ComputerPlayer ai = (ComputerPlayer) players.get(1);
		ai.setVisibleHand(true);
				
		context.getRequestDispatcher("/Poker/EndOfGame.jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
