package it.polimi.ingsw2.swim.admin;

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
				request.getSession().setAttribute("PasswordError", 1);
				String url = response.encodeURL("/Pages/AddNewAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			try {
				a.add(user, email, password);
			} catch (InvalidDataException e) {
				System.err.println("Errore nella validazione dei dati");
				if (e.invalidFields!=null){
				for(String s : e.invalidFields){
				System.err.println("* " + s);}}
				request.getSession().setAttribute("DataError", 1);
				String url = response.encodeURL("/Pages/AddNewAdmin.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			request.getSession().setAttribute("Inserted", 1);
			String url = response.encodeURL("/Pages/AdminList.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
