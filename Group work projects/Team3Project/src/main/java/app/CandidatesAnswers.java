package app;

/**
 * @author Miska
 */

public class CandidatesAnswers {
	
	private int ehdokas_id;
	private int vastaus;
	private int kysymys_id;
	private String etunimi;
	private String sukunimi;
	
	public CandidatesAnswers(){		
	}	
	
	public CandidatesAnswers(int ehdokas_id, int vastaus, int kysymys_id){
		this.ehdokas_id = ehdokas_id;
		this.vastaus = vastaus;
		this.kysymys_id = kysymys_id;		
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public int getEhdokas_id() {
		return ehdokas_id;
	}

	public void setEhdokas_id(int ehdokas_id) {
		this.ehdokas_id = ehdokas_id;
	}

	public int getVastaus() {
		return vastaus;
	}

	public void setVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public int getKysymys_id() {
		return kysymys_id;
	}

	public void setKysymys_id(int kysymys_id) {
		this.kysymys_id = kysymys_id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
}
