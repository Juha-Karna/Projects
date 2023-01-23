package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.*;

/**
 * @author Miska, Lauri
 */

public class dao {
	private static Connection conn;
	public dao() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "appuser", "kukkuu");
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAdmin(String username, String pw, String salt) {
		String sql = "insert into adminaccount (username, hashedpassword, salt) values(?,?,?)";
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			stmt.setString(2, pw);
			stmt.setString(3, salt);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getUserSalt(String username) {
		String result = "";
		String sql = "select salt from adminaccount where username = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next() ) {
				result = rs.getString("salt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getUserPasswordHash(String username) {
		String result = "";
		String sql = "select hashedpassword from adminaccount where username = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next() ) {
				result = rs.getString("hashedpassword");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Question getQuestionInfo(int id) {
		Question result = null;
		String sql = "select * from KYSYMYKSET where KYSYMYS_ID = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
						
			stmt.setInt(1, id);
			
			ResultSet resultset = stmt.executeQuery();
			
			if (resultset.next()) {
				result = new Question();
				result.setId(resultset.getInt("KYSYMYS_ID"));
				result.setQuestion(resultset.getString("KYSYMYS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateQuestion(Question question) {
		int count = 0;
		String sql = "update KYSYMYKSET set KYSYMYS_ID = ?, KYSYMYS = ? where KYSYMYS_ID = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, question.getId());
			stmt.setString(2, question.getQuestion());
			stmt.setInt(3, question.getId());
			
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int deleteQuestion(Question question) {
		int count = 0;
		String sql = "DELETE FROM KYSYMYKSET WHERE KYSYMYS_ID = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, question.getId());

			
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public void createQuestion(Question question) {
		try {
			String sql = "insert into KYSYMYKSET (KYSYMYS_ID, KYSYMYS) values (?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, question.getId());
			preparedStmt.setString(2, question.getQuestion());
			preparedStmt.executeUpdate();

		} catch (SQLException e) {

		}
	}
	
	public ArrayList<Candidate> readAllCandidates() {
		ArrayList<Candidate> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ehdokkaat");
			while (rs.next()) {
				Candidate c = new Candidate();
				c.setEhdokas_id(rs.getInt("ehdokas_id"));
				c.setSukunimi(rs.getString("sukunimi"));
				c.setEtunimi(rs.getString("etunimi"));
				c.setPuolue(rs.getString("puolue"));
				c.setKotipaikkakunta(rs.getString("kotipaikkakunta"));
				c.setIka(rs.getInt("ika"));
				c.setMiksi_eduskuntaan(rs.getString("miksi_eduskuntaan"));
				c.setMita_asioita_haluat_edistaa(rs.getString("mita_asioita_haluat_edistaa"));
				c.setAmmatti(rs.getString("ammatti"));
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Candidate readCandidate(String id) {
		try {
			Candidate c = null;
			PreparedStatement ptmt = conn.prepareStatement("select * from ehdokkaat where ehdokas_id=?");
			ptmt.setString(1, id);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				c = new Candidate();
				c.setEhdokas_id(rs.getInt("ehdokas_id"));
				c.setSukunimi(rs.getString("sukunimi"));
				c.setEtunimi(rs.getString("etunimi"));
				c.setPuolue(rs.getString("puolue"));
				c.setKotipaikkakunta(rs.getString("kotipaikkakunta"));
				c.setIka(rs.getInt("ika"));
				c.setMiksi_eduskuntaan(rs.getString("miksi_eduskuntaan"));
				c.setMita_asioita_haluat_edistaa(rs.getString("mita_asioita_haluat_edistaa"));
				c.setAmmatti(rs.getString("ammatti"));
			}
			return c;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public ArrayList<Question> readAllQuestions() {
		ArrayList<Question> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from kysymykset");
			while (rs.next()) {
				Question q = new Question();
				q.setId(rs.getInt("kysymys_id"));
				q.setQuestion(rs.getString("kysymys"));
				list.add(q);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public ArrayList<CandidatesAnswers> readCandidatesAnswers(int id) {
		ArrayList<CandidatesAnswers> list = new ArrayList<>();
		try {
			PreparedStatement ptmt = conn.prepareStatement("select * from vastaukset where ehdokas_id=? order by kysymys_id");
			ptmt.setInt(1, id);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				CandidatesAnswers a = new CandidatesAnswers();
				a.setEhdokas_id(rs.getInt("ehdokas_id"));
				a.setVastaus(rs.getInt("vastaus"));
				a.setKysymys_id(rs.getInt("kysymys_id"));
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public int deleteCandidate(Candidate candidate) {
		int count = 0;
		String sql = "DELETE FROM ehdokkaat WHERE ehdokas_id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, candidate.getEhdokas_id());

			
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public Candidate getCandidateInfo(int id) {
		Candidate result = null;
		String sql = "select * from ehdokkaat where ehdokas_id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
						
			stmt.setInt(1, id);
			
			ResultSet resultset = stmt.executeQuery();
			
			if (resultset.next()) {
				result = new Candidate();
				result.setEhdokas_id(resultset.getString("ehdokas_id"));
				result.setSukunimi(resultset.getString("sukunimi"));
				result.setEtunimi(resultset.getString("etunimi"));
				result.setPuolue(resultset.getString("puolue"));
				result.setKotipaikkakunta(resultset.getString("kotipaikkakunta"));
				result.setIka(resultset.getInt("ika"));
				result.setMiksi_eduskuntaan(resultset.getString("miksi_eduskuntaan"));
				result.setMita_asioita_haluat_edistaa(resultset.getString("mita_asioita_haluat_edistaa"));
				result.setAmmatti(resultset.getString("ammatti"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int updateCandidate(Candidate candidate) {
		int count = 0;
		String sql = "update ehdokkaat set ehdokas_id = ?, sukunimi = ?, etunimi = ?, puolue = ?, kotipaikkakunta = ?, ika = ?, miksi_eduskuntaan = ?, mita_asioita_haluat_edistaa = ?, ammatti = ?  where ehdokas_id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, candidate.getEhdokas_id());
			stmt.setString(2, candidate.getSukunimi());
			stmt.setString(3, candidate.getEtunimi());
			stmt.setString(4, candidate.getPuolue());
			stmt.setString(5, candidate.getKotipaikkakunta());
			stmt.setInt(6, candidate.getIka());
			stmt.setString(7, candidate.getMiksi_eduskuntaan());
			stmt.setString(8, candidate.getMita_asioita_haluat_edistaa());
			stmt.setString(9, candidate.getAmmatti());
			
			stmt.setInt(10, candidate.getEhdokas_id());
			
			
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public void createCandidate(Candidate candidate) {
		{
			try {
				String sql = "insert into ehdokkaat (ehdokas_id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan, mita_asioita_haluat_edistaa, ammatti) values (?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setInt(1, candidate.getEhdokas_id());
				preparedStmt.setString(2, candidate.getSukunimi());
				preparedStmt.setString(3, candidate.getEtunimi());
				preparedStmt.setString(4, candidate.getPuolue());
				preparedStmt.setString(5, candidate.getKotipaikkakunta());
				preparedStmt.setInt(6, candidate.getIka());
				preparedStmt.setString(7, candidate.getMiksi_eduskuntaan());
				preparedStmt.setString(8, candidate.getMita_asioita_haluat_edistaa());
				preparedStmt.setString(9, candidate.getAmmatti());
				
				preparedStmt.executeUpdate();

			} catch (SQLException e) {

			}
		}
		
	}
		
	
}