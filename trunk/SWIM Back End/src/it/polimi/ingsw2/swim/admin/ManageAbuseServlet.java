package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.admin.AddNewAdminServlet.Attribute;
import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.exceptions.AlreadyHandledException;
import it.polimi.ingsw2.swim.exceptions.DuplicateAdministratorException;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.session.remote.AbuseManagerRemote;
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
 * Servlet implementation class ManageAbuseServlet
 */
public class ManageAbuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAbuseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public enum Attribute {
    	LIST("abuseList"),
    	MANAGED("abuseManaged"),
    	CANCELED("abuseCanceled");
   	
    	private static final String componentName = "ManageAbuseServlet";
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
			Object ref = jndiContext.lookup("AbuseManager/remote");
			AbuseManagerRemote a = (AbuseManagerRemote) ref;

			// retrieve data from the form
			String abuseId = request.getParameter("Count");	
			
			// abuse handled
			
			if (request.getParameter("ManagedButton")!= null){
				try {
					a.markAbuseAsHandled(abuseId);
				} catch (AlreadyHandledException e) {
					e.printStackTrace();
				}
				List<Abuse> list = a.getAbuseList();
				request.getSession().setAttribute(Attribute.LIST.toString(), list);
				request.getSession().setAttribute(Attribute.MANAGED.toString(), 1);
				String url = response.encodeURL("/Pages/AbuseList.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			// abuse ignored
			
			if (request.getParameter("Remove")!= null){
				a.removeAbuse(abuseId);
				List<Abuse> list = a.getAbuseList();
				request.getSession().setAttribute(Attribute.LIST.toString(), list);
				request.getSession().setAttribute(Attribute.CANCELED.toString(), 1);
				String url = response.encodeURL("/Pages/AbuseList.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
		
			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
