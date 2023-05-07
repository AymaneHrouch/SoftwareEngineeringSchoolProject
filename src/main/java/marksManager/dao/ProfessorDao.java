package marksManager.dao;

import java.util.List;

import marksManager.model.Professor;
import marksManager.model.Speciality;

public interface ProfessorDao extends Dao<Professor> {
	List<Professor> findBySpeciality(Speciality speciality);
}
