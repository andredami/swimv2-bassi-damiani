package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.exceptions.DuplicateAdministratorException;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewAdminServlet
 */
public class AddNewAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public enum Attribute {
    	PASSWORD_ERROR("passwordError"),
    	DATA_ERROR("dataError"),
    	DUPLICATED_ERROR("duplicatedError"),
    	INSERTED("inserted");
    	
    	private static final String componentName = "AddNewAdminServlet";
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
			Object ref = jndiContext.lookup("AdministrationProfileManager/remote");
			AdministrationProfileManagerRemote a = (AdministrationProfileManagerRemote) ref;

			// retrieve data from the form
			String user = request.getParameter("Username");
			String email = request.getParameter("Email");
			String password = request.getParameter("Password");
			String confPassword = request.getParameter("ConfirmPassword");
			
			if (!(confPassword.equals(password))){
				request.getSession().setAttribute(Attribute.PASSWORD_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/AddNewAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			
			try {
				a.add(user, email, password);
			} catch (InvalidDataException e) {
				request.getSession().setAttribute(Attribute.DATA_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/AddNewAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			} catch (DuplicateAdministratorException e) {
				request.getSession().setAttribute(Attribute.DUPLICATED_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/AddNewAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			request.getSession().setAttribute(Attribute.INSERTED.toString(), 1);
			String url = response.encodeURL("/LoadAdminListServlet");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
