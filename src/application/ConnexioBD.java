package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexioBD {

	
	/**
	 * CONNEXI� BASE DE DADES
	 * 
	 * @param db
	 */	
	Connection con = null;
	public ConnexioBD() {

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://144.217.11.3/luxyrestaurant", "luxy", "LuxyRestaurant");
		} catch (Exception e) {
			e.getStackTrace();
		}

		
	}

	/**
	 * CONSULTA BASE DE DAES
	 * 
	 * @param sQuery
	 * @param db
	 */
	public ResultSet queryDB(String sQuery) {
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery(sQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * SCRIPTS BASE DE DADES
	 * 
	 * @param sQuery
	 * @param db
	 */
	public void updateDB(String sQuery) {
		try {
			con.createStatement().executeUpdate(sQuery);
			//db.conn.commit();
		} catch (Exception e) {
		}
	}

	/**
	 * DESCONEXI� BASE DE DADES
	 * 
	 * @param db
	 */
	public void desconnectarDB() {
		try {
			con.close();
		} catch (Exception e) {
		}
	}

}
