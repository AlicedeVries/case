package nl.blackjack.servlets;

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

import nl.blackjack.game.Game;
import nl.blackjack.game.Player;

/**
 * Servlet implementation class Wait
 */
@WebServlet("/Blackjack/Wait")
public class Wait extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int WAIT_TIME_FOR_NEW_GAME = 30*1000;
	private static int MAX_TIME_FOR_GAME = 300*1000;
	private static int MAX_TIME_AFTER_GAME = 20*1000;
	
       
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
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(false);
		Game game = (Game) context.getAttribute("game");

		if (session==null){
			context.getRequestDispatcher("/Start").forward(request, response);
		}
		
		else if (game!= null){
		//if a game already exists
			if (game.getHasFinished()){
			//if game has finished
				addToWaitingPlayerList(getPlayer(session));
				if (System.currentTimeMillis()- game.endTime>= MAX_TIME_AFTER_GAME)
				//if game has finished a while ago
					startNewGame(request, response);
				else 
					waitSomeMore(request, response);					
			}
			else{
			//if game is still going
				if (game.getPlayers().contains(getPlayer(session)))
				//if player is in game
					forwardToGame(request, response);
				else {
					addToWaitingPlayerList(getPlayer(session));
					if (System.currentTimeMillis()-game.startTime >= MAX_TIME_FOR_GAME)
					//if game has lasted to long
						startNewGame(request, response);
					else
						waitSomeMore(request, response);					
				}
			}
		} 
		else{
		//if no game exists yet
			addToWaitingPlayerList(getPlayer(session));
			if (context.getAttribute("timer")==null){ 
			//if there is no wait timer: start wait timer
				context.setAttribute("timer", System.currentTimeMillis());
				waitSomeMore(request, response);					
			}
			else if (System.currentTimeMillis()- (long) context.getAttribute("timer") >= WAIT_TIME_FOR_NEW_GAME)
			//if the wait time is over: start new game	
				startNewGame(request, response);
			else
				waitSomeMore(request, response);
		}
	}
			
	
	private Player getPlayer (HttpSession session){
		Player p = (Player) session.getAttribute("player");
		if (p==null){
				p = new Player((String) session.getAttribute("name"));
				session.setAttribute("player", p);
		}
		return p;
	}
	
	@SuppressWarnings("unchecked")
	private void addToWaitingPlayerList(Player p){
		ServletContext context = getServletContext();
		List<Player> players = (List<Player>) context.getAttribute("waitingPlayers") ;
		if (players == null) {
			players = new ArrayList<Player>();		
			players.add(p);						
			context.setAttribute("waitingPlayers",players);
		}
		else if (!players.contains(p))
			players.add(p);
	}

	@SuppressWarnings("unchecked")
	private void startNewGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		List<Player> players = (List<Player>) context.getAttribute("waitingPlayers");
		Game game = new Game(players);
		context.setAttribute("nextPlayers",null);
		context.setAttribute("game",game);
		context.getRequestDispatcher("/Play").forward(request, response);					
	}
	
	private void waitSomeMore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/BlackJack/Wait.jsp").forward(request, response);							
	}

	private void forwardToGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/Play").forward(request, response);					
	}
}
