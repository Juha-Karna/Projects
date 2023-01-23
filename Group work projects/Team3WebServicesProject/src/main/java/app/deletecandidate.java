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



/*
 * @author Lauri
 */
@WebServlet(
    name = "deletecandidate",
    urlPatterns = {"/deletecandidate"}
)
public class deletecandidate extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		if( request.getSession().getAttribute("AdminUser") != null)
		{
		String idValue = request.getParameter("ehdokas_id");
		
		if ( idValue != null) {
			try {
				int id = Integer.parseInt(idValue);
				
				dao dao = new dao();
				Candidate candidate = dao.getCandidateInfo(id);
				session.setAttribute("candidate", candidate);
				
				
				dao.deleteCandidate(candidate);
				
				response.sendRedirect("/candidatelist");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}} else {
			response.sendRedirect("/candidatelist");
		}
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {

		// Create connection
		dao dao=new dao();
		Candidate candidate = readCandidate(request);
		
		dao.updateCandidate(candidate);
		
		dao.close();
		
		response.sendRedirect("/candidatelist");
	
	}
	
	private Candidate readCandidate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Candidate candidate=new Candidate();
		candidate.setEhdokas_id(Integer.parseInt(request.getParameter("ehdokas_id")));
		candidate.setSukunimi(request.getParameter("sukunimi"));
		candidate.setEtunimi(request.getParameter("etunimi"));
		candidate.setPuolue(request.getParameter("puolue"));
		candidate.setKotipaikkakunta(request.getParameter("kotipaikkakunta"));
		candidate.setIka(Integer.parseInt(request.getParameter("ika")));
		candidate.setMiksi_eduskuntaan(request.getParameter("miksi_eduskuntaan"));
		candidate.setMita_asioita_haluat_edistaa(request.getParameter("mita_asioita_haluat_edistaa"));
		candidate.setAmmatti(request.getParameter("ammatti"));
		
		return candidate;
	}
 
}