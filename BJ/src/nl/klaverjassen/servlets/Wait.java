package nl.klaverjassen.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Servlet implementation class Wait
 */
@WebServlet(name = "WaitKlaverjassen", urlPatterns = { "/Klaverjassen/Wait" })
public class Wait extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int WAIT_TIME_FOR_NEW_GAME = 10*1000;
	private static int MAX_TIME_FOR_GAME = 60*1000;
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
		Game game = (Game)context.getAttribute("KJgame");

		if (session==null){
			context.getRequestDispatcher("/Klaverjassen/Start").forward(request, response);
		}
		
		else{
		//if no game exists yet
			System.out.println("no game");
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
		Player p = (Player) session.getAttribute("KJplayer");
		if (p==null){
				p = new Player((String) session.getAttribute("name"));
				session.setAttribute("KJplayer", p);
		}
		p.setTeamScore(0);
		p.getSlagen().clear();
		return p;
	}
	
	@SuppressWarnings("unchecked")
	private void addToWaitingPlayerList(Player p){
		ServletContext context = getServletContext();
		List<Player> players = (List<Player>) context.getAttribute("KJwaitingPlayers") ;
		if (players == null) {
			players = new ArrayList<Player>();		
			players.add(p);						
			context.setAttribute("KJwaitingPlayers",players);
		}
		else if (!players.contains(p) && players.size()<4)
			players.add(p);
	}

	@SuppressWarnings("unchecked")
	private void startNewGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		List<Player> players = (List<Player>) context.getAttribute("KJwaitingPlayers");
		int aiNumber = 1;
		while (players.size()<4){
			Player ai = new AIPlayer("AI player"+ aiNumber++);
			players.add(ai);
		}
		Game game = new Game(players);
		context.setAttribute("KJwaitingPlayers",null);
		context.setAttribute("KJgame",game);
		context.getRequestDispatcher("/Klaverjassen/Play").forward(request, response);					
	}
	
	private void waitSomeMore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/Klaverjassen/Wait.jsp").forward(request, response);							
	}

	private void forwardToGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/Klaverjassen/Play").forward(request, response);	 //TODO
	}
}
