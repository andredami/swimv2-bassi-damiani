package it.polimi.ingsw2.swim.pages;


import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.session.remote.AbilitySearchRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
/**
 * Servlet implementation class RegistrationServlet
 */
public class SelectAbilityRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public enum Attribute {
		LIST("list"), EMPTY_SEARCH("errorEmptySearch"), SEARCH_KEY("searchKey");

		private static final String componentName = "SelectAbilityRegistrationServlet";
		private final String name;

		private Attribute(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return componentName + "/" + name;
		}
	}

	private void attributesReset(HttpServletRequest request) {
		for (Attribute a : Attribute.values()) {
			request.getSession().setAttribute(a.toString(), null);
		}
	}
	
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
		attributesReset(request);
		try {
			// Starting the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AbilitySearch/remote");
			AbilitySearchRemote a = (AbilitySearchRemote) ref; 
			
			List<Ability> abilityList = null;
			if(request.getParameter("AbilitySelection.jsp/search")!=null){
				String searchKey = request.getParameter("TextAbility");
				if(searchKey == null || searchKey.isEmpty()){
					System.err.println("non trovo TextAbility");
					request.getSession().setAttribute(Attribute.EMPTY_SEARCH.toString(), 1);
					abilityList = a.findAbility("");
				} else {
					System.err.println("trovo TextAbility e filtro per "+searchKey);
					request.getSession().setAttribute(Attribute.SEARCH_KEY.toString(), searchKey);
					abilityList = a.findAbility(searchKey);
				}
			} else {
				abilityList = a.findAbility("");
			}
			
			
			System.err.println("lista lunga: "+abilityList.size());
			request.getSession().setAttribute(Attribute.LIST.toString(), abilityList);
			String url = response.encodeURL("/Pages/AbilitySelection.jsp");
			response.sendRedirect(request.getContextPath() + url);
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
		attributesReset(request);
	}
	
	
	
}
