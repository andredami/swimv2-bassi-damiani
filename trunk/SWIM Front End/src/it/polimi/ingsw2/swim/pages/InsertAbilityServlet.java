package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.session.remote.AbilitySearchRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertAbilityServlet
 */
public class InsertAbilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAbilityServlet() {
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
		
		try {
			// Starting the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AbilitySearch/remote");
			AbilitySearchRemote a = (AbilitySearchRemote) ref; 
			
			String ability = request.getParameter("ChosenAbility");
			a.findAbility(ability);
			request.getSession().setAttribute("Ability", ability);
			
			// if the ability name is not specified, return with an error message
			if (request.getParameter("TextAbility").isEmpty()){
				request.getSession().setAttribute("EmptyRequest", 1);
				response.sendRedirect(request.getContextPath() + "/Pages/AbilitySelection.jsp");
				return;
			}
			String abilityRequested = request.getParameter("TextAbility");
			// if there is no ability with that name, return a notification
			if (a.findAbility(abilityRequested).isEmpty()){
				request.getSession().setAttribute("NotFoundAbility", 1);
				response.sendRedirect(request.getContextPath() + "/Pages/AbilitySelection.jsp");
				return;
			}
				
			// set the attribute for the jsp page
			request.getSession().setAttribute("AbilityList", a.findAbility(abilityRequested));
			response.sendRedirect(request.getContextPath() + "/Pages/AbilitySelection.jsp");
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
