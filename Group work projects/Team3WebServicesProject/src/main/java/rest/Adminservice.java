package rest;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.geronimo.mail.util.Base64;

import app.security.SecurityUtils;
import dao.dao;
import data.admin;

@Path("/adminservice")

public class Adminservice {
	
//Reading all the rows from table prey.
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<admin> readAllAdmin() {
	//Create an EntityManagerFactory with the settings from persistence.xml file
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Vaalikone");
		//And then EntityManager, which can manage the entities.
		EntityManager em=emf.createEntityManager();
		
		//Read all the rows from table admin. Here the admin must start with capital, 
		//because class's name starts. This returns a List of admin objects.
		List<admin> list=em.createQuery("select a from admin a").getResultList();
		return list;
	}
	
	//Adding one admin object into the table admin
		@POST
		@Path("/addadmin")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public admin postAdmin(admin admin) {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Vaalikone");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(admin);//The actual insertion line
			em.getTransaction().commit();
			return admin;
		}
	
		//This method uses FormParams, but does the same as previous	
		@POST
		@Path("/addadmin")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public admin postAdminByParams(@FormParam("username") String username, @FormParam("password") String password, String login) {
			
	
			admin admin=new admin(username, password, login);
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Vaalikone");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(admin);
			em.getTransaction().commit();
			
			return admin;

			
			
		}
		@GET
		@Path("/readadmin")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<admin> readAdmin() {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Vaalikone");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			List<admin> list=em.createQuery("select a from admin a").getResultList();
			em.getTransaction().commit();
			return list;
		}
		
		@GET
		@Path("/readadmin1")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<admin> readAdmin1() {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Vaalikone");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<admin> login=em.createQuery("select a from admin a").getResultList();
		em.getTransaction().commit();
		
		for (admin a:login) {
			System.out.println(a.getLogin());
		}
		return login;
		}
		

}