package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Objectes {
	static class Beguda{
		int id;
		String nom;
		boolean en_estoc;
		int zona;
		double preu;
		ResultSet rs;
		
		Beguda(Statement stmt, String id) throws SQLException{
			rs = stmt.executeQuery("Select * from begudes where id="+id);
			
			while(rs.next()) {
				this.id = rs.getInt("id");
				this.nom = rs.getString("nom");
				this.en_estoc = rs.getBoolean("en_estoc");
				this.zona = rs.getInt("zona");
				this.preu = rs.getDouble("preu");
			}
		}
	}

	//--------------------------------------------------------------------------------------------------
	public static class Plat{
		int id;
		String nom;
		Boolean en_estoc;
		double rating;
		int vots;
		int ordre_plat;
		int zona;
		double preu;
		ResultSet rs;
		
		public Plat(Statement stmt, int id) throws SQLException {
			rs = stmt.executeQuery("Select * from plats where id="+id);
			
			while(rs.next()) {
				this.id = rs.getInt("id");
				this.nom = rs.getString("nom");
				this. en_estoc = rs.getBoolean("en_estoc");
				this.rating = rs.getDouble("rating");
				this.vots = rs.getInt("vots");
				this.ordre_plat = rs.getInt("ordre_plat");
				this.zona = rs.getInt("zona");
				this.preu = rs.getDouble("preu");
			}
		}
	}
	//--------------------------------------------------------------------------------------------------
	static class Sala{
		int id;
		String nom;
		ResultSet rs;
		
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}

		public String getNom() {return nom;}
		public void setNom(String nom) {this.nom = nom;}
	
		Sala(Statement stmt, String id) throws SQLException {
			rs = stmt.executeQuery("Select * from sales where id="+id);
			
			while(rs.next()) {
				this.id = rs.getInt("id");
				this.nom = rs.getString("nom");
			}
		}
	}
	//--------------------------------------------------------------------------------------------------
	static class Taula{
		int id;
		String nom;
		int persones;
		int id_sala;
		ResultSet rs;
		
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}

		public String getNom() {return nom;}
		public void setNom(String nom) {this.nom = nom;}

		public int getPersones() {return persones;}
		public void setPersones(int persones) {this.persones = persones;}

		public int getId_sala() {return id_sala;}
		public void setId_sala(int id_sala) {this.id_sala = id_sala;}
		
		Taula(Statement stmt, String id) throws SQLException {
			rs = stmt.executeQuery("Select * from taules where id="+id);
			
			while(rs.next()) {
				System.out.print(rs.getInt("id")+"\t"+rs.getString("nom")+
				"\t"+rs.getInt("persones")+"\t"+rs.getInt("id_sala"));
				System.out.println();
			}
		}
	}

}

