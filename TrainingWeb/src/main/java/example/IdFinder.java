package example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdFinder {

	public boolean controlId(String id) {
		boolean exist=false;
		DatabaseConnection dc=DatabaseConnection.getInstance();
		dc.start();
		String qry="SELECT * FROM person WHERE uniqueKey=?;";
		PreparedStatement stmt;
		try {
			stmt = dc.getCon().prepareStatement(qry);
			stmt.setString(1, id);
			ResultSet rs=stmt.executeQuery();
			rs.last();
			int rows = rs.getRow();
			if(rows>0) {
				exist=true;
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dc.closeConnection();
		}
		return exist;
	}
	
}
