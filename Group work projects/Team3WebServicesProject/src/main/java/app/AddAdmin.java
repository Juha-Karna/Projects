package app;

import java.io.IOException;

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
    name = "AddAdminServlet",
    urlPatterns = {"/addadmin"}
)
public class AddAdmin extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    response.sendRedirect("/jsp/index.jsp");
    
    

  }
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
	  
	  	dao dao = new dao();
	  	
	  	String uname = request.getParameter("username");
	  	String password = request.getParameter("password");
	  	
	  	String salt = SecurityUtils.getSalt();
	  	String hashpw = SecurityUtils.getPasswordHashed(password, salt);
	  	
	  	if (uname == "" && hashpw == "") {
	  	dao.addAdmin(uname, hashpw, salt);
	  	} else {
	  		dao.close();
	  	}
	  	dao.close();
		
	  	response.sendRedirect("/jsp/index.jsp");
	  	
  }
}