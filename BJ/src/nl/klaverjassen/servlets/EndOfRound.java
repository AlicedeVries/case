package nl.klaverjassen.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.klaverjassen.game.Game;
import nl.klaverjassen.game.Player;


/**
 * Servlet implementation class EndOfRound
 */
@WebServlet("/Klaverjassen/EndOfRound")
public class EndOfRound extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndOfRound() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		
		if (session==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Start").forward(request, response); return;
		}
		
		Game game = (Game) context.getAttribute("KJgame");
		Player p = (Player) session.getAttribute("player");
		
		if (game==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Wait").forward(request, response); return;
		}
		
		System.out.println(game.setWinnerOfRound().getName() + " win(t) de slag");
		game.startNewRound();
		
		getServletContext().getRequestDispatcher("/Klaverjassen/Play").forward(request, response); return;		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
