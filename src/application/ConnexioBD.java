package application;

import java.sql.DriverManager;

public class ConnexioBD {

	/**
	 * CONNEXIÓ BASE DE DADES
	 * 
	 * @param db
	 */
	
	public ConnexioBD(VariablesBaseDades db) {
		db.conn = null;

		try {
			Class.forName("org.postgresql.Driver");
			db.conn = DriverManager.getConnection("jdbc:postgresql://144.217.11.3/luxyrestaurant", "luxy", "LuxyRestaurant");
		} catch (Exception e) {
			e.getStackTrace();
		}

		try {
			db.stmt = db.conn.createStatement();
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
	public void queryDB(String sQuery, VariablesBaseDades db) {
		try {
			db.rs = db.stmt.executeQuery(sQuery);
		} catch (Exception e) {
		}
	}

	/**
	 * SCRIPTS BASE DE DADES
	 * 
	 * @param sQuery
	 * @param db
	 */
	public void updateDB(String sQuery, VariablesBaseDades db) {
		try {
			db.stmt.executeUpdate(sQuery);
			db.conn.commit();
		} catch (Exception e) {
		}
	}

	/**
	 * DESCONEXIÓ BASE DE DADES
	 * 
	 * @param db
	 */
	public void desconnectarDB(VariablesBaseDades db) {
		try {
			db.stmt.close();
			db.conn.close();
		} catch (Exception e) {
		}
	}

}
