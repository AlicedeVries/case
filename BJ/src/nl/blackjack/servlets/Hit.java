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
 * Servlet implementation class Draw
 */
@WebServlet("/Hit")
public class Hit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hit() {
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
		Player p = (Player) session.getAttribute("player");
		if (p==null)
			System.out.println("geen player");
		Game game = (Game) context.getAttribute("game");
		if (game==null)
			System.out.println("geen game");
		game.drawCard(p);
		
		if (!game.hasValidScore(p)){
			game.dealerHandVisible(true);
			request.setAttribute("msg", "You went bust");
			getServletContext().getRequestDispatcher("/Lost.jsp").forward(request, response);			
		}
		else
			getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
	}

}
