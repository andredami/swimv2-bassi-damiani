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

	public enum Attribute {
		PASSWORD_ERROR("passwordError"), PASSWORD_WRONG("passwordWrong"), NOT_MATCHIN_PASSWORD("notMatchingPassword"), NO_USER(
				"noUser"), EDITED("edited");

		private static final String componentName = "ChangeAdminPasswordServlet";
		private final String name;

		private Attribute(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return componentName + "/" + name;
		}
	}

	private void attributesReset(HttpServletRequest request) {
		for (Attribute a : Attribute.values()) {
			request.getSession().setAttribute(a.toString(), null);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeAdminPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		attributesReset(request);
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext
					.lookup("AdministrationProfileManager/remote");
			AdministrationProfileManagerRemote a = (AdministrationProfileManagerRemote) ref;

			// retrieve data from the form
			String oldpsw = request.getParameter("OldPassword");
			String newpsw = request.getParameter("NewPassword");
			String conpsw = request.getParameter("ConfPassword");
			String id = request.getParameter("IdAdmin");

			if(!conpsw.equals(newpsw)){
				request.getSession().setAttribute(
						Attribute.NOT_MATCHIN_PASSWORD.toString(), 1);
				String url = response.encodeURL("/Pages/ChangePassword.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			
			if (oldpsw.equals(newpsw)) {
				request.getSession().setAttribute(
						Attribute.PASSWORD_ERROR.toString(), 1);
				String url = response.encodeURL("/Pages/ChangePassword.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}
			try {
				a.insertNewPassword(id, oldpsw, newpsw);
			} catch (InvalidPasswordException e) {
				request.getSession().setAttribute(
						Attribute.PASSWORD_WRONG.toString(), 1);
				String url = response.encodeURL("/Pages/ChangePassword.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			} catch (NoSuchUserException e) {
				request.getSession().setAttribute(Attribute.NO_USER.toString(),
						1);
				String url = response.encodeURL("/Pages/ChangePassword.jsp");
				response.sendRedirect(request.getContextPath() + url);
				return;
			}

			request.getSession().setAttribute(Attribute.EDITED.toString(), 1);
			String url = response.encodeURL("/LoadAdminListServlet");
			response.sendRedirect(request.getContextPath() + url);
			return;

		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
