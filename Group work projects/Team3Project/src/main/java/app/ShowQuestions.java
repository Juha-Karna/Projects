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

import app.Question;
import dao.dao;

/**
 * @author Miska
 */

@WebServlet("/showquestions")
public class ShowQuestions extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession();
		
		dao dao = new dao();
		ArrayList<Question> questions = dao.readAllQuestions();
		
		session.setAttribute("allquestions", questions);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/showallquestions.jsp");
		rd.forward(request, response);
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		doGet(request, response);
	}
}

