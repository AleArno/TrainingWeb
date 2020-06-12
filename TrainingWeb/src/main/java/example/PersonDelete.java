package example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDelete {

	public String delete(String id) {
		String message;
		IdFinder idf=new IdFinder();
		boolean exist=idf.controlId(id);
		if(exist==true) {
		DatabaseConnection dc = DatabaseConnection.getInstance();
		String qry = "DELETE FROM person where uniqueKey=?";
		dc.start();
		PreparedStatement stmt;
		try {
			stmt = dc.getCon().prepareStatement(qry);
			stmt.setString(1, id);
			stmt.execute();
			stmt.close();
			message="Person deleted!";
		} catch (SQLException e) {
			message="Error deleting the person";
		}
		finally {
			dc.closeConnection();
		}
		}else {
			message="There aren't people with the given cf";
		}
		return message;
	}
}
