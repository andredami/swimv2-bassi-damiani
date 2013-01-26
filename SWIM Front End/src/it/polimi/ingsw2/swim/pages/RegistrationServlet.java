package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public enum Attribute {
		NOT_MATCHING("notMatching"), INVALID_DATA(
				"invalidData"), ALREADY_EXISTS("already"), IN_REGISTRATION("registration"), TIMEOUT("timeout"), REGISTRATION_COMPLETE("completed");

		private static final String componentName = "RegistrationServlet";
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
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		attributesReset(request);
		
		try {
			// Starting the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("Registration/remote");
			RegistrationRemote r = (RegistrationRemote) ref; 
			
			
			String email= request.getParameter("TextEmail");
			request.getSession().setAttribute("TextEmail", email);
			String confirmedEmail = request.getParameter("TextConfirmEmail");
			String password = request.getParameter("TextPassword");
			String confirmedPassword = request.getParameter("TextConfirmPassword");
			// verify password and email 
			if (!(email.equals(confirmedEmail)) || !(password.equals(confirmedPassword))){
				request.getSession().setAttribute(Attribute.NOT_MATCHING.toString(), 1);
				String url = response.encodeURL("/Pages/Registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			// assignment
			String name = request.getParameter("TextName");
			String surname = request.getParameter("TextSurname");
			Calendar birthdate = Calendar.getInstance();
			birthdate.clear();
			birthdate.set(Integer.parseInt(request.getParameter("Year")), Integer.parseInt(request.getParameter("Month")) - 1, Integer.parseInt(request.getParameter("Day")));

			Gender gender = Gender.valueOf(request.getParameter("Gender"));
			try {
				r.createUser(password, email, name, surname, birthdate, gender);
			} catch (InvalidDataException e) {
				// invio frase di errore
				request.getSession().setAttribute(Attribute.INVALID_DATA.toString(), 1);
				String url = response.encodeURL("/Pages/Registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			} catch (UserAlreadyExistsException e) {
				// invio frase di errore
				request.getSession().setAttribute(Attribute.ALREADY_EXISTS.toString(), 1);
				String url = response.encodeURL("/Pages/Registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			request.getSession().setAttribute(Attribute.IN_REGISTRATION.toString(), r);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SelectAbilityRegistrationServlet");
			dispatcher.forward(request, response);
			return;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
	}
	
}
