package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.session.Registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("Provo a scrivere qualcosa");
		String email= request.getParameter("TextEmail");
		String confirmedEmail = request.getParameter("TextConfirmEmail");
		String password = request.getParameter("TextPassword");
		String confirmedPassword = request.getParameter("TextConfirmPassword");
		if (!(email.equals(confirmedEmail)) || !(password.equals(confirmedPassword))){
			forward(request,response,"/Registration.jsp");
		}
		
		String name = request.getParameter("TextName");
		String surname = request.getParameter("TextSurname");
		Date birthdate = new Date();
		int day = Integer.parseInt(request.getParameter("Day"));
		int month = Integer.parseInt(request.getParameter("Month"));
		int year = Integer.parseInt(request.getParameter("Year"));
		birthdate.setDate(day);
		birthdate.setMonth(month);
		birthdate.setYear(year);
		String sex = request.getParameter("Gender");
		
		Registration register = new Registration();
		//register.createUser(password, email, name, surname, birthdate, sex);
		
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
		       throws ServletException, IOException
		    {
		        RequestDispatcher rd = request.getRequestDispatcher(page);
		        rd.forward(request,response);
		  }
	
	
}
