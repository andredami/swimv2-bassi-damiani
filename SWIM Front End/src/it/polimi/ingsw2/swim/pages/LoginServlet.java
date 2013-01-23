package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.session.remote.AuthenticationRemote;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.PROVIDER_URL,"localhost:1099");
		InitialContext jndiContext = new InitialContext();
		Object ref = jndiContext.lookup("AuthenticationRemote/remote");
		AuthenticationRemote a = (AuthenticationRemote) ref; 		
		
		// autenticazione
		String email = request.getParameter("emailText");
		String pass = request.getParameter("Password");
		if (!(a.authenticate(email, pass))){
			request.setAttribute("Errore", 1);
			forward(request,response,"../index.jsp");
		}	
		// if the authentication is correct, start the session and redirect the user to the home page
		HttpSession session = request.getSession();
		session.setAttribute("emailText", email);
		forward(request, response, "Pages/HomePage.jsp");
		//response.sendRedirect(response.encodeRedirectURL("Pages/HomePage.jsp"));
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
		       throws ServletException, IOException
		    {
		        RequestDispatcher rd = request.getRequestDispatcher(page);
		        rd.forward(request,response);
		    }
	
	}
