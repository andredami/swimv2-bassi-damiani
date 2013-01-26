package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.admin.ContactAdminServlet.Attribute;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;
import it.polimi.ingsw2.swim.session.remote.UserManagerRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactUserServlet
 */
public class ContactUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	 public enum Attribute {
	    	SENDER_ERROR("senderError"), ADDRESSEE_ERROR("addresseeError"), DATA_ERROR("textError"), SENT("sent");
	    	
	    	private static final String componentName = "ContactAdminServlet";
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
			Object ref = jndiContext.lookup("UserManager/remote");
			UserManagerRemote a = (UserManagerRemote) ref;
			
			// retrieve data from the form

			String addresseeId = request.getParameter("id");
			String text = request.getParameter("Message");
			
			if(text.isEmpty()){
				request.getSession().setAttribute(Attribute.DATA_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/UserContactProfile.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			
			try {
				a.sendMessage(addresseeId, text);
			} catch (Throwable e) {
				request.getSession().setAttribute(Attribute.ADDRESSEE_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/UserContactProfile.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			request.getSession().setAttribute(Attribute.SENT.toString(), 1);
			String url = response.encodeURL("/Pages/UserContactProfile.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
