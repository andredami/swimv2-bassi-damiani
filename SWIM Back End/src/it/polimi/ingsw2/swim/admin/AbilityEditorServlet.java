package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.admin.LoadAbilityEditorServlet.Attribute;
import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.exceptions.AlreadyHandledException;
import it.polimi.ingsw2.swim.exceptions.DuplicateAbilityException;
import it.polimi.ingsw2.swim.exceptions.NoSuchAbilityException;
import it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbilityEditorServlet
 */
public class AbilityEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbilityEditorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public enum Attribute {
    	MODIFY("modifyAbilityList");
    	
    	private static final String componentName = "AbilityEditorServlet";
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
		// TODO Auto-generated method stub
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
			
			// retrieve data from the form
			String name = request.getParameter("Name");
			String desc = request.getParameter("Description");
			String oldName = request.getParameter("oldName");
			String oldDesc = request.getParameter("oldDesc");
			Set<String> alias = new HashSet<String>();
			alias.add(request.getParameter("Alias"));	
						
			// insertion 
						
			if (request.getParameter("SubmitAbility")!= null){
				a.editAbilityName(oldName, name);
				a.editAbilityDescription(oldDesc, desc);
				List<Ability> list = a.retriveAbilityList();
				request.getSession().setAttribute(Attribute.MODIFY.toString(), list);
				String url = response.encodeURL("/Pages/AbilityList.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			
			// add alias 
						
			if (request.getParameter("Assegna")!= null){
				a.addAbilityAlias(oldName, alias);
				List<Ability> list = a.retriveAbilityList();
				request.getSession().setAttribute(Attribute.MODIFY.toString(), list);
				String url = response.encodeURL("/Pages/AbilityList.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
						}
			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (DuplicateAbilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAbilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
