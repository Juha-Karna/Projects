package rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.dao;
import data.*;

@Path("/editquestion")
public class EditQuestionRest {
  @Context HttpServletRequest request;
  @Context HttpServletResponse response;

  @GET
  @Path("/getquestionstoedit")
  @Produces(MediaType.APPLICATION_JSON)
  public void getQuestionsToEdit() throws ServletException, IOException {
    try {
      List < Kysymykset > questionlist = new ArrayList < Kysymykset > ();
      dao dao = new dao();
      questionlist = dao.getAllQuestions();
      request.setAttribute("questionlist", questionlist);
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestionstoedit.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @GET
  @Path("/getquestionbyid/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void getQuestionById(@PathParam("id") int id) throws ServletException, IOException {
    try {
      Kysymykset k = new Kysymykset();
      dao dao = new dao();
      k = dao.getQuestionById(id);
      request.setAttribute("kysymys", k);
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestiontoedit.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @POST
  @Path("/editquestion")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes("application/x-www-form-urlencoded")
  public void editQuestion(@FormParam("kysymysId") int kysymysId, @FormParam("kysymys") String kysymysString) {
    try {
      dao dao = new dao();
      Kysymykset k = new Kysymykset(kysymysId, kysymysString);
      System.out.println(k.getKysymysId());
      dao.editQuestion(k);
      RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}