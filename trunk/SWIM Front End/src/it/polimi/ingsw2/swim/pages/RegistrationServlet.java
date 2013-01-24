package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;
import java.util.Date;

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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// Starting the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("Registration/remote");
			RegistrationRemote r = (RegistrationRemote) ref; 
			
			
			String email= request.getParameter("TextEmail");
			String confirmedEmail = request.getParameter("TextConfirmEmail");
			String password = request.getParameter("TextPassword");
			String confirmedPassword = request.getParameter("TextConfirmPassword");
			// verify password and email 
			if (!(email.equals(confirmedEmail)) || !(password.equals(confirmedPassword))){
				request.getSession().setAttribute("Wrong", 1);
				response.sendRedirect(request.getContextPath() + "/Pages/Registration.jsp");
				return;
			}
			// assignment
			String name = request.getParameter("TextName");
			String surname = request.getParameter("TextSurname");
			Date birthdate = new Date();
			//if ((request.getParameter("Day").equals("")) || (request.getParameter("Month").equals("")) || (request.getParameter("Year").equals("")))
			int day = Integer.parseInt(request.getParameter("Day"));
			int month = Integer.parseInt(request.getParameter("Month"));
			int year = Integer.parseInt(request.getParameter("Year"));
			birthdate.setDate(day);
			birthdate.setMonth(month);
			birthdate.setYear(year);
			Gender gender = Gender.valueOf(request.getParameter("Gender"));
			try {
				r.createUser(password, email, name, surname, birthdate, gender);
			} catch (InvalidDataException e) {
				// invio frase di errore
				request.getSession().setAttribute("DataException", 1);
				response.sendRedirect(request.getContextPath() + "/Pages/Registration.jsp");
				return;
			} catch (UserAlreadyExistsException e) {
				// invio frase di errore
				request.getSession().setAttribute("AlreadyExists", 1);
				response.sendRedirect(request.getContextPath() + "/Pages/Registration.jsp");
				return;
			}
			request.getSession().setAttribute("Registration", 1);
			//request.getSession().setAttribute("utente", utente);
			response.sendRedirect(request.getContextPath() + "/Pages/AbilityRequest.jsp");
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
