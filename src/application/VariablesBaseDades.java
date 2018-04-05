package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mvila on 09/05/2017.
 */

public class VariablesBaseDades {
	public Connection conn = null; //Connexió
    public Statement stmt = null;
    public ResultSet rs; //Resultats consulta
    String e; //String d'errors
}