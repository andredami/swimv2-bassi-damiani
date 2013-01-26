package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActivationResend
 */
public class ActivationResend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public enum Attribute {
		RESENT("resent");

		private static final String componentName = "ActivationResend";
		private final String name;

		private Attribute(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return componentName + "/" + name;
		}
	}

	private void attributesReset(HttpServletRequest request) {
		for (Attribute a : Attribute.values()) {
			request.getSession().setAttribute(a.toString(), null);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
		try {
			InitialContext ctx = new InitialContext();
			RegistrationRemote registrationController = (RegistrationRemote) ctx.lookup("Registration/remote");
			registrationController.sendActivationEmail();
			request.getSession().setAttribute(Attribute.RESENT.toString(), 1);
			String url = response.encodeURL("/Pages/RegistrationConfirmation.jsp");
			response.sendRedirect(request.getContextPath() + url);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

}
