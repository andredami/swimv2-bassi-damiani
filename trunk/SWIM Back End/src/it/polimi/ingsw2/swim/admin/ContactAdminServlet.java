package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactAdminServlet
 */
public class ContactAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
     * @see HttpServlet#HttpServlet()
     */
    public ContactAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			Object ref = jndiContext.lookup("AdministrationProfileManager/remote");
			AdministrationProfileManagerRemote a = (AdministrationProfileManagerRemote) ref;
			
			// retrieve data from the form
			String senderId;
			try {
				senderId = request.getSession().getAttribute("Id").toString();
			} catch (NullPointerException e1) {
				request.getSession().setAttribute(Attribute.SENDER_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/ContactAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			
			String addresseeId = request.getParameter("AddresseeId");
			String text = request.getParameter("Message");
			
			if(text.isEmpty()){
				request.getSession().setAttribute(Attribute.DATA_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/ContactAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			
			try {
				a.sendMessage(senderId, addresseeId, text);
			} catch (Throwable e) {
				request.getSession().setAttribute(Attribute.ADDRESSEE_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/ContactAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			request.getSession().setAttribute(Attribute.SENT.toString(), 1);
			String url = response.encodeURL("/Pages/ContactAdmin.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
