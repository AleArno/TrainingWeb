package example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PersonInsert {

	public String insert(Person p) {
		String message="Duplicated id!";
		IdFinder idf=new IdFinder();
		boolean id=idf.controlId(p.getCf());
		DatabaseConnection dc=DatabaseConnection.getInstance();
		if(id==false) {
			dc.start();
		String qry="INSERT INTO person (uniqueKey,name,surname,birth,tinsert) VALUES(?,?,?,?,?);";
		try {
			PreparedStatement stmt = dc.getCon().prepareStatement(qry);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			stmt.setString(1, p.getCf());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getSurname());
			stmt.setString(4, p.getBirth());
			stmt.setString(5, timestamp+"");
			stmt.execute();
			stmt.close();
			message="Person successfully insert in DB!";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dc.closeConnection();
		}
		}
		
		return message;
		
	}
	
	
}
