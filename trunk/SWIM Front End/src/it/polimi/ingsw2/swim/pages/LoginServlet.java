package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.session.remote.AuthenticationRemote;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		// Starting the context
		try {
		InitialContext jndiContext = new InitialContext();
		Object ref = jndiContext.lookup("Authentication/remote");
		AuthenticationRemote a = (AuthenticationRemote) ref; 		
		
		// authentication
		String email = request.getParameter("emailText");
		String pass = request.getParameter("Password");
		if (!(a.authenticate(email, pass))){
			request.getSession().setAttribute("ErrorLogin", 1);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}	
		// if the authentication is correct, start the session and redirect the user to the home page
		HttpSession session = request.getSession();
		session.setAttribute("emailText", email);
		response.sendRedirect(request.getContextPath() + "/Pages/HomePage.jsp");
		return;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	}
