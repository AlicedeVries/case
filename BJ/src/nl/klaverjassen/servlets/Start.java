package nl.klaverjassen.servlets;

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
import nl.poker.texasHoldEm.game.Player;


/**
 * Servlet implementation class Start
 */
@WebServlet(name = "StartKlaverjassen", urlPatterns = { "/Klaverjassen/Start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
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
			context.getRequestDispatcher("/Klaverjassen/StartPage.jsp").forward(request, response);	return;
		}
		else{
			session.setMaxInactiveInterval(600);
			session.setAttribute("name",name);
			context.getRequestDispatcher("/Klaverjassen/Wait").forward(request, response);	
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
