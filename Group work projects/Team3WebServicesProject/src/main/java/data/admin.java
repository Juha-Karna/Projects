package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="admin.findAll", query="SELECT a FROM admin a")
public class admin implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String login;
	
	public admin() {
		super();
	}
	public admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public admin(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public admin(String username, String password, String login) {
		super();
		this.username = username;
		this.password = password;
		this.login = username+":"+password;
	}
	
	
	public admin(int id, String username, String password, String login) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.login = username+":"+password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = username+":"+password;
	}
	@Override
	public String toString() {
		return "admin [id=" + id + ", username=" + username + ", password=" + password + ", login=" + login + "]";
	}
	
}