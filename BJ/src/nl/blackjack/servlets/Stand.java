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
 * Servlet implementation class Stand
 */
@WebServlet("/Blackjack/Stand")
public class Stand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stand() {
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
			context.getRequestDispatcher("/Start").forward(request, response);
		}
		else{
			Player p1 = (Player) session.getAttribute("player");
			Game game = (Game) context.getAttribute("game");
			if (p1==null|| game==null)
				context.getRequestDispatcher("/Start").forward(request, response);
			else{		
				p1.stand();
				if(game.isFinished()){
					if (game.hasBlackjack(p1))
						request.setAttribute("msg", "You won! You have blackjack!");					
					else if (!game.hasValidScore(p1))
						request.setAttribute("msg", "You lost! You went bust");											
					else if (game.dealerPlayerDraw(p1))
						request.setAttribute("msg", "You have a draw with Dealer!");
					else if (game.dealerHasBlackjack())
						request.setAttribute("msg", "You lost! Dealer has blackjack");
					else if (!game.dealerHasValidScore())
						request.setAttribute("msg", "You won! Dealer went bust");
					else if (game.dealerBeatsPlayer(p1))
						request.setAttribute("msg", "You lost!");
					else
						request.setAttribute("msg", "You won!");
				}
				getServletContext().getRequestDispatcher("/BlackJack/Stand.jsp").forward(request, response);
			}
		}
	}

}
