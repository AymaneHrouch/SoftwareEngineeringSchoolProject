package marksManager.service;

import java.util.List;
import java.util.Optional;

import com.google.gson.JsonObject;

import marksManager.dao.ElementDao;
import marksManager.dao.ProfessorDao;
import marksManager.model.Professor;

public class ProfessorService {

	private final ProfessorDao professorDao;
	private final ElementDao elementDao;

	public ProfessorService(ProfessorDao professorDao, ElementDao elementDao) {
		this.professorDao = professorDao;
		this.elementDao = elementDao;
	}

	public Professor getProfessor(Long id) {
		Optional<Professor> professor = professorDao.findById(id);
		if (professor.isPresent()) {
			return professor.get();
		} else {
			return null;
		}
	}

	public List<Professor> getAllProfessors() {
		List<Professor> professors = professorDao.findAll();
		return professors;
	}

	public List<marksManager.model.Element> getProfessorElements(Long professorId) {
		List<marksManager.model.Element> elements = elementDao
				.findElementsByProfessorId(professorId);

		return elements;
	}

	public void createProfessor(Professor professor) {
		professorDao.save(professor);
	}

	public void updateProfessor(Long professorId, Professor newProfessor)
			throws Exception {
		Optional<Professor> professorOptional = professorDao.findById(professorId);
		if (professorOptional.isPresent()) {
			Professor professor = professorOptional.get();
			professor.setFirstName(newProfessor.getFirstName());
			professor.setLastName(newProfessor.getLastName());
			professor.setSpeciality(newProfessor.getSpeciality());
			professorDao.update(professor);
		} else {
			throw new Exception("Professor with id=" + professorId + " does NOT exist.");
		}
	}

	public void deleteProfessor(Long professorId) {
		professorDao.delete(professorId);
	}

	public Professor mapJson(JsonObject json) throws IllegalArgumentException {
		StringBuilder exceptionMessage = new StringBuilder();
		if (!json.has("firstName"))
			exceptionMessage.append("firstName entry is required\n");
		if (!json.has("lastName"))
			exceptionMessage.append("lastName entry is required\n");
		if (!json.has("speciality"))
			exceptionMessage.append("speciality entry is required\n");

		if (exceptionMessage.length() > 0) {
			throw new IllegalArgumentException(exceptionMessage.toString());
		}

		Professor professor = new Professor(
				json.get("firstName").toString().replace("\"", ""),
				json.get("lastName").toString().replace("\"", ""),
				json.get("speciality").toString().replace("\"", ""));

		return professor;
	}
}
