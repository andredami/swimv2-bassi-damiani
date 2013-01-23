package it.polimi.ingsw2.swim.pages;


import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.session.remote.AbilitySearchRemote;
import java.io.IOException;
import java.util.ArrayList;
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
public class SelectAbilityRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// these are the desired ability of the user at the moment of registration
	private ArrayList<Ability> desired = new ArrayList<Ability>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAbilityRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			Object ref = jndiContext.lookup("AbilitySearchRemote/remote");
			AbilitySearchRemote a = (AbilitySearchRemote) ref; 
			
			// if the ability name is not specified, return with an error message
			if (request.getParameter("TextAbility") == null){
				request.setAttribute("EmptyRequest", 1);
				forward(request,response,"/Pages/AbilitySelection.jsp");
			}
			String abilityRequested = request.getParameter("TextAbility");
			// if there is no ability with that name, return a notification
			if (a.findAbility(abilityRequested).isEmpty()){
				request.setAttribute("NotFoundAbility", 1);
				forward(request,response,"/Pages/AbilitySelection.jsp");
			}
				
			// set the attribute for the jsp page
			request.setAttribute("AbilityList", a.findAbility(abilityRequested));
			desired.addAll(a.findAbility(abilityRequested));
			request.setAttribute("desired", desired);
			forward(request,response,"/Pages/AbilitySelection.jsp");
			
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
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
		       throws ServletException, IOException
		    {
		        RequestDispatcher rd = request.getRequestDispatcher(page);
		        rd.forward(request,response);
		  }
	
	
}
