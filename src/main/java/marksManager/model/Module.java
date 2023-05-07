package marksManager.model;

public class Module implements Entity {
	private Long id;
	private String name;
	// Many-To-One
	private Filiere filiere;
	private Semester semester;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", filiere=" + filiere
				+ ", semester=" + semester + "]";
	}

	public Module(Long id, String name, Filiere filiere, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.filiere = filiere;
		this.semester = semester;
	}

	public Module(String name, Filiere filiere, Semester semester) {
		super();
		this.name = name;
		this.filiere = filiere;
		this.semester = semester;
	}

	public Module() {

	}

}
