package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AbuseManagerRemote;
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
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("UserManager/remote");
			UserManagerRemote a = (UserManagerRemote) ref;
			
			String userId = (String) request.getSession().getAttribute("Id");
			User user = a.retriveUserProfile(userId);
			
			request.getSession().setAttribute("Id", userId);
			request.getSession().setAttribute("Name", user.getName().getFirstname());
			request.getSession().setAttribute("Surname", user.getName().getSurname());
			request.getSession().setAttribute("Gender", user.getGender());
			request.getSession().setAttribute("Email", user.getEmail());
			request.getSession().setAttribute("Birthdate", user.getBirthdate());
			request.getSession().setAttribute("Address", user.getAddress());
			request.getSession().setAttribute("Phone Number", user.getTelephone());
			request.getSession().setAttribute("Cellphone", user.getMobile());
			request.getSession().setAttribute("Fax", user.getFax());
			request.getSession().setAttribute("Skype", user.getSkype());
			request.getSession().setAttribute("Image", user.getPicture());
			Set<Ability> abilities = user.getAbilities();
			request.getSession().setAttribute("abilities", abilities);
			Set <User> friends = user.getFriendships();
			request.getSession().setAttribute("friends", friends);
			
			String url = response.encodeURL("/Pages/Profile.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (NoSuchUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
