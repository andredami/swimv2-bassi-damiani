package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.ActivationRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserActivationServlet
 */
public class UserActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public enum Attribute {
		FAILED("error"), SUCCESS("activation");

		private static final String componentName = "UserActivationServlet";
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
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			String token = request.getParameter("tk");
			
			ActivationRemote a = (ActivationRemote) (new InitialContext()).lookup("Activation/remote");
			
			a.activateUser(id.toString(), token);
			request.setAttribute(Attribute.SUCCESS.toString(), 1);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (NullPointerException e) {
			request.setAttribute(Attribute.FAILED.toString(), 1);
		} catch (NoSuchUserException e) {
			request.setAttribute(Attribute.FAILED.toString(), 1);
		} catch (InvalidActivationCode e) {
			request.setAttribute(Attribute.FAILED.toString(), 1);
		} catch (NumberFormatException e) {
			request.setAttribute(Attribute.FAILED.toString(), 1);
		} finally {
			request.getRequestDispatcher(response.encodeURL("/index.jsp")).forward(request, response);
		}
	}

}
