package nl.blackjack.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.blackjack.game.Game;
import nl.blackjack.game.Player;

/**
 * Servlet implementation class Play
 */
@WebServlet("/Blackjack/Play")
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


	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		
		if (session==null){
			getServletContext().getRequestDispatcher("/Blackjack/Start").forward(request, response);
		}
		
//		getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
//		List<Player> players = (List<Player>) context.getAttribute("nextPlayers");
//		
		Game game = (Game) context.getAttribute("BJgame");
		Player p = (Player) session.getAttribute("BJplayer");
		
		if (game==null){
			getServletContext().getRequestDispatcher("/Blackjack/Wait").forward(request, response);  //////TODO  
		}
		else { 
			if (p.hasBlackjack()){
				request.setAttribute("msg", "You won! You have blackjack!!");
				getServletContext().getRequestDispatcher("/Blackjack/Stand").forward(request, response);
			}
			else
				getServletContext().getRequestDispatcher("/BlackJack/Game.jsp").forward(request, response);
		}
	}

}
