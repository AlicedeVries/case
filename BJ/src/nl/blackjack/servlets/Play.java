package nl.blackjack.servlets;

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

import nl.blackjack.game.Game;
import nl.blackjack.game.Player;

//import game.Game;
//import game.Player;

/**
 * Servlet implementation class Play
 */
@WebServlet("/Play")
public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Play() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	@SuppressWarnings("unchecked") //TODO
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);

		if (session==null){
			getServletContext().getRequestDispatcher("/Start.jsp").forward(request, response);
		}
		
		List<Player> players= (List<Player>) context.getAttribute("players");		
		
		if ( players == null || players.size()==0){
			players= new ArrayList<Player>();		
		}
		
		Player p = (Player) session.getAttribute("player");

		if (p==null){
				p = new Player((String) session.getAttribute("name"));
				session.setAttribute("player", p);
		}
		
		if (!players.contains(p)){
			players.add(p);						
		}
		
		context.setAttribute("players",players);
		
		Game game = (Game) context.getAttribute("game");
		
		if (game==null){
			game = new Game(players);
			context.setAttribute("game", game);
			if (game.hasBlackjack(p)){
				request.setAttribute("msg", "You have blackjack");
				getServletContext().getRequestDispatcher("/Won.jsp").forward(request, response);
			}
			else
				getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
		}
		
		else
			getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
	}

}