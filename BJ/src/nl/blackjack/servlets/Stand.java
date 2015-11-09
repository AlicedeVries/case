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
@WebServlet("/Stand")
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
			getServletContext().getRequestDispatcher("/Start.jsp").forward(request, response);
		}
		Player p1 = (Player) session.getAttribute("player");
		if (p1==null)
			System.out.println("geen player");
		Game game = (Game) context.getAttribute("game");
		if (game==null)
			System.out.println("geen game");
		game.playDealer();
		
		if (game.dealerHasBlackjack()){
			request.setAttribute("msg", "Dealer has blackjack");
			getServletContext().getRequestDispatcher("/Lost.jsp").forward(request, response);			
		}
		else if (!game.dealerHasValidScore()){
			request.setAttribute("msg", "Dealer went bust");
			getServletContext().getRequestDispatcher("/Won.jsp").forward(request, response);			
		}
		else if (game.dealerBeatsPlayer(p1)){
			getServletContext().getRequestDispatcher("/Lost.jsp").forward(request, response);			
		}
		else if (game.dealerPlayerDraw(p1)){
			getServletContext().getRequestDispatcher("/Draw.jsp").forward(request, response);			
		}
		else
			getServletContext().getRequestDispatcher("/Won.jsp").forward(request, response);
	}

}
