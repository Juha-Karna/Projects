package rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import dao.dao;
import data.*;

@Path("/addquestion")
public class AddQuestionRest {
  @Context HttpServletRequest request;
  @Context HttpServletResponse response;

  @GET
  @Path("/addnewquestionpage")
  public void addNewQuestionPage() throws ServletException, IOException {
    try {
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/addnewquestion.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @POST
  @Path("/addnewquestion")
  public void addNewQuestion(@FormParam("kysymys") String kysymys) throws ServletException, IOException, SQLException {
    try {
      dao dao = new dao();
      Kysymykset k = new Kysymykset();
      k.setKysymys(kysymys);
      dao.addQuestion(k);
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}