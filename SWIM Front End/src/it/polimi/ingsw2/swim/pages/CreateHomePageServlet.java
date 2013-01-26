package it.polimi.ingsw2.swim.pages;

import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.entities.FriendshipRequest;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.Notification;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.AbuseManagerRemote;
import it.polimi.ingsw2.swim.session.remote.NotificationManagerRemote;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateHomePageServlet
 */
public class CreateHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateHomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public enum Attribute {
    	FRIEND("friendList"),
    	HELP("helpList"),
    	MESSAGE("messageList"),
    	NOTIFICATION("notificationList");
   	
    	private static final String componentName = "CreateHomePageServlet";
    	private final String name;
    	
    	private Attribute(String name){
    		this.name = name;
    	}
    	
    	@Override
    	public String toString(){
    		return componentName+"/"+name;
    	}
    }
    
    private void attributesReset(HttpServletRequest request){
    	for(Attribute a : Attribute.values()){
    		request.getSession().setAttribute(a.toString(), null);
    	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attributesReset(request);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			attributesReset(request);
		
		try {
			// create the context
			InitialContext jndiContext = new InitialContext();
			Object ref = jndiContext.lookup("NotificationManager/remote");
			NotificationManagerRemote a = (NotificationManagerRemote) ref;
			
			String userId = (String) request.getSession().getAttribute("Id");
			List<FriendshipRequest> friendList = a.retriveFriendshipRequestsByUser(userId);
			List<Help> helpRequestList = a.retriveHelpRelationStatusByUser(userId);
			List<Message> incomingMessageList = a.retriveIncomingMessagesByUser(userId);
			List<Notification> notificationList = a.retriveNotificationsByUser(userId);
			
			request.getSession().setAttribute(Attribute.FRIEND.toString(), friendList);
			request.getSession().setAttribute(Attribute.HELP.toString(), helpRequestList);
			request.getSession().setAttribute(Attribute.MESSAGE.toString(), incomingMessageList);
			request.getSession().setAttribute(Attribute.NOTIFICATION.toString(), notificationList);
			String url = response.encodeURL("/Pages/HomePage.jsp");
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

}
