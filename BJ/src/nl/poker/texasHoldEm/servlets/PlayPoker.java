package nl.poker.texasHoldEm.servlets;

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

import nl.poker.texasHoldEm.game.ComputerPlayer;
import nl.poker.texasHoldEm.game.Game;
import nl.poker.texasHoldEm.game.Player;

/**
 * Servlet implementation class PlayPoker
 */
@WebServlet("/Poker/PlayPoker")
public class PlayPoker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayPoker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("restart")!=null || request.getAttribute("restart")!=null){
			List<Player> players = (List<Player>) context.getAttribute("Pokerplayers");
			Player p = players.get(0);
			p.clearHand();
			p.setHasFolded(false);
			p.setWinner(false);
			ComputerPlayer ai = (ComputerPlayer) players.get(1);
			ai.clearHand();			
			ai.setHasFolded(false);
			ai.setWinner(false);
			ai.setVisibleHand(false);
			context.setAttribute("winner", null);
		}
		

		if (session==null){
			context.getRequestDispatcher("/Poker/StartPoker").forward(request, response); return;
		}
		
		else {
			Player p = (Player) session.getAttribute("Pokerplayer");
			if (p==null){
					p = new Player((String) request.getAttribute("name"));
					session.setAttribute("Pokerplayer", p);
			}
			
			ComputerPlayer computerPlayer;  
			
			List<Player> players = (List<Player>) context.getAttribute("Pokerplayers");
			if (players==null){
				computerPlayer = new ComputerPlayer("AI player");
				players = new ArrayList<Player>();		
				players.add(p);
				players.add(computerPlayer);
				context.setAttribute("Pokerplayers", players);
			}
			else 
				computerPlayer = (ComputerPlayer) players.get(1);
			
			Game game = new Game(players);
			p.setSmallOrBigBlind(game);
			computerPlayer.setSmallOrBigBlind(game);
			context.setAttribute("Pokergame", game);
			if(computerPlayer.getDealer() == true) {
				//de AI speler is de dealer, en mag dus preflop als eerst. 
				int potsizeVoorActie = game.getPotSize();
				computerPlayer.actie(game, false);
				int potsizeNaActie = game.getPotSize();
				
				//als de AI speler preflop slechts callt, dan gaan we naar Game.jsp
				if(potsizeVoorActie + 3 == potsizeNaActie) {
					System.out.println("de AI speler callt");
					request.setAttribute("msg", "AI Player called the Big Blind. Your turn.");
					context.getRequestDispatcher("/Poker/Game.jsp").forward(request, response); return;
				}
				//als de AI speler preflop raist (2bet), dan gaan we naar raise.jsp
				if(potsizeVoorActie + 8 == potsizeNaActie) {
					request.setAttribute("msg", "AI Player raised preflop. Your turn");
					context.getRequestDispatcher("/Poker/Raise.jsp").forward(request, response); return;
				}
				//computerPlayer.actie(game, true);
				//context.getRequestDispatcher("/Poker/Check").forward(request, response);
				}
			else
				request.setAttribute("msg", "Preflop. We are first to act. Fold, call or raise?");
				context.getRequestDispatcher("/Poker/PreflopActie.jsp").forward(request, response);
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
