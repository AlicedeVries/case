package nl.poker.texasHoldEm.servlets;

import java.io.IOException;
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
 * Servlet implementation class Check
 */
@WebServlet("/Poker/Raise")
public class Raise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Raise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(true);

		Game game = (Game) session.getAttribute("Pokergame");
		
		List<Player> players = (List<Player>) session.getAttribute("Pokerplayers");
		ComputerPlayer computerPlayer = (ComputerPlayer) players.get(1);
		Player player1 = players.get(0);
		
		if(game.getTableCards().size() == 0)
			player1._2betPreflop(game);
		else
			player1.raise(game);
		
		//indien preflop
		if(game.getTableCards().size() == 0) {
			int potsizeVoorPreflopActie = game.getPotSize();
			computerPlayer.actie(game, true);
			int potsizeNaPreflopActie = game.getPotSize();
			
			//check of AI player fold
			if(potsizeVoorPreflopActie == potsizeNaPreflopActie) {
				request.setAttribute("msg", "AI Player folded. You won!");
				
				Player winner = game.winnerOfHand(players.get(0), players.get(1));
				winner.setStackBijWinstHand(game.getPotSize());
				session.setAttribute("winner", winner) ;
				
				context.getRequestDispatcher("/Poker/EndOfGame.jsp").forward(request, response);
				return;
			}
			
			//als AI player niet fold dan heeft ie gecallt:
			game.flop();
			
			//nu laten we de AI player meteen een actie doen op de flop, mits hij niet de dealer is
			if(computerPlayer.getDealer() == true) {
				System.out.println("de AI speler callt jouw preflop 3bet");
				request.setAttribute("msg", "AI Player called your preflop reraise. The flop is dealt. Your turn. Check or bet?");
				context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
				return;
			}
			
			int potsizeVoorFlopActie = game.getPotSize();
			computerPlayer.actie(game, false);
			int potsizeNaFlopActie = game.getPotSize();
			
			//indien de AI player niet bet gaan we naar:
			if(potsizeVoorFlopActie == potsizeNaFlopActie) {
				request.setAttribute("msg", "AI Player checks flop");
				context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);
				return;
			}
			if(potsizeVoorFlopActie != potsizeNaFlopActie) {
				request.setAttribute("msg", "AI Player bets flop");
				context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
				return;
			}
		}
		//indien postflop
		else {
			int potsizeVoorActie = game.getPotSize();
			computerPlayer.actie(game, true);
			int potsizeNaActie = game.getPotSize();
			
			//check of AI player raist
			if((potsizeVoorActie + 10) == potsizeNaActie) {
				request.setAttribute("msg", "AI Player reraised 10");
				context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
				return;
			}
			
			//check of AI player fold
			if(potsizeVoorActie == potsizeNaActie) {
				request.setAttribute("msg", "AI Player folded. You won!");
				
				Player winner = game.winnerOfHand(players.get(0), players.get(1));
				winner.setStackBijWinstHand(game.getPotSize());
				session.setAttribute("winner", winner) ;
				
				context.getRequestDispatcher("/Poker/EndOfGame.jsp").forward(request, response);
				return;
			}
			
			//als AI player callt, dan het volgende:	
			if(game.getTableCards().size() == 0) {
				request.setAttribute("msg", "AI Player called your raise. Flop is dealt. Your turn.");
				game.flop();
			}
			else if(game.getTableCards().size() == 3) {
				request.setAttribute("msg", "AI Player called your raise. Turn is dealt. Your turn.");
				game.turn();
			}
			else if(game.getTableCards().size() == 4) {
				request.setAttribute("msg", "AI Player called your raise. River is dealt. Your turn.");
				game.river();
			}
			else {
				context.getRequestDispatcher("/Poker/End").forward(request, response);
				return;
			}
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
