package rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import data.Kysymykset;




public class Ehdokkaat {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vaalikone");
	  
	private static ArrayList<Ehd> ehdokkaat1 = new ArrayList<>();
        // static initializer called when class is loaded
	static {   
		
		ehdokkaat1 = new ArrayList<>();
		EntityManager em = emf.createEntityManager();
	    List < Ehd > ehdokaslist = em.createQuery("SELECT k FROM Ehd k").getResultList();
	    em.close();
	    ehdokkaat1.add(new Ehd(1, "id / Nimi / Ika", 1));
	    ehdokkaat1.addAll(ehdokaslist);
		
	}
	
	
	public static List<Ehd> getEhdokkaat() {
	    
	    return ehdokkaat1;
	}
	
	public static void insertEhdokas(Ehd e) {
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(e);
	    em.getTransaction().commit();
	    em.close();
	    ehdokkaat1.add(e);
	  }
	
	public static void deleteEhdokas(Ehd e) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if (!em.contains(e)) {
		    e = em.merge(e);
		}
		em.remove(e);
		em.getTransaction().commit();
		em.close();
		ehdokkaat1.remove(e);
	}
	public static void editEhdokas(Ehd e) {
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.merge(e);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public static int getNextId() {
		return ehdokkaat1.stream().mapToInt( b -> b.getId()).max().getAsInt() + 1;
	}
}
