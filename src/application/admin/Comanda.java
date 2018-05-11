package application.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.ConnexioBD;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Comanda {
	private SimpleIntegerProperty id;
    private SimpleStringProperty taula;
    private SimpleStringProperty primer;
    //private SimpleIntegerProperty qtatPrimer;
    private SimpleStringProperty segon;
    //private SimpleIntegerProperty qtatSegon;
    private SimpleStringProperty postre;
    //private SimpleIntegerProperty qtatPostre;
    private SimpleStringProperty plat;
    //private int compt = 0;
    boolean bprimer=false;
    boolean bsegon=false;
    boolean bpostre=false;
    Comanda(){
    	
    }
    Comanda(Connection conn,int id,int taula,int primer, int segon, int postre){
    	try {
    		Statement stmt= conn.createStatement();
    		this.id = new SimpleIntegerProperty(id);
    	ResultSet t = stmt.executeQuery("select nom from taules where id="+taula);
    	
    	t.next();
		this.taula = new SimpleStringProperty(t.getString(1));
			
	
		t.close();
    	ResultSet p = stmt.executeQuery("SELECT nom,quantitat FROM plats WHERE id="+primer);
    	p.next();
		this.primer = new SimpleStringProperty(p.getString(1));
		this.plat = new SimpleStringProperty(p.getString(1));
		//this.qtatPrimer = new SimpleIntegerProperty(p.getInt(2));
		
    	p.close();
    	ResultSet s = stmt.executeQuery("SELECT nom,quantitat FROM plats WHERE id="+segon);
    	s.next();
    		this.segon = new SimpleStringProperty(s.getString(1));
			//this.qtatSegon = new SimpleIntegerProperty(s.getInt(2));
		
    	s.close();
    	ResultSet po = stmt.executeQuery("SELECT nom,quantitat FROM plats WHERE id="+postre);
    	po.next();
    		this.postre = new SimpleStringProperty(po.getString(1));
			//this.qtatPostre = new SimpleIntegerProperty(po.getInt(2));
		
    	po.close();
    	stmt.close();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int getId() {
        return id.get();
    }

    public String getTaula() {
        return taula.get();
    }
    
    public String getPrimer() {
        return primer.get();
    }
    
    public String getSegon() {
        return segon.get();
    }
    public String getPostre() {
    	return postre.get();
    }
 

}
