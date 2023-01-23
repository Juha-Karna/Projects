package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.persistence.EntityManager;

import com.google.appengine.repackaged.com.google.gson.Gson;
 

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOehdokas DAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        DAO = new DAOehdokas(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertEhdokas(request, response);
                break;
            case "/delete":
                deleteEhdokas(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateEhdokas(request, response);
                break;
            default:
                listEhdokas(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    //LIST (/list)
    private void listEhdokas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Ehdokas> listEhdokas = DAO.listAllEhdokas();
        request.setAttribute("listEhdokas", listEhdokas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EhdokasList.jsp");
        dispatcher.forward(request, response);
    }
   //ADD FORM (/new)
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EhdokasForm.jsp");
        dispatcher.forward(request, response);
    }
    //EDIT FORM (/edit)
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ehdokas existingEhdokas = DAO.getEhdokas(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EhdokasForm.jsp");
        request.setAttribute("ehdokas", existingEhdokas);
        dispatcher.forward(request, response);
 
    }
    //ADD (/insert)
    private void insertEhdokas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String puolue = request.getParameter("puolue");
        String nimi = request.getParameter("nimi");
        float ika = Float.parseFloat(request.getParameter("ika"));
 
        Ehdokas newEhdokas = new Ehdokas(puolue, nimi, ika);
        DAO.insertEhdokas(newEhdokas);
        response.sendRedirect("list");
    }
    //EDIT (/update)
    private void updateEhdokas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String puolue = request.getParameter("puolue");
        String nimi = request.getParameter("nimi");
        float ika = Float.parseFloat(request.getParameter("ika"));
 
        Ehdokas ehdokas = new Ehdokas(id, puolue, nimi, ika);
        DAO.updateEhdokas(ehdokas);
        response.sendRedirect("list");
    }
    //DELETE (/delete)
    private void deleteEhdokas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Ehdokas ehdokas = new Ehdokas(id);
        DAO.deleteEhdokas(ehdokas);
        response.sendRedirect("list");
 
    }
    
   
}
