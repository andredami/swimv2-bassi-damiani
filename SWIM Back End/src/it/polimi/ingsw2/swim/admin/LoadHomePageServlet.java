package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.entities.Ability;
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("RequestManager/remote");
			RequestManagerRemote a = (RequestManagerRemote) ref;
			
			List<Ability> list = a.retriveRequestsList();
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

