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

/**
 * @author Miska
 */

@WebServlet("/showcandidates")
public class ShowCandidates extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		dao dao = new dao();
		ArrayList<Candidate> candidates = dao.readAllCandidates();
		
		session.setAttribute("allcandidates", candidates);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/showallcandidates.jsp");
		rd.forward(request, response);
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		doGet(request, response);
	}

}
