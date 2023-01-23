package app;

import java.io.Serializable;

/**
 * @author Miska
 */

public class Question implements Serializable {
	private int id;
	private String question;
	
	public Question(String id, String question) {
		setId(id);
		this.question=question;
	}
	
	public Question() {
	}
	
	public Question(String question) {
		this.question = question;
		setQuestion(question);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {			
		}
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
}
