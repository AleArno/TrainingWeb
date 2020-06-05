package example;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

	private String query = "SELECT * FROM person ";

	public String query(InputParameters in) {
		List<String> conditions = new ArrayList<String>();
		conditions = getConditions(in);
		boolean from = false;
		boolean to = false;
		if (conditions.contains("birthFrom")) {
			from = true;
		}
		if (conditions.contains("birthTo")) {
			to = true;
		}

		if (conditions.size() > 0) {
			this.query = query.concat("WHERE ");

			for (int i = 0; i < conditions.size(); i++) {
				if (i != 0 && i < conditions.size()) {
					this.query = query.concat("AND ");
				}
				if (conditions.get(i).equals("name")) {
					insertName();
				}
				if (conditions.get(i).equals("surname")) {
					insertSurname();
				}
				if (conditions.get(i).equals("birthFrom") && to != true) {
					insertDateFrom();
				}
				if (conditions.get(i).equals("birthTo") && from != true) {
					insertDateTo();
				}
				if (conditions.get(i).equals("birthFrom") && to == true) {
					insertDateInterval();
					i = conditions.size();
				}

			}
		}
		this.query = query.concat(";");
		return query;
	}

	private String insertDateInterval() {
		this.query = query.concat("(birth BETWEEN ? AND ?) ");
		return query;
	}

	private String insertDateFrom() {
		this.query = query.concat("birth >=? ");
		return query;
	}

	private String insertDateTo() {
		this.query = query.concat("birth <=? ");
		return query;
	}

	private String insertName() {
		this.query = query.concat("name=? ");
		return query;
	}

	private String insertSurname() {
		this.query = query.concat("surname=? ");
		return query;
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
