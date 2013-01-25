package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote;
import it.polimi.ingsw2.swim.session.remote.UserManagerRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadUserProfileServlet
 */
public class LoadUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadUserProfileServlet() {
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
			
			// come ottengo lo userID?
			
			User user = a.retriveUserProfile(userId);
			
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
			
			String url = response.encodeURL("/Pages/UserContactProfile.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
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
