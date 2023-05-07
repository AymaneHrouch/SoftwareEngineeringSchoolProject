package marksManager.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import marksManager.dao.ProfessorDao;
import marksManager.model.Professor;
import marksManager.model.Speciality;

public class ProfessorDaoImpl extends DefaultDao<Professor> implements ProfessorDao {

	@Override
	public String getTableName() {
		return "professor";
	}

	@Override
	public String getPrimaryKeyLabel() {
		return "id";
	}

	@Override
	public String[] getColumnLabels() {
		return new String[] { getPrimaryKeyLabel(), "first_name", "last_name",
				"speciality" };
	}

	@Override
	public Professor map(Map<String, String> professorMap) {
		return new Professor(
				Long.parseLong(professorMap.get("id")),
				(String) professorMap.get("first_name"),
				(String) professorMap.get("last_name"),
				(String) professorMap.get("speciality"));
	}

	@Override
	public Map<String, String> getColumns(Professor professor) {
		HashMap<String, String> map = new HashMap<>();
		map.put("first_name", professor.getFirstName());
		map.put("last_name", professor.getLastName());
		map.put("speciality", professor.getSpeciality().getName());
		return map;
	}

	@Override
	public List<Professor> findBySpeciality(Speciality speciality) {
		return findBy("speciality", speciality.getName());
	}

	// Demonstration
	public static void main(String[] args) throws Exception {
		/*
		 * Scanner sc = new Scanner(System.in); // Printing all professors ProfessorDao
		 * professorDao = new ProfessorDao(); professorDao.printAll();
		 * 
		 * // Adding a new professor Professor newProf = new Professor("mohamed",
		 * "nasri", "génie logiciel"); professorDao.save(newProf); sc.nextLine();
		 * professorDao.printAll();
		 * 
		 * // Editing a professor System.out.println("\n\n\n");
		 * professorDao.update(newProf, new Object[] { "MOHAMED", "NASRI", new
		 * Speciality("GÉNIE LOGICIEL") }); System.out.println( "\n\nAFTER UPDATE: " +
		 * professorDao.get(newProf.getId()).get()); System.out.println("\n");
		 * professorDao.printAll(); sc.nextLine();
		 * 
		 * // Deleting a professor System.out.println("\n\n\nRemoving a professor");
		 * professorDao.delete(newProf); professorDao.printAll();
		 */
		ProfessorDaoImpl professorDao = new ProfessorDaoImpl();
		Professor professor = professorDao.findById(1L).get();
		Professor newProf = new Professor("mohamed",
				"nasri", "génie logiciel");
		newProf.setFirstName("heheehe");
		professorDao.update(newProf);
		professorDao.save(newProf);
	}
}
