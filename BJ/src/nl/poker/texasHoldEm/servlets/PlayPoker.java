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
import javax.servlet.http.HttpSession;

import nl.poker.texasHoldEm.game.ComputerPlayer;
import nl.poker.texasHoldEm.game.Game;
import nl.poker.texasHoldEm.game.Player;

/**
 * Servlet implementation class PlayPoker
 */
@WebServlet("/PlayPoker")
public class PlayPoker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayPoker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("restart")!=null){
			List<Player> players = (List<Player>) context.getAttribute("players");
			players.get(0).clearHand();
			players.get(0).setHasFolded(false);
			players.get(0).setWinner(false);
			players.get(1).clearHand();			
			players.get(1).setHasFolded(false);
			players.get(1).setWinner(false);
			context.setAttribute("winner", null);
		}
		

		if (session==null){
			context.getRequestDispatcher("/StartPoker").forward(request, response);
		}
		
		else {
			Player p = (Player) session.getAttribute("player");
			if (p==null){
					p = new Player((String) session.getAttribute("name"));
					session.setAttribute("player", p);
			}
			
			ComputerPlayer computerPlayer;
			
			List<Player> players = (List<Player>) context.getAttribute("players");
			if (players==null){
				computerPlayer = new ComputerPlayer("AI player");
				players = new ArrayList<Player>();		
				players.add(p);
				players.add(computerPlayer);
				context.setAttribute("players", players);
			}
			Game game = new Game(players);
			context.setAttribute("game", game);
			context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
