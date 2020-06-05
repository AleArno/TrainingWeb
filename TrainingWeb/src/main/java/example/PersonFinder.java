package example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonFinder {

	private InputParameters in;

	public InputParameters getIn() {
		return in;
	}

	public void setIn(InputParameters in) {
		this.in = in;
	}

	public PersonFinder(InputParameters in) {
		this.in = in;
	}

	public List<Person> find() {
		List<Person> people = new ArrayList<Person>();
		List<String> conditions = new ArrayList<String>();
		DatabaseConnection dc = DatabaseConnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		dc.start();

		try {
			PreparedStatement stmt = dc.getCon().prepareStatement(qb.query(in));

			conditions = getConditions(in);

			if (conditions.size() > 0) {

				if (conditions.contains("name")) {
					stmt.setString(1, in.getName());

					if (conditions.contains("surname")) {
						stmt.setString(2, in.getSurname());
						if (conditions.contains("birthFrom") && conditions.contains("birthTo")) {
							stmt.setString(3, in.getBirthFrom());
							stmt.setString(4, in.getBirthTo());
						} else {
							if (conditions.contains("birthFrom")) {
								stmt.setString(3, in.getBirthFrom());
							}
							if (conditions.contains("birthTo")) {
								stmt.setString(3, in.getBirthTo());
							}
						}
						// non c'è il cognome
					} else {
						if (conditions.contains("birthFrom") && conditions.contains("birthTo")) {
							stmt.setString(2, in.getBirthFrom());
							stmt.setString(3, in.getBirthTo());
						} else {
							if (conditions.contains("birthFrom")) {
								stmt.setString(2, in.getBirthFrom());
							}
							if (conditions.contains("birthTo")) {
								stmt.setString(2, in.getBirthTo());
							}
						}
					}

				}
				// non c'è il nome
				else {
					if (conditions.contains("surname")) {
						stmt.setString(1, in.getSurname());
						if (conditions.contains("dateFrom") && conditions.contains("dateTo")) {
							stmt.setString(2, in.getBirthFrom());
							stmt.setString(3, in.getBirthTo());
						} else {
							if (conditions.contains("birthFrom")) {
								stmt.setString(2, in.getBirthFrom());
							}
							if (conditions.contains("birthTo")) {
								stmt.setString(2, in.getBirthTo());
							}
						}
						// non c'è il cognome
					} else {
						if (conditions.contains("birthFrom") && conditions.contains("birthTo")) {
							stmt.setString(1, in.getBirthFrom());
							stmt.setString(2, in.getBirthTo());
						} else {
							if (conditions.contains("birthFrom")) {
								stmt.setString(1, in.getBirthFrom());
							}
							if (conditions.contains("birthTo")) {
								stmt.setString(1, in.getBirthTo());
							}
						}
					}
				}

			}
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Person p = new Person();
				p.setName(rs.getString("name"));
				p.setSurname(rs.getString("surname"));
				p.setBirth(rs.getString("birth"));
				p.setCf(rs.getString("uniqueKey"));
				people.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		dc.closeConnection();

		return people;
	}

	private List<String> getConditions(InputParameters in) {
		List<String> condition = new ArrayList<String>();

		if (in.getName().isEmpty() != true && in.getName() != null) {
			condition.add("name");
		}
		if (in.getSurname().isEmpty() != true && in.getSurname() != null) {
			condition.add("surname");
		}
		if (in.getBirthFrom().isEmpty() != true && in.getBirthFrom() != null) {
			condition.add("birthFrom");
		}
		if (in.getBirthTo().isEmpty() != true && in.getBirthTo() != null) {
			condition.add("birthTo");
		}

		return condition;

	}

}
