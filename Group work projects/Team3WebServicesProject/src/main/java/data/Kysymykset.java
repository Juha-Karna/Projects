package data;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Kysymykset.findAll", query = "SELECT k FROM Kysymykset k")
public class Kysymykset implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "KYSYMYS_ID")
  private int kysymysId;
  private String kysymys;

  public Kysymykset(int id, String kysymys) {
    setKysymysId(id);
    this.kysymys = kysymys;
  }

  public Kysymykset() {	  
  }

  public Kysymykset(String kysymys) {	  
  }

  public int getKysymysId() {
    return this.kysymysId;
  }

  public void setKysymysId(int kysymysId) {
    this.kysymysId = kysymysId;
  }

  public String getKysymys() {
    return this.kysymys;
  }

  public void setKysymys(String kysymys) {
    this.kysymys = kysymys;
  }

}