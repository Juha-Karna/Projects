package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.security.SecurityUtils;
import dao.dao;

/**
 * @author Lauri
 */

@WebServlet(
    name = "CheckLoggedUser",
    urlPatterns = {"/checkloggeduser"}
)
public class CheckLoggedUser extends HttpServlet {
	
/*
 * If the request type is POST, the request is transferred to the method doGet
 */
	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
		  doGet(request, response);
		  
		  
	  }
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
			response.setContentType("text/html");
			
			HttpSession session = request.getSession(false);
			if (SecurityUtils.isUserLogged(session)) {
				response.getWriter().println("User: " + session.getAttribute("LoggedUser"));
			} else {
				response.getWriter().println("User not logged");
			}

	  }
 
}