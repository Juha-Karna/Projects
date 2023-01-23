package rest;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Ehd.findAll", query = "SELECT k FROM Ehd k")
public class Ehd implements Serializable{
	private static final long serialVersionUID = 1L;
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "ehdokas_id") 
	 
	private int id;
	private String nimi;
	private float ika;
	public Ehd() {
	}
	public Ehd(int id, String nimi, float ika) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.ika = ika;
	}
	public Ehd(int id) {
		this.id=id;
	}
	public Ehd(int id, float ika) {
		this.id = id;
		this.ika = ika;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public double getIka() {
		return ika;
	}
	public void setIka(float ika) {
		this.ika = ika;
	}
	@Override
	public String toString() {
		return "Ehd [id=" + id + ", nimi=" + nimi + ", ikä=" + ika + "]";
	}
}
