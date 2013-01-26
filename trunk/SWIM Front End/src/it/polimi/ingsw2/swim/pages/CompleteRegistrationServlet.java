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

	private void attributesReset(HttpServletRequest request) {
		for (Attribute a : Attribute.values()) {
			request.getSession().setAttribute(a.toString(), null);
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
		if(request.getSession().getAttribute(RegistrationServlet.Attribute.IN_REGISTRATION.toString())==null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
		
		if(request.getParameter("ChosenAbility") == null || request.getParameter("ChosenAbility").isEmpty()){
			request.getSession().setAttribute(Attribute.NO_ABILITY.toString(), 1);
			String url = response.encodeURL("/Pages/AbilitySelection.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
			RegistrationRemote registrationController = (RegistrationRemote) request.getSession().getAttribute(RegistrationServlet.Attribute.IN_REGISTRATION.toString());
			String[] entry = { request.getParameter("ChosenAbility") };
			try {
				registrationController.registerUser(entry);
				request.getSession().setAttribute(RegistrationServlet.Attribute.IN_REGISTRATION.toString(), null);
				request.getSession().setAttribute(RegistrationServlet.Attribute.REGISTRATION_COMPLETE.toString(), 1);
				registrationController.sendActivationEmail();
				String url = response.encodeURL("/Pages/RegistrationConfirmation.jsp");
				response.sendRedirect(request.getContextPath() + url);
			} catch (InvalidDataException e) {
				request.getSession().setAttribute(Attribute.NO_ABILITY.toString(), 1);
				String url = response.encodeURL("/Pages/AbilitySelection.jsp");
				response.sendRedirect(request.getContextPath() + url);
			} catch (UserAlreadyExistsException e) {
				request.getSession().setAttribute(RegistrationServlet.Attribute.ALREADY_EXISTS.toString(), 1);
				request.getSession().setAttribute(RegistrationServlet.Attribute.IN_REGISTRATION.toString(), null);
				registrationController.abort();
				String url = response.encodeURL("/Pages/Registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
			} catch (IllegalStateException e){
				request.getSession().setAttribute(RegistrationServlet.Attribute.TIMEOUT.toString(), 1);
				request.getSession().setAttribute(RegistrationServlet.Attribute.IN_REGISTRATION.toString(), null);
				registrationController.abort();
				String url = response.encodeURL("/Pages/Registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}		
	}

}
