package it.polimi.ingsw2.swim.servlets;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.UserDirectoryManagerRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelpRequestUser
 */
public class HelpRequestUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public enum Attribute {
		WRONG_DATA("error"),
		RESULT("res"), REGISTRATION("reg");

		private static final String componentName = "LoginServlet";
		private final String name;

		private Attribute(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return componentName + "/" + name;
		}
	}
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("chosenAbility") == null){
			response.sendRedirect(response.encodeURL(request.getContextPath() + "/Pages/AbilitySeleciont.jsp?" + AbilitySelectionMode.FILTER));
			return;
		} else {
			try {
				UserDirectoryManagerRemote directory = (UserDirectoryManagerRemote) (new InitialContext()).lookup("UserDirectoryManager/remote");
				int page = 0;
				if(request.getParameter("page") != null){
					try {
						page = Integer.parseInt(request.getParameter("page"));
					} catch (NumberFormatException e) {
					}
				}
				int minFeedback = -5;
				if(request.getParameter("minFeedback") != null){
					try {
						minFeedback = Integer.parseInt(request.getParameter("minFeedback"));
					} catch (NumberFormatException e) {
					}
				}
				String location = null;
				if(request.getParameter("location") != null){
					location = request.getParameter("location");
				}
				
				try {
					if(request.getSession().getAttribute(SessionAttribute.USER_ID.toString()) == null){
						request.setAttribute(Attribute.REGISTRATION.toString(), 1);
						request.getRequestDispatcher("/Pages/Registration.jsp").forward(request, response);
						return;
					}
					List<User> results = directory.findUserByAbility(((Long) request.getSession().getAttribute(SessionAttribute.USER_ID.toString())).toString(), request.getParameter("chosenAbility"), location, minFeedback, page);
					request.setAttribute(Attribute.RESULT.toString(), results);
					request.getRequestDispatcher("/Pages/RequestForHelp.jsp").forward(request, response);
					return;
				} catch (InvalidDataException e) {
					if(e.invalidFields.contains("name")){
						response.sendRedirect(response.encodeURL(request.getContextPath() + "/Pages/AbilitySelection.jsp?" + AbilitySelectionMode.FILTER));
						return;
					}
					request.setAttribute(Attribute.WRONG_DATA.toString(), 1);
					request.getRequestDispatcher("/Pages/RequestForHelp.jsp").forward(request, response);
					return;
				} catch (NoSuchUserException e) {
					response.sendRedirect(response.encodeURL(request.getContextPath() + "/LogoutServlet"));
					return;
				}
			} catch (NamingException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
}
