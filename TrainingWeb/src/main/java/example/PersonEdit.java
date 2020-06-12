package example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonEdit {

	public String edit(String id, Person p) {
		String message;
		String qry = "UPDATE person SET uniqueKey=?, name=?,surname=?, birth=? WHERE uniqueKey=?";
		IdFinder idf = new IdFinder();
		boolean controlId = idf.controlId(id);
		boolean existNewId;
		if(id.equals(p.getCf())) {
			existNewId=false;
		}else {
			existNewId= idf.controlId(p.getCf());
		}
		DatabaseConnection dc = DatabaseConnection.getInstance();
		PreparedStatement stmt;
		if (controlId == true) {
			if (existNewId == false) {
				dc.start();
				try {
					stmt = dc.getCon().prepareStatement(qry);
					stmt.setString(1, p.getCf());
					stmt.setString(2, p.getName());
					stmt.setString(3, p.getSurname());
					stmt.setString(4, p.getBirth());
					stmt.setString(5, id);
					stmt.execute();
					stmt.close();
					message = "Person successfully UPDATED!";
				} catch (SQLException e) {
					message = "Error updating person";
				} finally {
					dc.closeConnection();
				}
			} else {
				message = "In the DB already exist a person with the given CF!!";
			}
		} else {
			message = "In the Db there are not people with the given id";
		}

		return message;
	}

	// TODO control of the person id if is just used in db

}
