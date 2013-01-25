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
			Object ref2 = jndiContext.lookup("UserManager/remote");
			AdministrationProfileManagerRemote a = (AdministrationProfileManagerRemote) ref;
			UserManagerRemote b = (UserManagerRemote)ref2;
			
			// retrieve data from the form
			String senderId = (String) request.getSession().getAttribute("Id");
			String addresseeId = request.getParameter("Addressee");
			String text = request.getParameter("Message");
			String userId = request.getParameter ("User");
			
			
			try {
				b.sendMessage(userId, text);
			} catch (NoSuchUserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				a.sendMessage(senderId, addresseeId, text);
			} catch (NoSuchUserException e) {
				request.getSession().setAttribute("DataError", 1);
				String url = response.encodeURL("/Pages/ContactUser.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
			request.getSession().setAttribute("Send", 1);
			String url = response.encodeURL("/Pages/AdminList.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
