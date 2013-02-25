package it.polimi.ingsw2.swim.servlets;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.HelpManagerRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterHelpRequest
 */
public class RegisterHelpRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HelpManagerRemote helpManager = (HelpManagerRemote) (new InitialContext()).lookup("HelpManager/remote");
			
			try {
				helpManager.registerHelpRequest(request.getParameter("addressee"), ((Long) request.getSession().getAttribute(SessionAttribute.USER_ID.toString())).toString(), request.getParameter("ability"), request.getParameter("text"));
			} catch (InvalidDataException e) {
				request.setAttribute("errorInvalid", 1);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			} catch (NoSuchUserException e) {
				request.setAttribute("errorUser", 1);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			}
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
