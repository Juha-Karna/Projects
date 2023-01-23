package services;

import java.util.Optional;

public class Ehdokas {
    protected int id;
    protected String puolue;
    protected String nimi;
    protected float ika;
 
    public Ehdokas() {
    }
 
    public Ehdokas(int id) {
        this.id = id;
    }
 
    public Ehdokas(int id, String puolue, String nimi, float ika) {
        this(puolue, nimi, ika);
        this.id = id;
    }
     
    public Ehdokas(String puolue, String nimi, float ika) {
        this.puolue = puolue;
        this.nimi = nimi;
        this.ika = ika;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getPuolue() {
        return puolue;
    }
 
    public void setPuolue(String puolue) {
        this.puolue = puolue;
    }
 
    public String getNimi() {
        return nimi;
    }
 
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
 
    public float getIka() {
        return ika;
    }
 
    public void setIka(float ika) {
        this.ika = ika;
    }
    @Override
	public String toString() {
		return "Ehdokas [id=" + id + ", puolue=" + puolue + ", nimi="+ nimi +", ika=" + ika + "]";
	}

}
