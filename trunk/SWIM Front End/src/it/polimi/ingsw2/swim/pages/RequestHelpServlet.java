package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.LocationMissingException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.UserDirectoryManagerRemote;
import it.polimi.ingsw2.swim.session.remote.UserManagerRemote;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestHelpServlet
 */
public class RequestHelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestHelpServlet() {
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
			Object ref = jndiContext.lookup("UserDirectoryManager/remote");
			UserDirectoryManagerRemote a = (UserDirectoryManagerRemote) ref;
			Object ref2 = jndiContext.lookup("UserManager/remote");
			UserManagerRemote b = (UserManagerRemote) ref2;
			
		
			String userId = request.getParameter("user");
			String abilityName = (String) request.getSession().getAttribute("TextAbility");
			User user = b.retriveUserProfile(userId);
			
			List<User> listUser = a.findUserByAbility(userId, abilityName, user.getAddress().getCity(), 0, 0);
			request.getSession().setAttribute("listUser", listUser);
			String url = response.encodeURL("/Pages/RequestForHelp.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String url = response.encodeURL("/Pages/RequestForHelp.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		} catch (NoSuchUserException e) {
			String url = response.encodeURL("/Pages/RequestForHelp.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		} catch (LocationMissingException e) {
			String url = response.encodeURL("/Pages/RequestForHelp.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		} catch (InvalidDataException e) {
			String url = response.encodeURL("/Pages/RequestForHelp.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
		
		
		
	}

}
