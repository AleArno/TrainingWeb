package example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PersonInsert {

	public boolean insert(Person p) {
		boolean exist=false;
		boolean id=controlId(p.getCf());
		DatabaseConnection dc=DatabaseConnection.getInstance();
		if(id==false) {
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
			exist=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		return exist;
		
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
