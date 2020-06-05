package example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDelete {

	public boolean delete(String id) {
		boolean result = false;

		DatabaseConnection dc = DatabaseConnection.getInstance();
		String qry = "DELETE FROM person where uniqueKey=?";
		dc.start();

		try {
			PreparedStatement stmt = dc.getCon().prepareStatement(qry);
			stmt.setString(1, id);
			result = stmt.execute();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}
}
