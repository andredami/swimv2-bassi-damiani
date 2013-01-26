package it.polimi.ingsw2.swim.admin;


import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadAdminListServlet
 */
public class LoadAbilityEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadAbilityEditorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public enum Attribute {
    	LIST("aliasList");
    	
    	private static final String componentName = "LoadAdminListServlet";
    	private final String name;
    	
    	private Attribute(String name){
    		this.name = name;
    	}
    	
    	@Override
    	public String toString(){
    		return componentName+"/"+name;
    	}
    }
    
    private void attributesReset(HttpServletRequest request){
    	for(Attribute a : Attribute.values()){
    		request.getSession().setAttribute(a.toString(), null);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AbilityManager/remote");
			AbilityManagerRemote a = (AbilityManagerRemote) ref;
			
			String name = request.getParameter("name");
			String desc = request.getParameter("desc");
			// ci sarebbe anche da prendere gli alias
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("desc", desc);
			String url = response.encodeURL("/Pages/AbilityEditor.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
	}

}
