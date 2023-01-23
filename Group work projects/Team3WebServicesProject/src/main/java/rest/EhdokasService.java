package rest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.repackaged.com.google.gson.Gson;

import dao.dao;
import data.Kysymykset;



@Path("/ehdokkaat")
public class EhdokasService {
	 
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vaalikone");
	List<Ehd> ehdokkaat1;

	public EhdokasService() throws SQLException {
		ehdokkaat1 = Ehdokkaat.getEhdokkaat();
	}
	//GET ALL
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getEhdokkaat() {
		Gson gson = new Gson();
		return gson.toJson(ehdokkaat1);
	}
	//GET BY ID
	@Path("{id}")   // Example url is /ehdokkaat/1
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getEhdokas(@PathParam("id") int id) {
       
		Optional <Ehd> ehdokas = ehdokkaat1.stream().filter(b -> b.getId() == id).findAny();
		if (!ehdokas.isPresent())
			throw new NotFoundException(); // sends 404 to client 
		Gson gson = new Gson();
		return gson.toJson(ehdokas.get());
	}
	
	//EDIT IKA BY ID
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateEhdokas(@PathParam("id") int id,@FormParam("ika") float ika) {
		Optional<Ehd> ehdokas = ehdokkaat1.stream().filter(b -> b.getId() == id).findAny();
		if (!ehdokas.isPresent())
			throw new NotFoundException();
		
		ehdokas.get().setIka(ika);
		for(Ehd b : ehdokkaat1) {
			 if ( b.getIka() == ika) {;
			 Ehdokkaat.editEhdokas(b);
				 return;
			 }
		}
		 
	}
	
	//DELETE EHDOKAS BY ID
	@DELETE
        @Path("{id}")
	public void deleteEhdokas(@PathParam("id") int id) {
		 for(Ehd b : ehdokkaat1) {
			 if ( b.getId() == id) {
				 ehdokkaat1.remove(b);
				 Ehdokkaat.deleteEhdokas(b);
				 return;
			 }
		 }
                 throw new NotFoundException();
	}
	
	//ADD EHDOKAS
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addEhdokas (@FormParam("nimi") String nimi, @FormParam("ika") float ika) {
		int id = Ehdokkaat.getNextId();
		Ehd ehdokas = new Ehd(id, nimi, ika);
		Ehdokkaat.insertEhdokas(ehdokas);
		
	}	

}