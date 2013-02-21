package it.polimi.ingsw2.swim.servlets;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AuthenticationRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	public enum Attribute {
		FAILED("error");

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

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Starting the context
		try {
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("Authentication/remote");
			AuthenticationRemote a = (AuthenticationRemote) ref;

			User user;
			// authentication
			String email = request.getParameter("emailText");
			String pass = request.getParameter("Password");
			try {
				user = a.authenticate(email, pass);
			} catch (NoSuchUserException e) {
				request.setAttribute(Attribute.FAILED.toString(), 1);
				request.getRequestDispatcher(response.encodeURL("/index.jsp"))
						.forward(request, response);
				return;
			}

			// obtain the Id of the user
			Long id = user.getId();
			// login session created, forward to the servlet that creates the
			// home page
			request.getSession().setAttribute(
					SessionAttribute.USER_ID.toString(), id);
			// if the authentication is correct, start the session and redirect
			// the user to the servlet that creates the home page
			String url = response.encodeURL(request.getContextPath()
					+ "/Pages/Home.jsp");
			response.sendRedirect(url);
			return;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
