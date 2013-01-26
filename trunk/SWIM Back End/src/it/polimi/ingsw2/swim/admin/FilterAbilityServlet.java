package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.admin.AbilityListServlet.Attribute;
import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FilterAbilityServlet
 */
public class FilterAbilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterAbilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public enum Attribute {
    	LIST("filterAbilityList"),
    	FILTER("filtering");
   
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AbilityManager/remote");
			AbilityManagerRemote a = (AbilityManagerRemote) ref;
			
			String filter = request.getParameter("FilterText");
			if (filter.isEmpty())
			{
				// redirect se vuota la richiesta
				String url = response.encodeURL("/Pages/AbilityList.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			List<Ability> list = a.retriveAbilityList(filter);
			request.getSession().setAttribute(Attribute.FILTER.toString(), 1);
			request.getSession().setAttribute(Attribute.LIST.toString(), list);
			String url = response.encodeURL("/Pages/AbilityList.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
