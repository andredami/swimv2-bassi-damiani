package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;
import it.polimi.ingsw2.swim.session.remote.RequestManagerRemote;
import java.io.IOException;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoadPageServlet
 */
public class LoadHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadHomePageServlet() {
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
			Object ref = jndiContext.lookup("RequestManager/remote");
			Object ref2 = jndiContext.lookup("AdministrationProfileManager/remote");
			RequestManagerRemote a = (RequestManagerRemote) ref;
			AdministrationProfileManagerRemote b = (AdministrationProfileManagerRemote) ref2;
			
			
			List<Ability> list = a.retriveRequestsList();
			// find the admin associated with the id of the current session
			try {
				Administrator admin = b.retrive(request.getSession().getAttribute("Id").toString());
				request.getSession().setAttribute("Username", admin);
			} catch (NoSuchUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("list", list);
			String url = response.encodeURL("/Pages/home.jsp");
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
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("RequestManager/remote");
			Object ref2 = jndiContext.lookup("AdministrationProfileManager/remote");
			RequestManagerRemote a = (RequestManagerRemote) ref;
			AdministrationProfileManagerRemote b = (AdministrationProfileManagerRemote) ref2;
			
			
			List<Ability> list = a.retriveRequestsList();
			// find the admin associated with the id of the current session
			try {
				Administrator admin = b.retrive(request.getSession().getAttribute("Id").toString());
				request.getSession().setAttribute("Username", admin);
			} catch (NoSuchUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("list", list);
			String url = response.encodeURL("/Pages/home.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
		
	}

