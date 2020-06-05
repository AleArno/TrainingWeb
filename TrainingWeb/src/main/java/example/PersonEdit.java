package example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonEdit {

	public boolean edit(String id, Person p) {
		String qry="UPDATE person SET uniqueKey=?, name=?,surname=?, birth=? WHERE uniqueKey=?";
		boolean result=false;
		boolean controlId=controlId(id);
		DatabaseConnection dc= DatabaseConnection.getInstance();
		PreparedStatement stmt;
		if(controlId==true) {
		try {
			stmt = dc.getCon().prepareStatement(qry);
			stmt.setString(1, p.getCf());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getSurname());
			stmt.setString(4, p.getBirth());
			stmt.setString(5, id);
			stmt.execute();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}else {
		System.out.println("In the Db there are not people with the given id");
		}
		
		return result;
	}
	
	private boolean controlId(String id) {
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
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
}
