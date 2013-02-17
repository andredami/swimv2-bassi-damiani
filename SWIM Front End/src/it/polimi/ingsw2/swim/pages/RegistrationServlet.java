package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
		NOT_MATCHING("notMatching"), 
		INVALID_DATA("invalidData"),
		ALREADY_EXISTS("already"),
		REGISTRATION_AGENT("registration"),
		TIMEOUT("timeout"),
		REGISTRATION_COMPLETE("completed");

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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Starting the context
			
			RegistrationRemote r = null;
			if(request.getSession().getAttribute(Attribute.REGISTRATION_AGENT.toString()) == null){
				InitialContext jndiContext = new InitialContext();
				Object ref = jndiContext.lookup("Registration/remote");
				r = (RegistrationRemote) ref;
				request.getSession().setAttribute(Attribute.REGISTRATION_AGENT.toString(), r);
			} else {
				r = (RegistrationRemote) request.getSession().getAttribute(Attribute.REGISTRATION_AGENT.toString());
			}
			
			if(r == null){
				throw new RuntimeException();
			}
			
			String email= request.getParameter("TextEmail");
			String confirmedEmail = request.getParameter("TextConfirmEmail");
			String password = request.getParameter("TextPassword");
			String confirmedPassword = request.getParameter("TextConfirmPassword");
			// verify password and email 
			if (!(email.equals(confirmedEmail)) || !(password.equals(confirmedPassword))){
				request.setAttribute(Attribute.NOT_MATCHING.toString(), 1);
				String url = response.encodeURL("Pages/Registration.jsp");
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
			
			String name = request.getParameter("TextName");
			String surname = request.getParameter("TextSurname");
			Calendar birthdate = Calendar.getInstance();
			birthdate.clear();
			birthdate.set(Integer.parseInt(request.getParameter("Year")), Integer.parseInt(request.getParameter("Month")), Integer.parseInt(request.getParameter("Day")));

			Gender gender = Gender.valueOf(request.getParameter("Gender"));
			
			try {
				r.createUser(password, email, name, surname, birthdate, gender);
			} catch (InvalidDataException e) {
				// invio frase di errore
				request.setAttribute(Attribute.INVALID_DATA.toString(), 1);
				String url = response.encodeURL("/Pages/Registration.jsp");
				request.getRequestDispatcher(url).forward(request, response);
				return;
			} catch (UserAlreadyExistsException e) {
				// invio frase di errore
				request.setAttribute(Attribute.ALREADY_EXISTS.toString(), 1);
				String url = response.encodeURL("/Pages/Registration.jsp");
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
			
			request.getRequestDispatcher("/SelectAbilityRegistrationServlet").forward(request, response);
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
		request.getRequestDispatcher("/SelectAbilityRegistrationServlet").forward(request, response);
	}
	
}
