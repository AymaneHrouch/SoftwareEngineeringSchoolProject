package marksManager.dao;

import java.util.List;

import marksManager.model.Element;

public interface ElementDao extends Dao<Element> {

	List<Element> findElementsByProfessorId(Long professorId);

}
