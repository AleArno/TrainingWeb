package example;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class InputParameters {

	private String name;
	private String surname;
	private String birthFrom;
	private String birthTo;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthFrom() {
		return birthFrom;
	}

	public void setBirthFrom(String birthFrom) {
		this.birthFrom = birthFrom;
	}

	public String getBirthTo() {
		return birthTo;
	}

	public void setBirthTo(String birthTo) {
		this.birthTo = birthTo;
	}


	

}
