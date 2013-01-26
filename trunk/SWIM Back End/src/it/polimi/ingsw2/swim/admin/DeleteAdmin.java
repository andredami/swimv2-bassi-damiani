package it.polimi.ingsw2.swim.admin;

import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAdmin
 */
public class DeleteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			AdministrationProfileManagerRemote profileManager = (AdministrationProfileManagerRemote) ctx.lookup("AdministrationProfileManager/remote");
			String adminId = request.getParameter("Id");
			if(adminId != null){
				try {
					profileManager.delete(adminId);
				} catch (Exception e) {	
				}
			}	
			String url = response.encodeURL("/LoadAdminListServlet");
			response.sendRedirect(request.getContextPath() + url);
			return;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
