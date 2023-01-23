package app;

import java.io.IOException;
import java.io.PrintWriter;

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
    name = "AdminLogin",
    urlPatterns = {"/adminlogin"}
)
public class AdminLogin extends HttpServlet {
	static final String NEW_HOST = "jsp/yllapito.jsp";

	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {

	    response.sendRedirect("jsp/index.jsp");

	  }
	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		  
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  
		  	dao dao = new dao();
		  	
		  	String uname = request.getParameter("username");
		  	String password = request.getParameter("password");
		  	
		  	String salt = dao.getUserSalt(uname);
		  	String hashpw = dao.getUserPasswordHash(uname);
		  	
		  	dao.close();
		  	
		  	if ( SecurityUtils.isPassordOk(hashpw, password, salt) ) {
		  		response.getWriter().println("Login success");
		  		
		  		HttpSession session = request.getSession();
		  		
		  		session.setAttribute("AdminUser", uname);
		  		
		  	    String newLocation = NEW_HOST;

		  	    PrintWriter out = response.getWriter();
		  	    response.setHeader("Refresh", "10; URL=" + newLocation);

		  	    out.println("please wait");
		  	    out.println("Your browser will forward you in 5 seconds.");
		  		
		  	} else {
		  		response.getWriter().println("Login failed");
		  	}
	  }
	}
