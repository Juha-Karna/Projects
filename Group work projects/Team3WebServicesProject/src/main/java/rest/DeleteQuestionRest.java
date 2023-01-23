package rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import data.*;
import dao.*;

@Path("/deletequestion")
public class DeleteQuestionRest {

  @Context HttpServletRequest request;
  @Context HttpServletResponse response;

  @GET
  @Path("/getquestionstodelete")
  public void getQuestionsToDelete() throws ServletException, IOException {
    try {
      List < Kysymykset > questionlist = new ArrayList < Kysymykset > ();
      dao dao = new dao();
      questionlist = dao.getAllQuestions();
      request.setAttribute("questionlist", questionlist);
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/deletequestion.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("/deletequestionbyid/{id}")
  public void deleteQuestionById(@PathParam("id") int id) throws ServletException, IOException {
    try {
      dao dao = new dao();
      dao.deleteQuestionById(id);
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}