package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

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

/**
 * Servlet implementation class ValidationServlet
 */
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationServlet() {
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
			// Starting the context
			Hashtable<String,String> env = new Hashtable<String,String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			env.put(Context.PROVIDER_URL,"localhost:1099");
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AbilitySearch/remote");
			RegistrationRemote a = (RegistrationRemote) ref; 
			
			// obtain all the abilities chosen by the user
			String[] abilities = request.getParameterValues("ability");
			// if none is chosen, return with error
			if (abilities.length == 0){
				request.setAttribute("AbilityListEmpty", 1);
				forward (request, response, "../Pages/AbilitySelection.jsp");}
			
			// if there is at least one ability, try to register the user into the system
			try {
				a.registerUser(abilities);
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Send email for confirmation 
			a.sendActivationEmail();
			// forward to the validation page
			forward (request, response, "../Pages/Validation.jsp");
			
			
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
