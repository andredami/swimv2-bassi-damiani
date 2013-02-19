package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompleteRegistrationServlet
 */
public class CompleteRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public enum Attribute {
		NO_ABILITY("noAbility");

		private static final String componentName = "CompleteRegistrationServlet";
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RegistrationRemote registrationAgent = (RegistrationRemote) request
				.getSession().getAttribute(
						RegistrationServlet.Attribute.REGISTRATION_AGENT
								.toString());
		if (registrationAgent == null) {
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}

		if (request.getParameter("chosenAbility") == null
				|| request.getParameter("chosenAbility").isEmpty()) {
			request.setAttribute(Attribute.NO_ABILITY.toString(), 1);
			request.getRequestDispatcher(response.encodeURL("/Pages/AbilitySelection.jsp"))
					.forward(request, response);
			return;
		}
		String[] entry = { request.getParameter("chosenAbility") };
		try {
			registrationAgent.registerUser(entry);
			request.getSession()
					.setAttribute(
							RegistrationServlet.Attribute.REGISTRATION_COMPLETE
									.toString(),
							1);
			registrationAgent.sendActivationEmail();
			String url = response
					.encodeURL("/Pages/RegistrationConfirmation.jsp");
			response.sendRedirect(request.getContextPath() + url);
		} catch (InvalidDataException e) {
			request.setAttribute(Attribute.NO_ABILITY.toString(),
					1);
			request.getRequestDispatcher(response.encodeURL("/SelectAbilityRegistrationServlet"))
					.forward(request, response);
		} catch (UserAlreadyExistsException e) {
			request.setAttribute(
					RegistrationServlet.Attribute.ALREADY_EXISTS.toString(), 1);
			request.getSession().setAttribute(
					RegistrationServlet.Attribute.REGISTRATION_AGENT.toString(),
					null);
			registrationAgent.abort();
			request.getRequestDispatcher(response.encodeURL("/Pages/Registration.jsp"))
					.forward(request, response);
		} catch (IllegalStateException e) {
			request.setAttribute(
					RegistrationServlet.Attribute.TIMEOUT.toString(), 1);
			request.getSession().setAttribute(
					RegistrationServlet.Attribute.REGISTRATION_AGENT.toString(),
					null);
			registrationAgent.abort();
			request.getRequestDispatcher(response.encodeURL("/Pages/Registration.jsp"))
					.forward(request, response);
		}
	}

}
