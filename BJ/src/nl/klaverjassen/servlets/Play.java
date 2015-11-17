package nl.klaverjassen.servlets;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import general.Card;
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
		
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		
		if (session==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Start").forward(request, response); return;
		}
		
		Game game = (Game) context.getAttribute("KJgame");
		Player player = (Player) session.getAttribute("player");
		
		if (game==null){
			getServletContext().getRequestDispatcher("/Klaverjassen/Wait").forward(request, response); return;
		}
	
		else if (request.getParameter("card")==null){
			System.out.println("Troef is "+ game.getTroef());
			System.out.println("Your hand:");
			for (Card c: player.getHand())
				System.out.print(c+ "    \t");
			System.out.println();
			
			game.playAI();
			player.setClickableCards(game);
			
			getServletContext().getRequestDispatcher("/Klaverjassen/Game.jsp").forward(request, response);	return;	
		}
		
		else {
//		while (!player.getHand().isEmpty()){			
//			Scanner in = new Scanner(System.in);
//			int cardIndex;
//			do {
//				System.out.print("Your turn: ");
//				cardIndex = in.nextInt();
//			} while (!player.getHand().get(cardIndex).isClickable());
			
			int cardIndex = Integer.parseInt(request.getParameter("card"));
			player.setPlayCard(game, player.getHand().get(cardIndex));
			game.rotatePlayers();
			game.playAI();
			
			game.setWinnerOfRound();
			System.out.println( "You won " + (game.getWinnerOfRound()==player));

			getServletContext().getRequestDispatcher("/Klaverjassen/EndOfRound.jsp").forward(request, response);	return;	

//		}
		}
		
		
	}
		

}
