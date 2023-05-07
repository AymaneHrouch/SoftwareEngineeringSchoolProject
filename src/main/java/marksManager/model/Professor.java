package marksManager.model;

import java.util.List;

public class Professor implements Entity {
	private Long id;
	private String firstName;
	private String lastName;
	private Speciality speciality;
	private List<Element> elements;

	public Professor(Long id, String firstName, String lastName, Speciality speciality) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.speciality = speciality;
	}

	public Professor(Long id, String firstName, String lastName, String speciality) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.speciality = new Speciality(speciality);
	}

	public Professor(String firstName, String lastName, String speciality) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.speciality = new Speciality(speciality);
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", speciality=" + speciality + "]";
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public void addElement(Element element) {
		elements.add(element);
		element.setProfessor(this);
	}

	@Override
	public boolean equals(Object o) {
		Professor p = (Professor) o;

		if (p.getFirstName().equals(getFirstName()) &&
				p.getLastName().equals(getLastName()) &&
				p.getSpeciality().getName().equals(getSpeciality().getName())) {
			return true;
		} else
			return false;
	}

}
