package application;

import java.sql.DriverManager;

public class BaseDades {

	/**
     * CONNEXIÓ BASE DE DADES
     * @param db
     */
    public static void ConnectarDB( VariablesBaseDades db ){
        db.conn = null;

        try {
        	Class.forName( "org.postgresql.Driver" );
            db.conn = DriverManager.getConnection("jdbc:postgresql://144.217.11.3/luxyrestaurant","luxy","LuxyRestaurant" );
            db.conn.setAutoCommit( false );
        } catch (Exception e) {
        	e.getStackTrace();
        }

        try {
            db.stmt = db.conn.createStatement();
        } catch ( Exception e ) {
        	e.getStackTrace();
        }
    }

    /**
     * CONSULTA BASE DE DAES
     * @param sQuery
     * @param db
     */
    public static void QueryDB( String sQuery , VariablesBaseDades db ){
        try {
            db.rs = db.stmt.executeQuery( sQuery );
        } catch (Exception e) {}
    }

    /**
     * SCRIPTS BASE DE DADES
     * @param sQuery
     * @param db
     */
    public static void UpdateDB( String sQuery , VariablesBaseDades db ){
        try {
            db.stmt.executeUpdate( sQuery );
            db.conn.commit();
        } catch ( Exception e ) {}
    }

    /**
     * DESCONEXIÓ BASE DE DADES
     * @param db
     */
    public static void DesconnectarDB( VariablesBaseDades db ){
        try {
            db.stmt.close();
            db.conn.close();
        } catch ( Exception e ) {}
    }

}
