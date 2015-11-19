package nl.poker.texasHoldEm.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.poker.texasHoldEm.game.Player;


/**
 * Servlet implementation class StartPoker
 */
@WebServlet("/Poker/StartPoker")
public class StartPoker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartPoker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(true);
		
		String name = (String) request.getParameter("name");
		if (name==null || name==""){
			context.getRequestDispatcher("/Poker/StartPage.jsp").forward(request, response);	
		}
		Player p = (Player) session.getAttribute("Pokerplayer");
		if (p!=null){
			request.setAttribute("restart", "Hallo");
		}
		session.setMaxInactiveInterval(600);
		request.setAttribute("name", name);
		context.getRequestDispatcher("/Poker/PlayPoker").forward(request, response);	
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}