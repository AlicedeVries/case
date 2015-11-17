package nl.klaverjassen.servlets;

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

import nl.klaverjassen.game.AIPlayer;
import nl.klaverjassen.game.Game;
import nl.klaverjassen.game.Player;


/**
 * Servlet implementation class Play
 */
@WebServlet(name = "PlayKlaverjassen", urlPatterns = { "/Klaverjassen/Play" })
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * while it is not your turn: alle kaarten zijn niet clickable + forward naar Game.jsp met msg
		 * if it is: set kaarten clickable en forward naar Game.jsp
		 * if it is en je hebt een kaart gekozen: setPlay card en forward naar PlayAI servlet
		 * 
		 * 
		 */
		
		
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		
		if (session==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Start").forward(request, response);
		}
		
		Game game = (Game) context.getAttribute("KJgame");
		Player p = (Player) session.getAttribute("player");
		
		if (game==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Wait").forward(request, response);
		}
//		else if (game.getRoundFinished()){
//			for (Player player : game.getPlayers()){
//				player.remove(player.getPlayCard());
//			}
//			game.setRoundFinished(roundFinished);
//		}
		else if (request.getParameter("card")==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Game.jsp").forward(request, response);			
		}
		else { 
			System.out.println(request.getParameter("card"));
			int cardIndex = Integer.parseInt(request.getParameter("card"));
			p.setPlayCard(p.getHand().get(cardIndex));
			int indexOfPlayer = game.getPlayers().indexOf(p);
			if (indexOfPlayer==3)
				game.setRoundFinished(true);
			context.getRequestDispatcher("/Klaverjassen/PlayAI").forward(request, response);
			
//			else{
//				while (indexOfPlayer<3 && game.getPlayers().get(indexOfPlayer+1) instanceof AIPlayer){
//					AIPlayer ai = (AIPlayer) game.getPlayers().get(indexOfPlayer+1);
//					ai.makeMove(game);
//					indexOfPlayer++;
//					if (indexOfPlayer==3)
//						game.setRoundFinished(true);
//				}
//			}
//			getServletContext().getRequestDispatcher("/Klaverjassen/EndOfRound.jsp").forward(request, response);
		}
	}
		

}
