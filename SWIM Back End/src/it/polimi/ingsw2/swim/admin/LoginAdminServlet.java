package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AdministrationAuthenticationRemote;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAdminServlet
 */
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdminServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AdministrationAuthentication/remote");
			AdministrationAuthenticationRemote a = (AdministrationAuthenticationRemote) ref;

		
			// acquire the login parameters
			String username = request.getParameter("Username");
			String password = request.getParameter("Password");

			// if the login is not corrected, redirect to the home page
			
			Administrator admin;
			try {
				admin = a.authenticate(username, password);
			} catch (NoSuchUserException e) {
				request.getSession().setAttribute("LoginError", 1);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			}
			
			// obtain the Id of the admin
			Long Id = admin.getId();
			String name = admin.getUsername();
			System.err.println("ID: " + Id );
			System.err.println("user: " + name );
			// login session created, forward to the servlet that creates the home page
			request.getSession().setAttribute("Admin", name);
			request.getSession().setAttribute("Id", Id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LoadHomePageServlet");
			dispatcher.forward(request, response);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}	
	
}
