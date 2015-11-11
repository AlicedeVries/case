package nl.blackjack.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class Wait
 */
@WebServlet("/Wait")
public class Wait extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wait() {
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

	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);

		if (session==null){
			context.getRequestDispatcher("/Start").forward(request, response);
		}
		
		else {
			Player p = (Player) session.getAttribute("player");
			if (p==null){
					p = new Player((String) session.getAttribute("name"));
					session.setAttribute("player", p);
			}
			
			if (context.getAttribute("game") == null){
				System.out.println("game is null");
				List<Player> players = null;
	
				if (context.getAttribute("timer")==null){
					System.out.println("timer is null");
					players = new ArrayList<Player>();		
					players.add(p);						
					context.setAttribute("timer", System.currentTimeMillis());
					context.setAttribute("nextPlayers",players);
					context.getRequestDispatcher("/Wait.jsp").forward(request, response);	
				}
				else{
					players = (List<Player>) context.getAttribute("nextPlayers");
					if (!players.contains(p)){
						players.add(p);						
						context.setAttribute("nextPlayers",players);
					}
									
					if (System.currentTimeMillis()- (long) context.getAttribute("timer") >= 60000){
						
						Game game = new Game(players);
						context.setAttribute("game", game);
						context.setAttribute("timer", null);
						context.setAttribute("players",players);
						context.setAttribute("nextPlayers",null);
						context.getRequestDispatcher("/Play").forward(request, response);
					}
					else
						context.getRequestDispatcher("/Wait.jsp").forward(request, response);
				}
			}
			else {
				System.out.println("game exists");
				Game game = (Game) context.getAttribute("game");
				if (game.getHasFinished()){
					System.out.println("game finished");
					p.clearHand();
					if (System.currentTimeMillis()- game.endTime>= 30000){
						System.out.println("game ended long time ago");
						context.setAttribute("game", null);
					}					
					context.getRequestDispatcher("/Wait.jsp").forward(request, response);	
				}
				else if (System.currentTimeMillis()- game.startTime>= 300000){
					System.out.println("game started long time ago");
					context.setAttribute("game", null);
					context.getRequestDispatcher("/Wait.jsp").forward(request, response);	
				}
				else{
					List<Player> players = (List<Player>) context.getAttribute("players");
					if (players.contains(p)){
						System.out.println("player of game");
						context.getRequestDispatcher("/Play").forward(request, response);	
					}
					context.getRequestDispatcher("/Wait.jsp").forward(request, response);						
				}
			}
		}
	}
	
}
