package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.dao;


/*
 * @author Lauri
 * The name of the servlet is AddGame
 * and the servlet's URI (url-pattern) is 'addgame'
 */
@WebServlet(
    name = "candidatelist",
    urlPatterns = {"/candidatelist"}
)

public class candidatelist extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		dao dao = new dao();
		ArrayList<Candidate> candidate = dao.readAllCandidates();
		
		session.setAttribute("candidates", candidate);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/candidates.jsp");
		rd.forward(request, response);
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		doGet(request, response);
	}
 


}
