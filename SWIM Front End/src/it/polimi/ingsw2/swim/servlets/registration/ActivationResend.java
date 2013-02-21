package it.polimi.ingsw2.swim.servlets.registration;

import it.polimi.ingsw2.swim.servlets.SessionAttribute;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RegistrationRemote registrationAgent;
			if(request.getSession().getAttribute(SessionAttribute.REGISTRATION_AGENT.toString()) == null || request.getSession().getAttribute(SessionAttribute.REGISTRATION_COMPLETED.toString()) == null){
				request.getSession().setAttribute(SessionAttribute.REGISTRATION_AGENT.toString(), null);
				request.getSession().setAttribute(SessionAttribute.REGISTRATION_COMPLETED.toString(), null);
				response.sendRedirect(response.encodeURL(request.getContextPath() + "/Pages/Registration.jsp"));
				return;
			} else {
				registrationAgent = (RegistrationRemote) request.getSession().getAttribute(SessionAttribute.REGISTRATION_AGENT.toString());
			}

			registrationAgent.sendActivationEmail();

			request.setAttribute(Attribute.RESENT.toString(), 1);
			
			request.getRequestDispatcher(response.encodeURL("/Pages/RegistrationConfirmation.jsp")).forward(request,response);
	}

}
