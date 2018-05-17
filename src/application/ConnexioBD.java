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
			// con = DriverManager.getConnection("jdbc:postgresql://144.217.11.3/luxyrestaurant", "luxy", "LuxyRestaurant");
			con = DriverManager.getConnection("jdbc:postgresql://192.168.16.127/luxyrestaurant", "postgres","postgres");
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
	 * @param sCommand
	 * @param db
	 */
	public void execDB(String sCommand) {
		try {
			con.createStatement().execute(sCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return con;
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
