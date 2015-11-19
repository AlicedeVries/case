package nl.poker.texasHoldEm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.poker.texasHoldEm.game.ComputerPlayer;
import nl.poker.texasHoldEm.game.Game;
import nl.poker.texasHoldEm.game.Player;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Poker/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Game game = (Game) context.getAttribute("Pokergame");
		
		List<Player> players = (List<Player>) context.getAttribute("Pokerplayers");
		ComputerPlayer computerPlayer = (ComputerPlayer) players.get(1);
		
		//de actie na check van human player als de computerplayer de dealer is
		if(computerPlayer.getDealer() == true) {
		
			//indien het preflop is, en de human player checkt, dan heeft de computerplayer de smallblind gecallt en kunnen we naar de flop
			if(game.getTableCards().size() == 0) {
				game.flop();
				request.setAttribute("msg", "Flop dealt. Your turn. Check or bet?");
				context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);	
				return;
			}
			
			int potsizeVoorActie = game.getPotSize();
			
			computerPlayer.actie(game, false);
			
			int potsizeNaActie = game.getPotSize();
			
		
			if(potsizeVoorActie != potsizeNaActie) {
				request.setAttribute("msg", "AI Player bet 5");
				context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response);
				return;
			}
			
			if(game.getTableCards().size() == 0) {
				request.setAttribute("msg", "AI Player checks preflop. Flop is dealt. Your turn.");
				game.flop();
			}
			else if(game.getTableCards().size() == 3) {
				request.setAttribute("msg", "AI Player checks flop. Turn is dealt. Your turn.");
				game.turn();
			}
				
			else if(game.getTableCards().size() == 4) {
				request.setAttribute("msg", "AI Player checks turn. River is dealt. Your turn.");
				game.river();
			}
			else {
				context.getRequestDispatcher("/Poker/End").forward(request, response);
				return;
			}
			context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response);		
		}
		
		//de actie na check van human player als human player de dealer is
		if(computerPlayer.getDealer() != true) {
			if(game.getTableCards().size() == 0)
				game.flop();
			else if(game.getTableCards().size() == 3)
				game.turn();
			else if(game.getTableCards().size() == 4)
				game.river();
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
