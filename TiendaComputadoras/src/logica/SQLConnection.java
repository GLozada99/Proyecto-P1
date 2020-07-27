package logica;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SQLConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String connectionUrl = "jdbc:sqlserver://LAPTOP-TPPBSJFQ\\SQLEXPRESS;database=CovidUniversidad;integratedSecurity=true;";
		JOptionPane.showMessageDialog(null,"Connected");
		//Connection con = DriverManager.getConnection(connectionUrl);  

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM dbo.Estudiante";
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("PrimerNombre") + " " + rs.getString("PrimerApellido"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
	}

}