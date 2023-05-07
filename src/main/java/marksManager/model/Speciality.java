package marksManager.model;

public class Speciality {
	private String name;

	public Speciality(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Speciality [name=" + name + "]";
	}

}