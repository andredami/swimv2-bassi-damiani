package it.polimi.ingsw2.swim.admin;

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
 * Servlet implementation class ContactAdminServlet
 */
public class ContactAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			String senderId = request.getSession().getAttribute("Id").toString();
			String addresseeId = request.getParameter("id");
			String text = request.getParameter("Message");
			
			try {
				a.sendMessage(senderId, addresseeId, text);
			} catch (NoSuchUserException e) {
				request.getSession().setAttribute("DataError", 1);
				String url = response.encodeURL("/Pages/ContactUser.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
			request.getSession().setAttribute("Send", 1);
			String url = response.encodeURL("/Pages/home.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
