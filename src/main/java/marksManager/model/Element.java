package marksManager.model;

import java.util.List;

public class Element implements Entity {
	private Long id;
	private String name;
	private double coefficient;
	private List<EvaluationMethod> evaluationMethods;
	private Module module;
	private Professor professor;

	public Element(long id, String name, double coefficient,
			List<EvaluationMethod> evaluationMethods, Module module,
			Professor professor) {
		super();
		this.id = id;
		this.name = name;
		this.coefficient = coefficient;
		this.evaluationMethods = evaluationMethods;
		this.module = module;
		this.professor = professor;
	}

	public Element(String name, double coefficient,
			List<EvaluationMethod> evaluationMethods, Module module,
			Professor professor) {
		super();
		this.name = name;
		this.coefficient = coefficient;
		this.evaluationMethods = evaluationMethods;
		this.module = module;
		this.professor = professor;
	}

	public Element(String name, double coefficient, Module module, Professor professor) {
		super();
		this.name = name;
		this.coefficient = coefficient;
		this.module = module;
		this.professor = professor;
	}

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

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public List<EvaluationMethod> getEvaluationMethods() {
		return evaluationMethods;
	}

	public void setEvaluationMethods(List<EvaluationMethod> evaluationMethods) {
		this.evaluationMethods = evaluationMethods;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
