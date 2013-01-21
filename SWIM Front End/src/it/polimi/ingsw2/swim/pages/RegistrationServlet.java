package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
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
			Hashtable<String,String> env = new Hashtable<String,String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			env.put(Context.PROVIDER_URL,"localhost:1099");
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("RegistrationRemote/remote");
			RegistrationRemote r = (RegistrationRemote) ref; 
			// verify password and email 
			String email= request.getParameter("TextEmail");
			String confirmedEmail = request.getParameter("TextConfirmEmail");
			String password = request.getParameter("TextPassword");
			String confirmedPassword = request.getParameter("TextConfirmPassword");
			//if (!(email.equals(confirmedEmail)) || !(password.equals(confirmedPassword))){
				//forward(request,response,"/Pages/Registration.jsp");
			//}
			// assignment
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
			//r.createUser(password, email, name, surname, birthdate, gender);
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
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Io sono un titolo!</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<h1>Questa è una prova!</h1>");
        out.println("</body>");
        out.println("</html>");
        forward(request,response,"/Pages/Registration.jsp");
	
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
		       throws ServletException, IOException
		    {
		        RequestDispatcher rd = request.getRequestDispatcher(page);
		        rd.forward(request,response);
		  }
	
	
}
