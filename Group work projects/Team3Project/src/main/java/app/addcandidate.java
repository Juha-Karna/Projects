package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dao;

/**
 * @author Lauri
 */

@WebServlet(
		name = "addcandidate",
		urlPatterns = {"/addcandidate"}
		)
public class addcandidate extends HttpServlet {

	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		response.setContentType("text/html");
		
		// Read parameters to Model
		Candidate candidate=readCandidate(request);
	
		// Create connection
		dao dao=new dao();
		
		// Save value and query total list
		dao.createCandidate(candidate);
		

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