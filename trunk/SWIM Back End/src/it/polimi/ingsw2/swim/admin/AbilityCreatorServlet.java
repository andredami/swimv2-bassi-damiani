package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.exceptions.DuplicateAbilityException;
import it.polimi.ingsw2.swim.exceptions.DuplicateAliasException;
import it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote;
import it.polimi.ingsw2.swim.session.remote.RequestManagerRemote;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbilityCreatorServlet
 */
public class AbilityCreatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbilityCreatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("RequestManager/remote");
			RequestManagerRemote a = (RequestManagerRemote) ref;
			
			String name = request.getParameter("Name");
			String description = request.getParameter("Description");
			Set<String> alias;
			Set<String> stub;
			try {
				a.addNewAbility(name, description, alias, stub);
			} catch (DuplicateAliasException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateAbilityException e) {
				request.getSession().setAttribute("Duplicate", 1);
				String url = response.encodeURL("/Pages/AbilityCreation.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			String url = response.encodeURL("/Pages/AbilityCreation.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
