package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeAdminPasswordServlet
 */
public class ChangeAdminPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAdminPasswordServlet() {
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
			String oldpsw = request.getParameter("OldPassword");
			String newpsw = request.getParameter("NewPassword");
			String confPassword = request.getParameter("ConfirmNewPassword");
			
			if (!(confPassword.equals(newpsw))){
				request.getSession().setAttribute("PasswordError", 1);
				String url = response.encodeURL("/Pages/ChangePassword.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
				// è sbagliato, non c'è l'adminID
				try {
					a.insertNewPassword(confPassword, oldpsw, newpsw);
				} catch (InvalidPasswordException e) {
					request.getSession().setAttribute("PasswordError", 1);
					String url = response.encodeURL("/Pages/ChangePassword.jsp");
					response.sendRedirect(request.getContextPath() + url);
					return;
				} catch (NoSuchUserException e) {
					request.getSession().setAttribute("PasswordError", 1);
					String url = response.encodeURL("/Pages/ChangePassword.jsp");
					response.sendRedirect(request.getContextPath() + url);
					return;
				}
			
			request.getSession().setAttribute("PasswordChanged", 1);
			String url = response.encodeURL("/Pages/AdminList.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		

}
