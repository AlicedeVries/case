package nl.poker.texasHoldEm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Player player1 = players.get(0);
		player1.call(game);
				
		if(game.getTableCards().size() == 0)
			game.flop();
		else if(game.getTableCards().size() == 3)
			game.turn();
		else if(game.getTableCards().size() == 4)
			game.river();
		else {
			context.getRequestDispatcher("/End").forward(request, response);
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
