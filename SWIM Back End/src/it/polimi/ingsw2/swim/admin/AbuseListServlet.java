package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.admin.ManageAbuseServlet.Attribute;
import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.session.remote.AbuseManagerRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbuseListServlet
 */
public class AbuseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbuseListServlet() {
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
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("AbuseManager/remote");
			AbuseManagerRemote a = (AbuseManagerRemote) ref;
			
			List<Abuse> list = a.getAbuseList();
			request.getSession().setAttribute(Attribute.LIST.toString(), list);
			String url = response.encodeURL("/Pages/AbuseList.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
